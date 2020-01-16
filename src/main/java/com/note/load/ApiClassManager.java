package com.note.load;

import com.note.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * class 文件管理类
 */
@Slf4j
public class ApiClassManager {
    /**
     * 类名对应class map
     */
    private static Map<String, Long> classMap = new HashMap<>();


    public static void put(String name, Long lastModified) {
        classMap.put(name, lastModified);
    }

    public static Class<?> getClass(String apiName) {
        // todo 多线程加锁
        Class<?> clazz = null;
        String className = "com.note.apifile." + apiName;
        String classPath = FileUtil.getResourcePath("com/note/apifile/" + apiName + ".class");
        File classFile = new File(classPath);

        if (classMap.get(apiName) == null ||
                classFile.lastModified() > classMap.get(apiName)) {
            LoadAgent.reDefineClass(classFile, className);
            ApiObjectManager.removeSingleObject(apiName);
            put(apiName, classFile.lastModified());
        }

        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("类加载失败{}", e);
        }

        return clazz;
    }

}
