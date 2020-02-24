package com.llx.studio.service;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface LoginlogService {
    /**
     * 插入日志
     */
    JSONObject addLoginLog(JSONObject jsonObject);

    /**
     * 登入日志列表
     */
    JSONObject listLoginLog(JSONObject jsonObject);

    /**
     * 操作日志列表
     * @param jsonObject
     * @return
     */
    JSONObject listOperLog(JSONObject jsonObject);
}
