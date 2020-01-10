package com.note.controller;

import com.note.service.ApiService;
import com.note.vo.ApiAddVO;
import com.note.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * api Controller
 */
@RestController
@RequestMapping("/api")
//@CrossOrigin(value = "*")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 添加api接口
     *
     * @param apiAddVO 添加api信息
     * @return 添加成功的用户编号
     */
    @PostMapping("add")
    public CommonResult add(ApiAddVO apiAddVO) {
        return apiService.add(apiAddVO);
    }

}
