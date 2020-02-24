package com.llx.studio.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: 用户/角色/权限
 */
public interface UserService {

	/**
	 * 添加用户
	 */
	JSONObject addUser(JSONObject jsonObject);

	/**
	 * 修改用户信息
	 */
	JSONObject updataUser(JSONObject jsonObject);
	/**
	 * 删除用户
	 */
	JSONObject DelUser(Integer userId);

	JSONObject listUser(JSONObject jsonObject);

	JSONObject oneUser(JSONObject jsonObject);
}
