package com.note.load;

import com.note.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@Configurable
@EnableScheduling
@Slf4j
public class CompilerJob {
    // private static boolean isExecute = false;

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
            // isExecute = true;

            compilerAndRun();
        } catch (Exception e) {
            log.error("test", e);
        }
    }

    public void compilerAndRun() {
        try {
            // 获取java源文件
            List<File> fileList = getUpdateFileList(new File(FileUtil.getSourceFilePath("")), new ArrayList<>());
            compilerJavaFile(fileList);
        } catch (Exception e) {
            log.error("编译java源文件异常", e);
        }
    }


    /**
     * 编译java源文件
     * @param fileList
     */
    private void compilerJavaFile(List<File> fileList) {
        for (File file : fileList) {
            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d",
                    System.getProperty("user.dir") + "\\target\\classes",
                    FileUtil.getSourceFilePath(file.getName()));

            if (status != 0) {
                log.error("编译失败!");
            }
        }
    }

    /**
     * 查找java源文件
     * @param dir
     * @param fileList
     * @return
     */
    public static List<File> getUpdateFileList(File dir, List<File> fileList) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir + " is not a directory.");
        }

        File[] fileArr = dir.listFiles();

        for (File file : fileArr) {
            if (file.isDirectory()) {
                getUpdateFileList(file, fileList);
            } else {
                if (!file.getName().endsWith(".java")) {
                    continue;
                }

                if (ApiSourceManage.lastModified(file.getName()) == null ||
                        file.lastModified() > ApiSourceManage.lastModified(file.getName())) {
                    // 文件发生更新时,重新编译
                    fileList.add(file);
                    // 更新时间
                    ApiSourceManage.put(file.getName(), file.lastModified());
                }
            }
        }

        return fileList;
    }
}