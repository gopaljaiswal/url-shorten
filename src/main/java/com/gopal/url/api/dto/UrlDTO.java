package com.gopal.url.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlDTO implements Serializable {

	private Long id;
	private String longUrl;
	private String clientId;
	private String shortUrl;
	private String cachedUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCachedUrl() {
		return cachedUrl;
	}

	public void setCachedUrl(String cachedUrl) {
		this.cachedUrl = cachedUrl;
	}

	@Override
	public String toString() {
		return "UrlDTO{" +
				"id=" + id +
				", longUrl='" + longUrl + '\'' +
				", clientId='" + clientId + '\'' +
				", shortUrl='" + shortUrl + '\'' +
				", cachedUrl='" + cachedUrl + '\'' +
				'}';
	}
}
