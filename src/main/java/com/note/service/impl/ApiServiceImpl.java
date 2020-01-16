package com.note.service.impl;

import com.note.apifile.ApiMethod;
import com.note.apifile.ApiParam;
import com.note.load.ApiObjectManager;
import com.note.load.ApiSourceManage;
import com.note.service.ApiService;
import com.note.util.FileUtil;
import com.note.vo.ApiAddVO;
import com.note.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("apiService")
@Slf4j
public class ApiServiceImpl implements ApiService {
    @Override
    public CommonResult add(ApiAddVO apiAddVO) {
        String apiCode = apiAddVO.getApiCode();
        if (StringUtils.isEmpty(apiCode)) {
            return CommonResult.error(400, "apiCode为空");
        }

        // 获取类名
        String className = getClassName(apiCode);
        if (StringUtils.isEmpty(className)) {
            return CommonResult.error(400, "apiCode 不包含public class类声明语句");
        }

        try {
            String fileName = className + ".java";
            String filePath = FileUtil.getSourcePath() + fileName;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            // 保存api代码到硬盘
            FileCopyUtils.copy(apiCode, bufferedWriter);
            // todo 编译校验
        } catch (Exception e) {
            log.error("保存失败{}", e);
            return CommonResult.error(500, "保存失败");
        }

        return CommonResult.success("成功");
    }


    @Override
    public CommonResult callApi(String apiName) {
        ApiMethod apiMethod = ApiObjectManager.getSingleObject(apiName);
        return apiMethod.invoke(new ApiParam());
    }

    /**
     * 采用正则获取类名
     * @param apiCode
     * @return
     */
    private String getClassName(String apiCode) {
        String regex = "public\\s+class\\s+(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(apiCode);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }
}
