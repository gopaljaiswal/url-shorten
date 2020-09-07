package com.gopal.url.api.controller;

import com.gopal.url.api.common.GenericRes;
import com.gopal.url.api.dto.UrlResTO;
import com.gopal.url.api.exception.ControllerException;
import com.gopal.url.api.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v0")
public class UrlController extends ControllerException {

	@Autowired
	private UrlService urlService;

	@RequestMapping(value = "/getShortenedURL", method = RequestMethod.GET)
	@ResponseBody
	public GenericRes<UrlResTO> getShortenedURL(@RequestHeader String clientId, @RequestParam String url) {

		return new GenericRes<UrlResTO>(urlService.getShortenedURL(clientId, url));
	}

	@RequestMapping(value = "/getOriginalURL", method = RequestMethod.GET)
	@ResponseBody
	public GenericRes<UrlResTO> getShortenedURL(@RequestParam String url) {

		return new GenericRes<UrlResTO>(urlService.getOriginalURL(url));
	}

	@RequestMapping(value = "/getHitCount", method = RequestMethod.GET)
	@ResponseBody
	public GenericRes<UrlResTO> getHitCount(@RequestParam String url) {

		return new GenericRes<UrlResTO>(urlService.getHitCount(url));
	}

}
