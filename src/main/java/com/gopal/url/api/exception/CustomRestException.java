package com.gopal.url.api.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;


public class CustomRestException extends RuntimeException {
    private static final long serialVersionUID = -977217199574702703L;

    public static int INTERNAL_ERROR_CODE = -1;
    protected int httpErrorCode = SC_OK;
    protected int errorCode = Integer.MAX_VALUE;

    protected String exType;
    protected String exReason;

    protected Map<String, Object> meta;
    protected Map<String, String> dictionary;

    public CustomRestException() {
        super();
        this.meta = new LinkedHashMap<String, Object>();
    }

    public CustomRestException(String message) {
        super(message);
        this.meta = new LinkedHashMap<String, Object>();
    }

    public CustomRestException(String message, Throwable t) {
        super(message, t);
        this.meta = new LinkedHashMap<String, Object>();
    }

    public CustomRestException(String message, Throwable t, int errCode) {
        this(message, t);
        errorCode = errCode;
    }

    public CustomRestException(String message, Throwable t, int errCode, int httpErrCode) {
        this(message, t);
        errorCode = errCode;
        httpErrorCode = httpErrCode;
    }

    public CustomRestException(int errCode, int httpErrCode, String message) {
        this(message);
        errorCode = errCode;
        httpErrorCode = httpErrCode;
    }

    public CustomRestException(int errCode, int httpErrCode, String message, Throwable t) {
        this(message, t);
        errorCode = errCode;
        httpErrorCode = httpErrCode;
    }

    public String getExType() {
        return exType;
    }

    public void setExType(String exType) {
        this.exType = exType;
    }

    public String getExReason() {
        return exReason;
    }

    public void setExReason(String exReason) {
        this.exReason = exReason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, Object> meta) {
        this.meta = meta;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    /* Exceptions */
    public static final class shorternUrlNotExists extends CustomRestException {
        private static final long serialVersionUID = 1L;

        public shorternUrlNotExists(String url) {
            super(40001, SC_NOT_FOUND, String.format("Shortened [ %s ] doesn't exists in system !!", url));
        }
    }

}
