package com.gopal.url.api.exception;

import com.gopal.url.api.common.GenericRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public abstract class ControllerException {

    private static String SERVICE_EXCEPTION = "SERVICE_EXCEPTION";
    protected Logger logger = LoggerFactory.getLogger(super.getClass());

    public ControllerException() {
        super();
    }

    @ExceptionHandler(CustomRestException.class)
    @ResponseBody
    private GenericRes<Void> handleApplicationException(CustomRestException ex, HttpServletRequest request,
														HttpServletResponse response) {
        logger.error("f(app.ex)=[true], f(cause)=[" + ex.getMessage() + "]", ex);

        request.setAttribute(SERVICE_EXCEPTION, ex);
        GenericRes<Void> genericRes = new GenericRes<Void>(ex);
        response.setStatus(genericRes.getHttpStatusCode());
        return genericRes;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private GenericRes<Void> handleMethodArgException(MethodArgumentNotValidException ex, HttpServletRequest request,
                                                      HttpServletResponse response) {
        logger.error("f(http.method.ex)=[true], f(cause)=[" + ex.getMessage() + "]", ex);

        GenericRes<Void> genericRes = new GenericRes<Void>(ex);
        genericRes.setHttpStatusCode(SC_BAD_REQUEST);
        response.setStatus(SC_BAD_REQUEST);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        if (fieldErrors != null && fieldErrors.size() > 0) {
            String message = "Validation failed for following argument ";
            for (FieldError fieldError : fieldErrors) {
                message += fieldError.getField() + " : " + fieldError.getDefaultMessage() + ",";
            }
            message = message.substring(0, message.length() - 1);
            genericRes.setMessage(message);
        }
        request.setAttribute(SERVICE_EXCEPTION, ex);

        return genericRes;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    private GenericRes<Void> handleException(Throwable ex, HttpServletRequest request, HttpServletResponse response) {
        logger.error("f(ex)=[true], f(cause)=[" + ex.getMessage() + "]", ex);

        request.setAttribute(SERVICE_EXCEPTION, ex);
        GenericRes<Void> genericRes = new GenericRes<Void>(ex);
        response.setStatus(genericRes.getHttpStatusCode());
        return genericRes;
    }

}
