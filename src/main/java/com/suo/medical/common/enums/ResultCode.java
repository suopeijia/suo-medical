package com.suo.medical.common.enums;

import com.suo.medical.common.response.Result;
import lombok.Getter;


@Getter
public enum ResultCode {
    //返回值枚举常量
    SUCCESS(200, "操作成功"),
    ERROR(500, "操作失败"),
    PATIENT_NOT_FOUND(1001, "患者不存在"),
    PARAM_ERROR(400,"参数错误"),
    DEPARTMENT_NOT_EXIST(400,"科室不存在" ),
    DEPARTMENT_EXIST(400,"科室已经存在" );


    private final Integer code;
    private final String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
