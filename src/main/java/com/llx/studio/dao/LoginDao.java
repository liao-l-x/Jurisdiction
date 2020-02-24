package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @author: hxy
 * @description: 登录相关dao
 * @date: 2017/10/24 11:02
 */
public interface LoginDao {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	JSONObject getUser(@Param("userName") String userName);

	/**
	 * 更新登入IP
	 * @param userLastIp
	 * @return
	 */
	int LogUser(JSONObject userLastIp);
}
