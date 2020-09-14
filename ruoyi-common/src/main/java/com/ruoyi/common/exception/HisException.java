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

    protected int code;


    public HisException(String message)
    {
        this.code = -1;
        this.message = message;
    }
    public HisException(int code,String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
