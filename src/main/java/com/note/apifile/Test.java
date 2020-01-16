package com.note.apifile;

import com.note.util.HttpUtil;
import com.note.vo.CommonResult;

import java.util.HashMap;

public class Test implements ApiMethod {

    @Override
    public CommonResult invoke(ApiParam apiParam) {
        String cardNo = (String) apiParam.getState("cardNo");
        ApiParam.ApiInfo apiInfo = apiParam.getApiInfo();
        String url = apiInfo.getUrl();
        // 调用接口
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("card", cardNo);
        String result = HttpUtil.doPost(url, paramMap, String.class);
        return CommonResult.success(result);
    }
}