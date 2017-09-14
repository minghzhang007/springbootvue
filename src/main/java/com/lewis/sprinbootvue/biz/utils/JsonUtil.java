package com.lewis.sprinbootvue.biz.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtil {

    public static String toString(Object obj) {
        if (obj != null) {
            return JSON.toJSONString(obj);
        }
        return "";
    }
}
