package com.capgemini.common.exception;

public class ApplicationCommonException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ApplicationCommonException(String message)
    {
	super(message);
    }

    public ApplicationCommonException(String message, Exception exception)
    {
	super(message, exception);
    }
}
