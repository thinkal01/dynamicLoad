package com.note.load;

import com.note.apifile.ApiMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * api 对象管理类
 */
@Slf4j
public class ApiObjectManager {
    /**
     * api对象缓存map
     */
    private static Map<String, ApiMethod> apiObjectMap = new HashMap<>();

    /**
     * 获取单例对象
     * @param apiName
     * @return
     */
    public static ApiMethod getSingleObject(String apiName) {
        try {
            // todo 多线程加锁
            ApiMethod apiMethod = apiObjectMap.get(apiName);
            if (apiMethod != null) {
                return apiMethod;
            }
            Class<?> clazz = ApiClassManager.getClass(apiName);
            // 把class文件生成到当前工程目录下的classes目录,所以classloader是可以加载到的
            // 使用的是AppClassLoader,sun.misc.Launcher$AppClassLoader
            apiMethod = (ApiMethod) clazz.newInstance();
            // 加入缓存
            // apiObjectMap.put(apiName, apiMethod);
            return apiMethod;
        } catch (Exception e) {
            log.error("反射创建对象失败:{}", e);
        }

        // Method method = clz.getDeclaredMethod("sayHello");//返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
        // String result = (String) method.invoke(o);//静态方法第一个参数可为null,第二个参数为实际传参
        return null;
    }

    public static void removeSingleObject(String apiName) {
        apiObjectMap.remove(apiName);
    }
}
