package com.note.vo;

import lombok.Data;

/**
 * ApiAddVO
 */
@Data
public class ApiAddVO {
    /**
     * url地址
     */
    private String url;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * wsCode
     */
    private String wsCode;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 调用类型
     */
    private String callType;
    /**
     * 接口类名
     */
    // private String className;
    /**
     * 接口import
     */
    // private String importClass;
    /**
     * 接口代码
     */
    private String apiCode;

}
