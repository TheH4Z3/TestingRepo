package com.restful.booker.exceptions;

public class ServiceException extends AssertionError {

    private static final long serialVersionUID = 1L;

    public static final String STATUS_CODE_IS_NOT_EXPECTED = "The status code service response is not expected";
    public static final String VALUE_SERVICE_IS_NOT_EXPECTED = "The values in the response do not match";
    public static final String FIELD_SERVICE_IS_NOT_EXPECTED = "The fields in the response do not match";

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}
