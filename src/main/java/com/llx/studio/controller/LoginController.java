package com.llx.studio.controller;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.service.LoginService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.GetIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 登录相关Controller
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 */
	@PostMapping("/auth")
	public JSONObject authLogin(HttpServletRequest request) {
		JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "userName,passWd");
		String remoteAddr = GetIp.getRemoteIpByServletRequest(request,true);
		requestJson.put("userIp",remoteAddr);
		return loginService.authLogin(requestJson);
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@PostMapping("/getInfo")
	public JSONObject getInfo() {
		return loginService.getInfo();
	}

	/**
	 * 登出
	 */
	@PostMapping("/logout")
	public JSONObject logout() {
		return loginService.logout();
	}
}
