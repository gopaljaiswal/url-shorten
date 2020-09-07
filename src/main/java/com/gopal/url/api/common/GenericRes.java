/**
 *
 */
package com.gopal.url.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gopal.url.api.enums.ExasolResponseStatus;
import com.gopal.url.api.exception.CustomRestException;

import java.util.Map;
import java.util.UUID;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericRes<T> {

    @JsonIgnore
    private Integer httpStatusCode = SC_OK;

    private String exCode;
    private Integer resCode = 0;
    private ExasolResponseStatus resStatus = ExasolResponseStatus.SUCCESS;

    private String id;
    private T data;
    private String reqId;
    private String url;

    private String message;

    @JsonIgnore
    private Throwable exception;

    private Map<String, Object> meta;
    private Map<String, String> dictionary;

    public GenericRes() {
        super();
        reqId= UUID.randomUUID().toString();
    }

    public GenericRes(Throwable exception) {

        resCode = Integer.MAX_VALUE;
        if (exception == null) {
            return;
        }

        exCode = exception.getClass().getSimpleName();
        this.setException(exception);
        if (exception instanceof CustomRestException) {
            CustomRestException customRestException = (CustomRestException) exception;

            resCode = customRestException.getErrorCode();
            resStatus = ExasolResponseStatus.FAILURE;
            message = customRestException.getMessage();

            this.meta = customRestException.getMeta();
            this.dictionary = customRestException.getDictionary();
            this.httpStatusCode = customRestException.getHttpErrorCode();
        } else {
            resCode = CustomRestException.INTERNAL_ERROR_CODE;
            resStatus = ExasolResponseStatus.FAILURE;
            message = exception.getMessage();
        }
    }

    public GenericRes(T data) {
        this.data = data;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public Integer getResCode() {
        return resCode;
    }

    public void setResCode(Integer resCode) {
        this.resCode = resCode;
    }

    public ExasolResponseStatus getResStatus() {
        return resStatus;
    }

    public void setResStatus(ExasolResponseStatus resStatus) {
        this.resStatus = resStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
