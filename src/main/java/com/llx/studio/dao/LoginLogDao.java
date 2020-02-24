package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface LoginLogDao {
    /**
     * 查询日志数量
     */
    int countLoginLog();
    /**
     * 新增日志
     */
    int addLoginLog(JSONObject jsonObject);
    /**
     * 所有日志
     */
    List<JSONObject> listLoginLog(JSONObject jsonObject);
}
