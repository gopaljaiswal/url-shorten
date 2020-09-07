package com.gopal.url.api.service.Impl;

import com.gopal.url.api.common.Constants;
import com.gopal.url.api.common.Utilities;
import com.gopal.url.api.dto.UrlDTO;
import com.gopal.url.api.exception.CustomRestException;
import com.gopal.url.api.service.ConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Service("ConversionService")
public class ConversionServiceImpl implements ConversionService {

	private String validChars;
	private Long id;
	private ConcurrentHashMap<String, Long> longUrlMap;
	private ConcurrentHashMap<Long, UrlDTO> urlIdMap;
	private ConcurrentHashMap<String, String> shortUrlMD5Map;
	private int BASE;

	@Override
	@PostConstruct
	public void init() {
		id = 0L;
		longUrlMap = new ConcurrentHashMap<>();
		urlIdMap = new ConcurrentHashMap<>();
		shortUrlMD5Map = new ConcurrentHashMap<>();
		validChars = Constants.validChars;
		BASE = Constants.BASE;
	}

	@Override
	public Boolean checkLongUrlExists(String longUrl) {
		return longUrlMap.containsKey(longUrl);
	}

	@Override
	public Long getLongUrlId(String longUrl) {
		return longUrlMap.get(longUrl);
	}

	@Override
	public Boolean checkShortUrlExists(Long urlId) {
		return urlIdMap.containsKey(urlId);
	}

	@Override
	public String getOriginalUrlFromId(Long urlId) {
		UrlDTO urlDTO = urlIdMap.get(urlId);
		return urlDTO.getLongUrl();
	}

	@Override
	public String getShortUrlFromId(Long urlId) {
		UrlDTO urlDTO = urlIdMap.get(urlId);
		return urlDTO.getShortUrl();
	}

	@Override
	public synchronized String createShortUrl(UrlDTO urlDTO) {
		id += 1;
		Long urlId = id;
		StringBuilder shortUrl = new StringBuilder();
		while (urlId > 0) {
			shortUrl.append(validChars.charAt((int) (urlId % BASE)));
			urlId = urlId / BASE;
		}
		String shortenedUrl = shortUrl.reverse().toString();
		// here we add check and lookup to handle duplicate cases
		// Currently we are assuming no duplicate
		String shortenedUrlMD5Hash = Utilities.getMd5(shortenedUrl);

		urlDTO.setId(id);
		urlDTO.setShortUrl(shortenedUrlMD5Hash);

		// update id mapping
		urlIdMap.put(id, urlDTO);
		// update cache
		longUrlMap.put(urlDTO.getCachedUrl(), id);
		// update md5
		shortUrlMD5Map.put(shortenedUrlMD5Hash, shortenedUrl);
		System.out.println(urlIdMap);
		System.out.println(longUrlMap);
		return shortenedUrlMD5Hash;
	}

	@Override
	public String getShortenUrlFromHashedUrl(String shortenedUrlMD5Hash) {
		if(shortUrlMD5Map.size()==0){
			throw new CustomRestException.shorternUrlNotExists(shortenedUrlMD5Hash);
		}
		return shortUrlMD5Map.get(shortenedUrlMD5Hash);
	}

	@Override
	public Long getIdFromShortUrl(String shortURL) {
		Long id = 0L;
		for (int i = 0; i < shortURL.length(); i++) {
			id = id * BASE + validChars.indexOf(shortURL.charAt(i));
		}
		return id;
	}
}
