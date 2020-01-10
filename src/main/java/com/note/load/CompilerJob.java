package com.note.load;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Method;

// @Component
@Configurable
@EnableScheduling
@Slf4j
public class CompilerJob {
    private static boolean isExecute = false;

    /**
     * 加载class
     */
    @Scheduled(cron = "*/5 * * * * * ")
    public void loadClass() {
        try {
            /*if (isExecute) {
                return;
            }*/
            //只是测试，所以只执行一次
            isExecute = true;

            compilerAndRun();
        } catch (Exception e) {
            log.error("test", e);
        }
    }

    public void compilerAndRun() {
        try {
            getUpdateFileList(new File(FileUtil.getSourceFilePath("")));

            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d",
                    System.getProperty("user.dir") + "\\target\\classes",
                    FileUtil.getSourceFilePath("Test.java"));

            if (status != 0) {
                log.error("编译失败!");
            }

            //动态执行
            //返回与带有给定字符串名的类 或接口相关联的 Class 对象。
            String dynamicMethodClassName = "Test";
            Class clz = Class.forName(dynamicMethodClassName);
            // 把class文件生成到当前工程目录下的classes目录,所以classloader是可以加载到的
            // 是哪个类加载器：sun.misc.Launcher$AppClassLoader,使用的是AppClassLoader
            System.out.println(clz.getClassLoader().getSystemClassLoader());

            Object o = clz.newInstance();
            Method method = clz.getDeclaredMethod("sayHello");//返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
            String result = (String) method.invoke(o);//静态方法第一个参数可为null,第二个参数为实际传参
            System.out.println(result);
        } catch (Exception e) {
            log.error("test", e);
        }
    }

    public static boolean getUpdateFileList(File dir) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir + " is not a directory. ");
        }

        File[] fileArr = dir.listFiles();

        for (int i = 0; i < fileArr.length; i++) {
            if (fileArr[i].isDirectory()) {
                if (!getUpdateFileList(fileArr[i])) {
                    return false;
                }
            } else {
                if (!fileArr[i].delete()) {
                    return false;
                }
            }
        }

        if (!dir.delete()) {
            return false;
        }
        return true;
    }
}