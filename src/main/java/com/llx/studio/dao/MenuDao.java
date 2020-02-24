package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MenuDao {

    //菜单个数
    int countMenu();
    //创建菜单
    //#{menuSuperior}, #{menuOrder}, #{menuName}, #{menuURL}, #{meunImg}, #{menuType}, #{createTime}, #{createPerson},
    // #{delFlag}, #{remark}
    int addMenu(JSONObject jsonObject);
    //修改菜单
    //menuSuperior
    //menuOrder
    //menuName
    //menuURL
    //meunImg
    //menuType
    //updatePerson
    //lastModifide
    //menuId
    int updataMenu(JSONObject jsonObject);
    //菜单列表信息
    List<JSONObject> listMenu();
    //菜单信息
    //menuId
    JSONObject oneMenu(Integer menuId);
    //根据角色获取菜单列表信息
    //roelId
    List<JSONObject> listRoleMenu(Integer roleId);
    //根据用户获取菜单列表信息
    //userId
    List<JSONObject> listUserMennu(Integer userId);
    //删除菜单
    //updatePerson
    //lastModifide
    //menuId
    int delMenu(JSONObject jsonObject);
    //为角色添加菜单
    //#{roleId},#{menuId},#{createTime},#{createPerson}
    int addRoleMenu(JSONObject jsonObject);
    //为角色删除菜单
    //menuId
    //roleId
    int delRoleMenu(JSONObject jsonObject);
    //判断菜单名是否存在
    //menuName
    int isNotMenuName(String menuName);
    //判断角色是否已经拥有该菜单
    //menuId
    //roleId
    int isNotRoleMenu(JSONObject jsonObject);

    //判断菜单id是否存在
    int isNotMenuId(Integer menuId);

    //判断菜单是否为叶菜单
    int isLeafMenu(Integer menuId);

    //获取菜单页面的按钮
    List<JSONObject> listMennuButton(Integer menuSuperior);

    //根据URL获取菜单id
    JSONObject URLmenuId(String URL);

    //角色的使用菜单id
    List<JSONObject> RoleMenuId(Integer roleId);
    //获取菜单的父菜单id
    int isMenuSuperior(Integer menuId);

}
