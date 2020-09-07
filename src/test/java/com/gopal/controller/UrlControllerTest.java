package com.gopal.controller;

import com.gopal.url.api.controller.UrlController;
import com.gopal.url.api.service.UrlService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@WebMvcTest
public class UrlControllerTest {

//	private UrlController urlController;

	@Test
	public void getShortenUrl() throws Exception {

		String longUrl = "http://localhost:8080/abc/def/ghi/jkl";
		String clintId = "gopal.nitncse49@gmail.com";
		// Given
		HttpUriRequest request = new HttpGet( "http://localhost:8090/url/api/health");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
		System.out.println(httpResponse.getStatusLine().getStatusCode());
	}
}
