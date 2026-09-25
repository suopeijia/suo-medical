package com.suo.medical.common.exception;
import com.suo.medical.common.enums.ResultCode;
import com.suo.medical.common.response.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//让DispatcherServlet拦截所有的异常
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getResultCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidation(MethodArgumentNotValidException e){
        String validateMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(400,validateMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handleConstraint(ConstraintViolationException e){
        return Result.error(400,e.getMessage());
    }

    /**
     * 类上面的@RestControllerAdvice注解让DispatcherServlet知道让这个异常处理器来处理异常
     * 方法上的@ExceptionHandler注解告诉这个异常处理器，这个方法用来处理什么类型的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("系统异常", e);
        return Result.error();
    }
}
