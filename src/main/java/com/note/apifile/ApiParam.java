package com.note.apifile;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ApiParam {
    /**
     * 状态
     */
    private Map<String, Object> stateMap = new HashMap<>();
    /**
     * api 信息
     */
    @Getter
    private ApiInfo apiInfo = new ApiInfo();

    /**
     * 获取状态
     * @param Key
     * @return
     */
    public Object getState(String Key) {
        return stateMap.get(Key);
    }

    @Data
    class ApiInfo {
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
    }
}
