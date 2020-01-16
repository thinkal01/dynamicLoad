package com.note.service;

import com.note.vo.CommonResult;
import com.note.vo.ApiAddVO;

public interface ApiService {
    CommonResult add(ApiAddVO apiAddVO);

    CommonResult callApi(String apiName);
}
