package com.note.load;

import com.note.apifile.ApiMethod;
import com.note.apifile.ApiParam;
import com.note.vo.CommonResult;
import lombok.Data;

public class TestDynamicMethod01 implements ApiMethod<TestDynamicMethodEntity01> {

    @Override
    public CommonResult<TestDynamicMethodEntity01> invoke(ApiParam dynamicParam) {
        /**
         * 调用接口
         */
        TestDynamicMethodEntity01 data = new TestDynamicMethodEntity01();
        data.setName("");
        return CommonResult.success(data);
    }
}

@Data
class TestDynamicMethodEntity01 {
    private String name;
}
