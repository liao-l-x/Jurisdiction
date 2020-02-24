package com.llx.studio.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MenuService {

    //添加菜单
    JSONObject addMenu(JSONObject jsonObject);
    //菜单列表信息
    JSONObject listMenu(JSONObject jsonObject);
    //菜单信息
    JSONObject oneMenu(JSONObject jsonObject);
    //修改菜单
    JSONObject updataMenu(JSONObject jsonObject);
    //删除菜单
    JSONObject delMenu(JSONObject jsonObject);
    //用户的菜单列表信息
    JSONObject listUserMenu(JSONObject jsonObject);
    //角色的菜单列表信息
    JSONObject listRoleMenu(JSONObject jsonObject);
    //为角色添加菜单
    JSONObject addRoleMenu(JSONObject jsonObject);
    //为角色删除菜单
    JSONObject delRoleMenu(JSONObject jsonObject);
    //获取菜单按钮
    List<JSONObject> getBooten(String uri);
}
