package com.llx.studio.controller;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.config.log.SystemControllerLog;
import com.llx.studio.service.UserService;
import com.llx.studio.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: hxy
 * @description: 用户/角色/权限相关controller
 * @date: 2017/11/2 10:19
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;


	/**
	 * 添加用户
	 * @param request 必须有用户名和密码
	 * @return
	 */
	@SystemControllerLog(description = "添加用户")
	@PostMapping("/addUser")
	public JSONObject addUser(HttpServletRequest request) {
		JSONObject requestJson = CommonUtil.request2Json(request);
		CommonUtil.hasAllRequired(requestJson, "userName, userPasswd, userPhone");
		return userService.addUser(requestJson);
	}

	/**
	 * 修改用户基本信息
	 * @param request
	 * userFullName 姓名
	 * userMail 电子邮箱
	 * userGender 用户性别（0男 1女 2未知）
	 * userSchool 学校
	 * userSchoolLocation 学校系班级
	 * userSchoolIdentity 在校身份(0学生 1老师 2教授）
	 * userSchoolStudentId 学号
	 * userAdder 个人地址
	 * userIntroduce 个人介绍
	 *
	 * "userFullName, userMail, userSchool" 必填
	 * @return
	 */
	@SystemControllerLog(description = "修改用户")
	@PostMapping("/updataUser")
	public JSONObject updataUser(HttpServletRequest request){
		JSONObject requestJson = CommonUtil.request2Json(request);
		CommonUtil.hasAllRequired(requestJson, "userFullName, userMail, userSchool");
		return userService.updataUser(requestJson);
	}

	/**
	 * userId 用户id
	  */
	@SystemControllerLog(description = "删除用户")
	@PostMapping("/delUser")
	public JSONObject delUser(HttpServletRequest request){
		JSONObject requestJson = CommonUtil.request2Json(request);
		return userService.DelUser(Integer.parseInt(requestJson.getString("userId")));
	}

	/**
	 * 用户列表
	 */
	@PostMapping("/listUser")
	public JSONObject listUser(HttpServletRequest request){
		JSONObject requestJson = CommonUtil.request2Json(request);
		return userService.listUser(requestJson);
	}

	/**
	 * 用户信息
	 * 有userId 就是查该id的，没有就是查自己的
	 */
	@PostMapping("/oneUser")
	public JSONObject oneUser(HttpServletRequest request){
		JSONObject requestJson = CommonUtil.request2Json(request);
		return userService.oneUser(requestJson);
	}

}
