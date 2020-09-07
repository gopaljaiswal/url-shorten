package com.gopal.url.api.service;

import com.gopal.url.api.dto.UrlDTO;

public interface ConversionService {

	void init();

	Boolean checkLongUrlExists(String longUrl);

	Long getLongUrlId(String longUrl);

	Boolean checkShortUrlExists(Long urlId);

	String getShortUrlFromId(Long urlId);

	String createShortUrl(UrlDTO urlDTO);

	Long getIdFromShortUrl(String shortURL);

	String getOriginalUrlFromId(Long urlId);

	String getShortenUrlFromHashedUrl(String shortenedUrlMD5Hash);

}
