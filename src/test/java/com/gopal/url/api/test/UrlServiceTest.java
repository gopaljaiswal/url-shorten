package com.gopal.url.api.test;

import com.gopal.url.api.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@WebMvcTest
public class UrlServiceTest {

	@Autowired
	private UrlService urlService;

	@Test
	public void getShortenUrl() throws Exception {

		String longUrl = "http://localhost:8080/abc/def/ghi/jkl";
		String clintId = "gopal.nitncse49@gmail.com";

		String shortUrl = urlService.getShortenedURL(clintId, longUrl).getShortUrl();
		String originalUrl = urlService.getOriginalURL(shortUrl).getLongUrl();
		long hitCount = urlService.getHitCount(shortUrl).getHitCounts();

		System.out.println(shortUrl);
		System.out.println(originalUrl);
		System.out.println(hitCount);
	}
}
