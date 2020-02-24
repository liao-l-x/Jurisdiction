package com.llx.studio.controller;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.service.LoginlogService;
import com.llx.studio.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LoginlogService loginlogService;
    /**
     * 查看登入日志列表
     */
    @PostMapping("/login")
    public JSONObject listLoginLog(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        JSONObject jsonObject = loginlogService.listLoginLog(requestJson);
        return jsonObject;
    }

    /**
     * 查看操作日志
     * @param request
     * @return
     */
    @PostMapping("/oper")
    public JSONObject listLogoper(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        JSONObject jsonObject = loginlogService.listOperLog(requestJson);
        return jsonObject;
    }
}
