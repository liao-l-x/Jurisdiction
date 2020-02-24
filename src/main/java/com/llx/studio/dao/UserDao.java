package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 用户/角色/权限
 */
public interface UserDao {
	/**
	 * 查询用户数量
	 */
	int countUser();

	/**
	 * 校验用户名是否已存在
	 */
	int queryExistUsername(JSONObject jsonObject);

	/**
	 * 新增用户
	 */
	int addUser(JSONObject jsonObject);

	/**
	 * 修改用户信息
	 * @param jsonObject 用户信息
	 * @return
	 */
	int updataUser(JSONObject jsonObject);

	/**
	 * 删除用户
	 */
	int DelUser(JSONObject jsonObject);

	/**
	 * 用户列表
	 * @return
	 */
	List<JSONObject> listUser(JSONObject jsonObject);

	/**
	 * 用户信息
	 * @param userId
	 * @return
	 */
	JSONObject oneUser(Integer userId);
}
