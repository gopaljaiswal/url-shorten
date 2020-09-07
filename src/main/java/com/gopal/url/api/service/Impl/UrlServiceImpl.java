package com.gopal.url.api.service.Impl;

import com.gopal.url.api.common.Constants;
import com.gopal.url.api.common.Utilities;
import com.gopal.url.api.dto.UrlDTO;
import com.gopal.url.api.dto.UrlResTO;
import com.gopal.url.api.exception.CustomRestException;
import com.gopal.url.api.service.ConversionService;
import com.gopal.url.api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Service("UrlService")
public class UrlServiceImpl implements UrlService {

	private ConcurrentHashMap<Long, Long> hitMap;

	@Autowired
	private ConversionService conversionService;

	@Override
	@PostConstruct
	public void init() {
		hitMap = new ConcurrentHashMap<>();
	}

	@Override
	public UrlResTO getShortenedURL(String clientId, String longUrl) {

		String cacheUrl = Utilities.createCacheUrl(clientId, longUrl);
		Long id = null;
		String shortenedUrl = null;
		if (conversionService.checkLongUrlExists(cacheUrl)) {
			id = conversionService.getLongUrlId(cacheUrl);
			if (id != null) {
				shortenedUrl = conversionService.getShortUrlFromId(id);
			}
		}
		if (id==null || shortenedUrl == null) {
			UrlDTO urlDTO = new UrlDTO();
			urlDTO.setLongUrl(longUrl);
			urlDTO.setClientId(clientId);
			urlDTO.setCachedUrl(cacheUrl);
			shortenedUrl = conversionService.createShortUrl(urlDTO);
		}
		//prepare response object
		UrlResTO urlResTO = new UrlResTO(longUrl, clientId, Constants.baseUrl + shortenedUrl);
		return urlResTO;
	}

	@Override
	public UrlResTO getOriginalURL(String shortUrl) {

		String hashedUrl = Utilities.getShortUrl(shortUrl);
		String suffixShortUrl = conversionService.getShortenUrlFromHashedUrl(hashedUrl);
		Long id = conversionService.getIdFromShortUrl(suffixShortUrl);
		hitMap.put(id, hitMap.getOrDefault(id, 0L) + 1);
		if (!conversionService.checkShortUrlExists(id)) {
			throw new CustomRestException.shorternUrlNotExists(shortUrl);
		}
		//prepare response object
		UrlResTO urlResTO = new UrlResTO(shortUrl, conversionService.getOriginalUrlFromId(id));
		return urlResTO;
	}

	@Override
	public UrlResTO getHitCount(String shortUrl) {
		String hashedUrl = Utilities.getShortUrl(shortUrl);
		String suffixShortUrl = conversionService.getShortenUrlFromHashedUrl(hashedUrl);
		Long id = conversionService.getIdFromShortUrl(suffixShortUrl);
		Long hitCounts = 0L;
		if (hitMap.size() > 0 && hitMap.containsKey(id)) {
			hitCounts = hitMap.get(id);
		}
		//prepare response object
		UrlResTO urlResTO = new UrlResTO(shortUrl, hitCounts);
		return urlResTO;
	}
}
