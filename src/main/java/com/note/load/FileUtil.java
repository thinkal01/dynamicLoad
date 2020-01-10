package com.note.load;

import java.net.URL;

public class FileUtil {
    /**
     * java源码目录
     */
    private static final String SOURCE_DIR = "source/";

    // 获取资源目录
    public static String getResourcePath(String fileName) {
        URL resource = Object.class.getResource("/" + fileName);
        String path = resource.getPath();
        return path;
    }

    public static String getSourceFilePath(String fileName) {
        return getResourcePath(SOURCE_DIR + fileName);
    }
}