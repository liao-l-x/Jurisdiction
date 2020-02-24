package com.llx.studio.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

public interface RoleDao {

    //角色个数
    int countRole();

    //用户角色个数
    //userId
    int countUserRole();

    //用户是否有该角色
    //userId roleId
    int IsNotUserRole(JSONObject jsonObject);

    //校验角色是否存在
    //roleName
    int queryExistRoleName(JSONObject jsonObject);

    //校验角色是否存在
    //roleId
    int queryExistRoleId(Integer roleId);

    //创建角色
    //#{roleName},#{rolePowe},#{roleOrder},#{roleStat},#{createTime},#{createPerson}
    int addRole(JSONObject jsonObject);

    //修改角色
    //roleName
    //rolePowe
    //roleOrder
    //roleStat
    //updatePerson
    //lastModifide
    //roleId
    int updataRole(JSONObject jsonObject);

    //角色列表信息
    List<JSONObject> listRole(JSONObject jsonObject);

    //角色信息
    //roleId
    JSONObject oneRole(Integer roleId);

    //删除角色
    //lastModifide
    //updatePerson
    //roleId
    int delRole(JSONObject jsonObject);

    //为用户添加角色
    //#{userId},#{roleId},#{createTime},#{createPerson}
    int addUserRole(JSONObject jsonObject);

    //用户的角色
    //userId
    List<JSONObject> listUserRole(Integer roleId);

    //用户角色的权限
    //userId
    Set<String> listUserRolePermission(Integer roleId);

    //删除用户角色
    //roleId
    int delUserRole(JSONObject jsonObject);

    //修改角色状态
    //lastModifide
    //roleId
    int setRoleStat(JSONObject jsonObject);

    //修改角色权限
    //rolePowe
    //lastModifide
    //roleId
    int setRolePermission(JSONObject jsonObject);

}
