package com.br.common.advice;

import com.br.common.domain.Result;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.CommonException;
import com.br.common.exception.DbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

import static com.br.common.constants.SystemConstants.SYSTEM_ERROR;

@RestControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    /**
     * 数据库异常
     */
    @ExceptionHandler(DbException.class)
    public Result<Void> handleDbException(DbException e) {
        log.error("数据库异常 -> ", e);
        return Result.error(e);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CommonException.class)
    public Result<Void> handleCommonException(CommonException e) {
        log.error("业务异常 -> {}", e.getMessage());
        return Result.error(e);
    }

    /**
     * 参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("|"));
        return Result.error(new BadRequestException(msg));
    }

    /**
     * 所有漏网的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常 -> ", e);
        return Result.error(500, SYSTEM_ERROR);
    }
}