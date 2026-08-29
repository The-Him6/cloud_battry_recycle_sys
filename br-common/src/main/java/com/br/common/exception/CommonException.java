package com.br.common.exception;

import lombok.Getter;

/**
 * 业务异常类
 */
@Getter
public class CommonException extends RuntimeException {
    
    private int code;
    
    public CommonException(String message) {
        super(message);
        this.code = code;
    }
    
    public CommonException( String message, int code) {
        super(message);
        this.code = code;
    }
}

