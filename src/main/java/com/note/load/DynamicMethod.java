package com.note.load;

import com.note.vo.CommonResult;

public interface DynamicMethod<T> {
    CommonResult<T> invoke(DynamicParam dynamicParam);
}
