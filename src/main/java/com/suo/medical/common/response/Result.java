package com.suo.medical.common.response;

import com.suo.medical.common.enums.ResultCode;
import lombok.Data;

@Data
public class Result<T>{
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static <T> Result<T> error(){
        Result<T> result = new Result<>();
        result.setMessage(ResultCode.ERROR.getMessage());
        result.setCode(ResultCode.ERROR.getCode());
        return result;
    }

    public static <T> Result<T> error(ResultCode resultCode){
        Result<T> result = new Result<>();
        result.setMessage(resultCode.getMessage());
        result.setCode(resultCode.getCode());
        return result;
    }

}
