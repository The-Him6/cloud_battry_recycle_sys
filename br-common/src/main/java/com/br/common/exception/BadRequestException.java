package com.br.common.exception;

/**
 * 参数校验异常
 */
public class BadRequestException extends CommonException {

    public BadRequestException(String message) {
        super(message, 400);
    }
}