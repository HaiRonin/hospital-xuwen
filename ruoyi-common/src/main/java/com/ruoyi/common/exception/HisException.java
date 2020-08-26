package com.ruoyi.common.exception;

/**
 * His业务异常
 * 
 * @author ruoyi
 */
public class HisException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public HisException(String message)
    {
        this.message = message;
    }

    public HisException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
