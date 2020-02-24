package com.llx.studio.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.LoginLogDao;
import com.llx.studio.dao.MenuDao;
import com.llx.studio.dao.OperLogDao;
import com.llx.studio.service.LoginlogService;
import com.llx.studio.service.MenuService;
import com.llx.studio.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LoginlogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Autowired
    private OperLogDao operLogDao;
    @Autowired
    private MenuService menuService;

    @Override
    public JSONObject addLoginLog(JSONObject jsonObject) {
        return CommonUtil.successJson(loginLogDao.addLoginLog(jsonObject));
    }

    @Override
    public JSONObject listLoginLog(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int i = loginLogDao.countLoginLog();
        List<JSONObject> logList = loginLogDao.listLoginLog(jsonObject);
        return CommonUtil.successPage(jsonObject,logList,i,menuService.getBooten(jsonObject.getString("uri")));
    }

    @Override
    public JSONObject listOperLog(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int i = operLogDao.countOperLog();
        List<JSONObject> listOperLog = operLogDao.listOperLog(jsonObject);
        return CommonUtil.successPage(jsonObject,listOperLog,i,menuService.getBooten(jsonObject.getString("uri")));
    }
}
