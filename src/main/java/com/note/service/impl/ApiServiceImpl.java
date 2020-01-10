package com.note.service.impl;

import com.note.vo.CommonResult;
import com.note.load.FileUtil;
import com.note.service.ApiService;
import com.note.vo.ApiAddVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
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
            // 保存api代码到硬盘
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FileUtil.getSourceFilePath(className)));
            FileCopyUtils.copy(apiCode, bufferedWriter);
            // todo 编译校验
        } catch (Exception e) {
            return CommonResult.error(500, "保存失败");
        }

        return CommonResult.success("成功");
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
