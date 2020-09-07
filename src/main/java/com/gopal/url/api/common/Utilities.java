package com.gopal.url.api.common;

import org.omg.CORBA.portable.InputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {

	private static final Charset UTF_8 = StandardCharsets.UTF_8;

	public static String createCacheUrl(String clientId, String longUrl){
		StringBuilder cachedUrl = new StringBuilder(clientId);
		cachedUrl.append("-");
		cachedUrl.append(longUrl);

		return cachedUrl.toString();
	}

	public static String getShortUrl(String shortUrl){

		String[] urls=shortUrl.split("/");
		return urls[urls.length-1];
	}

	public static byte[] digest(byte[] input) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		byte[] result = md.digest(input);
		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			if(sb.length()>8) break;
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public static String getMd5(String url){
		byte[] md5InBytes = digest(url.getBytes(UTF_8));
		return bytesToHex(md5InBytes);
	}
}
