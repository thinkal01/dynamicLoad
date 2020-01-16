package com.note.apifile;

import com.note.vo.CommonResult;

public interface ApiMethod<T> {
    CommonResult<T> invoke(ApiParam apiParam);
}
