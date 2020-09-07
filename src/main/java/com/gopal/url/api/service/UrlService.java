package com.gopal.url.api.service;

import com.gopal.url.api.dto.UrlResTO;

public interface UrlService {

	void init();

	UrlResTO getShortenedURL(String clientId, String longUrl);

	UrlResTO getOriginalURL(String shortUrl);

	UrlResTO getHitCount(String shortUrl);

}
