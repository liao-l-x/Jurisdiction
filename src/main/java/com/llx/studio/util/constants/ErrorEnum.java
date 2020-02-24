package com.llx.studio.util.constants;

/**
 * 错误信息
 */
public enum ErrorEnum {
	/*
	 * 错误信息
	 * */
	E_400("400", "请求处理异常，请稍后再试"),
	E_500("500", "请求方式有误,请检查 GET/POST"),
	E_501("501", "请求路径不存在"),
	E_502("502", "权限不足"),
	E_10008("10008", "角色删除失败,尚有用户属于此角色"),
	E_10009("10009", "账户已存在"),
	E_10010("10010", "角色已存在"),
	E_10011("10011", "该用户已经拥有这个角色"),
	E_10012("10011", "该用户没有拥有这个角色"),
	E_10013("10013", "没有这个角色"),

	E_10020("10020", "菜单已存在"),
	E_10021("10021", "角色已经拥有该菜单"),
	E_10022("10022", "角色没有拥有该菜单"),
	E_10023("10023", "菜单不存在"),
	E_10024("10024", "菜单下有子菜单"),
	E_10025("10025", "该角色没有子菜单的父菜单"),

	E_20011("20011", "登陆已过期,请重新登陆"),
	E_20051("20051", "用户名错误，请检查用户名输入"),

	E_90003("90003", "缺少必填参数");

	private String errorCode;

	private String errorMsg;

	ErrorEnum(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}