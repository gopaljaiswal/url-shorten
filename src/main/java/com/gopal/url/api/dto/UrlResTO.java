package com.gopal.url.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlResTO implements Serializable {

	private String longUrl;
	private String clientId;
	private String shortUrl;
	private Long hitCounts;

	public UrlResTO(String longUrl, String clientId, String shortUrl){
		this.longUrl=longUrl;
		this.clientId=clientId;
		this.shortUrl=shortUrl;
	}

	public UrlResTO(String shortUrl, String longUrl){
		this.longUrl=longUrl;
		this.shortUrl=shortUrl;
	}

	public UrlResTO(String shortUrl, Long hitCounts){
		this.shortUrl=shortUrl;
		this.hitCounts=hitCounts;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public Long getHitCounts() {
		return hitCounts;
	}

	public void setHitCounts(Long hitCounts) {
		this.hitCounts = hitCounts;
	}
}
