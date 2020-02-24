package com.llx.studio.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.LoginDao;
import com.llx.studio.service.LoginService;
import com.llx.studio.service.LoginlogService;
import com.llx.studio.service.UserService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.constants.Constants;
import com.llx.studio.util.constants.ConstantsEncryption;
import com.llx.studio.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * @description: 登录service实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private LoginlogService loginlogService;


	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("userName");
		String password = jsonObject.getString("passWd");
		JSONObject user = loginDao.getUser(username);
		//判断用户是否存在
		if(null == user || user.size() == 0 ){
			return CommonUtil.errorJson(ErrorEnum.E_20051);
		}
		SimpleHash simpleHash = new SimpleHash(//加密
				ConstantsEncryption.ENCRTPTION_NAME,
				password,
				user.getString("userEncryption"),
				ConstantsEncryption.ITERATIONS);
		final String s = simpleHash.toHex();
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, s);
		//日志信息
		// (user_id,user_type, user_ip, user_time,user_stat)
		JSONObject logInfo = new JSONObject();
		logInfo.put("userId",user.getString("userId"));
		logInfo.put("userType",user.getString("userType"));
		logInfo.put("userIp",jsonObject.getString("userIp"));
		logInfo.put("userTime",(int)System.currentTimeMillis());
		try {
			currentUser.login(token);
			Serializable id = currentUser.getSession().getId();
			info.put("token", id);
			info.put("result", "success");
			logInfo.put("userStat",1);
			loginlogService.addLoginLog(logInfo);
		} catch (AuthenticationException e) {
			info.put("result", "fail");
			logInfo.put("userStat",0);
			loginlogService.addLoginLog(logInfo);
		}
		//用户表里面的记录 更新IP地址
		JSONObject logUser = new JSONObject();
		logUser.put("userLastIp",jsonObject.getString("userIp"));
		logUser.put("lastModifide",(int)System.currentTimeMillis());
		loginDao.LogUser(logUser);
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名查询对应的用户
	 */
	@Override
	public JSONObject getUser(String username) {
		return loginDao.getUser(username);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getString("userName");
		JSONObject info = new JSONObject();
		/*JSONObject userPermission = permissionService.getUserPermission(username);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);*/
		info.put("userPermission", "");
		info.put("username", username);
		return CommonUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return CommonUtil.successJson();
	}
}
