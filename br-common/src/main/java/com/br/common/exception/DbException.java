package com.br.common.exception;


/**
 * 数据异常类
 */
public class DbException extends CommonException {

    public DbException(String message) {
        super(message, 500);

    }

}

