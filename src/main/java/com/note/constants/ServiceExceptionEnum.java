package com.note.constants;

/**
 * 业务异常枚举
 */
public enum ServiceExceptionEnum {

    // ========== 系统级别 ==========
    SUCCESS(0, "成功"),
    SYS_ERROR(2001001000, "服务端发生异常"),
    MISSING_REQUEST_PARAM_ERROR(2001001001, "参数缺失"),

    // ========== 模块 ==========
    USER_NOT_FOUND(1001002000, "用户不存在"),

    // ========== 模块2 ==========

    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误提示
     */
    private final String message;

    ServiceExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
