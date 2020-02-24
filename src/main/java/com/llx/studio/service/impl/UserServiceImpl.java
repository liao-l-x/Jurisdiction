package com.llx.studio.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.UserDao;
import com.llx.studio.service.MenuService;
import com.llx.studio.service.UserService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.constants.Constants;
import com.llx.studio.util.constants.ConstantsEncryption;
import com.llx.studio.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: hxy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuService menuService;


	/**
	 * 添加用户
	 */
	@Override
	public JSONObject addUser(JSONObject jsonObject) {
		//校验用户是否存在
		int exist = userDao.queryExistUsername(jsonObject);
		if (exist > 0) {
			return CommonUtil.errorJson(ErrorEnum.E_10009);
		}
		//当前时间戳+“”
		String salt = System.currentTimeMillis()+"";
		SimpleHash simpleHash = new SimpleHash(
				ConstantsEncryption.ENCRTPTION_NAME,
				jsonObject.getString("userPasswd"),
				salt,
				ConstantsEncryption.ITERATIONS);
		//加密的密码
		jsonObject.put("userPasswd",simpleHash.toHex());
		jsonObject.put("userEncryption",salt);
		userDao.addUser(jsonObject);
		JSONObject info = new JSONObject();
		info.put("result", "success");
		return CommonUtil.successJson(info);
	}

    /**
     * 修改用户基本信息
     * @param jsonObject
     * @return
     */
	public JSONObject updataUser(JSONObject jsonObject){
		//获取用户信息
		JSONObject userInfi = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		jsonObject.put("userId",userInfi.getString("userId"));
		jsonObject.put("lastModifide",(int)System.currentTimeMillis());
		userDao.updataUser(jsonObject);
		return CommonUtil.successJson();
	}

    /**
     *
     * @param    userId lastModifide
     * @return
     */
    public JSONObject DelUser(Integer userId){
        int l = (int)System.currentTimeMillis();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("lastModifide",l);
        userDao.DelUser(jsonObject);
        return CommonUtil.successJson();
    }

	@Override
	public JSONObject listUser(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);
		int i = userDao.countUser();
		List<JSONObject> userList = userDao.listUser(jsonObject);
		return CommonUtil.successPage(jsonObject,userList,i,menuService.getBooten(jsonObject.getString("uri")));

	}

	@Override
	public JSONObject oneUser(JSONObject jsonObject) {
		JSONObject userInfo = new JSONObject();
		if (jsonObject.containsKey("userId")){
			 userInfo = userDao.oneUser(Integer.parseInt(jsonObject.getString("userId")));
		}else {
			JSONObject userSession = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
			userInfo = userDao.oneUser(Integer.parseInt(userSession.getString("userId")));
		}
		return CommonUtil.successJson(userInfo);
	}
}
