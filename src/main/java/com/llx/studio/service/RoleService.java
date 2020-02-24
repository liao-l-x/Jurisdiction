package com.llx.studio.service;

import com.alibaba.fastjson.JSONObject;

public interface RoleService {

    //添加角色
    JSONObject addRole(JSONObject jsonObject);
    //修改角色
    JSONObject updataRole(JSONObject jsonObject);
    //角色列表信息
    JSONObject listRole(JSONObject jsonObject);
    //角色信息
    JSONObject oneRole(JSONObject jsonObject);
    //删除角色
    JSONObject delRole(JSONObject jsonObject);
    //为用户添加角色
    JSONObject addUserRole(JSONObject jsonObject);
    //用户的角色
    JSONObject listUserRole(JSONObject jsonObject);
    //用户角色的权限
    //JSONObject listUserRolePermission(JSONObject jsonObject);
    //删除用户角色
    JSONObject delUserRole(JSONObject jsonObject);
    //修改角色状态
    JSONObject setRoleStat(JSONObject jsonObject);
    //修改角色权限
    JSONObject setRolePermission(JSONObject jsonObject);
}
