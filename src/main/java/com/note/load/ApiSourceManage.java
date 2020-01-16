package com.note.load;

import java.util.HashMap;
import java.util.Map;

/**
 * api 源文件管理类
 */
public class ApiSourceManage {
    private static Map<String, Long> fileMap = new HashMap<>();

    public static void put(String fileName, long lastModified) {
        fileMap.put(fileName, lastModified);
    }

    public static Long lastModified(String fileName) {
        return fileMap.get(fileName);
    }
}
