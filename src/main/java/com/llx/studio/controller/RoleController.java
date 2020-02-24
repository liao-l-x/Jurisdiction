package com.llx.studio.controller;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.config.log.SystemControllerLog;
import com.llx.studio.service.RoleService;
import com.llx.studio.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     *roleName, 角色名称
     * rolePowe,角色权限
     * roleOrder角色显示顺序
     */
    @SystemControllerLog(description = "添加角色")
    @PostMapping("/addRole")
    public JSONObject addUser(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleName, rolePowe, roleOrder");
        return roleService.addRole(requestJson);
    }

    @PostMapping("/updataRole")
    @SystemControllerLog(description = "修改角色信息")
    public JSONObject updataRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId");
        return roleService.updataRole(requestJson);
    }

    /**
     *角色列表信息
     */
    @PostMapping("/listRole")
    public JSONObject listUser(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        return roleService.listRole(requestJson);
    }

    /**
     *角色信息
     */
    @SystemControllerLog(description = "角色信息")
    @PostMapping("/oneRole")
    public JSONObject oneRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId");
        return roleService.oneRole(requestJson);
    }

    /**
     *删除角色信息
     */
    @SystemControllerLog(description = "删除角色")
    @PostMapping("/delRole")
    public JSONObject delRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId");
        return roleService.delRole(requestJson);
    }

    /**
     *添加用户角色信息
     */
    @SystemControllerLog(description = "为用户添加角色")
    @PostMapping("/addUserRole")
    public JSONObject addUserRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId, userId");
        return roleService.addUserRole(requestJson);
    }

    /**
     *用户角色信息
     */
    @PostMapping("/listUserRole")
    public JSONObject listUserRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        return roleService.listUserRole(requestJson);
    }

    /**
     *删除用户角色
     */
    @SystemControllerLog(description = "为用户删除角色")
    @PostMapping("/delUserRole")
    public JSONObject delUserRole(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId, userId");
        return roleService.delUserRole(requestJson);
    }

    /**
     *修改角色状态
     */
    @SystemControllerLog(description = "修改角色状态")
    @PostMapping("/setRoleStat")
    public JSONObject setRoleStat(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId, roleStat");
        return roleService.setRoleStat(requestJson);
    }

    /**
     *修改角色权限
     */
    @SystemControllerLog(description = "修改角色权限")
    @PostMapping("/setRolePermission")
    public JSONObject setRolePermission(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson, "roleId, rolePowe");
        return roleService.setRolePermission(requestJson);
    }

}
