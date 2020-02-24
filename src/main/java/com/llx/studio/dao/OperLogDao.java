package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface OperLogDao {

    //日志个数
    int  countOperLog();

    //添加操作日志
    //#{operDescription}, #{oper_method}, #{operName}, #{operUrl}, #{operIp}, #{operParam}, #{operStatus}, #{errorMsg},
    //         #{operTime}
    int addOperLog(JSONObject jsonObject);
    //操作日志列表
    List<JSONObject> listOperLog(JSONObject jsonObject);

}
