package com.llx.studio.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.RoleDao;
import com.llx.studio.service.MenuService;
import com.llx.studio.service.RoleService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.constants.Constants;
import com.llx.studio.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuService menuService;

    // #{roleName},#{rolePowe},#{roleOrder},#{roleStat},#{createTime},#{createPerson}
    @Override
    public JSONObject addRole(JSONObject jsonObject) {
        //校验用户是否存在
        int exist = roleDao.queryExistRoleName(jsonObject);
        if (exist > 0) {
            return CommonUtil.errorJson(ErrorEnum.E_10010);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("createPerson",userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("createTime",System.currentTimeMillis()/1000+"");
        jsonObject.put("roleStat",0);
        roleDao.addRole(jsonObject);
        return CommonUtil.successJson();
    }
    //roleName
    //rolePowe
    //roleOrder
    //roleStat
    //updatePerson
    //lastModifide
    //roleId
    @Override
    public JSONObject updataRole(JSONObject jsonObject){
        //获取用户信息
        JSONObject userInfi = (JSONObject)SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("updatePerson",userInfi.getString("userId"));
        jsonObject.put("lastModifide",(int)System.currentTimeMillis());
        roleDao.updataRole(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listRole(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int i = roleDao.countRole();
        List<JSONObject> userRole = roleDao.listRole(jsonObject);
        return CommonUtil.successPage(jsonObject,userRole,i,menuService.getBooten(jsonObject.getString("uri")));
    }

    @Override
    public JSONObject oneRole(JSONObject jsonObject) {
        JSONObject role = roleDao.oneRole(Integer.parseInt(jsonObject.getString("roleId")));
        return CommonUtil.successJson(role);
    }

    @Override
    public JSONObject delRole(JSONObject jsonObject) {
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("updatePerson",userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("lastModifide",System.currentTimeMillis()/1000+"");
        jsonObject.put("roleId",jsonObject.getString("roleId"));
        roleDao.delRole(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject addUserRole(JSONObject jsonObject) {
        //校验用户是否已经有该角色了
        int i = roleDao.IsNotUserRole(jsonObject);
        if (i > 0){
            return CommonUtil.errorJson(ErrorEnum.E_10011);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("createPerson",userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("createTime",System.currentTimeMillis()/1000+"");
        roleDao.addUserRole(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listUserRole(JSONObject jsonObject) {
        List<JSONObject> listUserRole;
        //userId存在校验
        if (jsonObject.containsKey("userId")){
            listUserRole = roleDao.listUserRole(Integer.parseInt(jsonObject.getString("userId")));
        }else {
            //获取Session的数据
            JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
            listUserRole = roleDao.listUserRole(Integer.parseInt(userSession.getString("userId")));
        }
        CommonUtil.fillPageParam(jsonObject);
        int i = roleDao.countRole();
        return CommonUtil.successPage(jsonObject,listUserRole,i,menuService.getBooten(jsonObject.getString("uri")));
    }


    @Override
    public JSONObject delUserRole(JSONObject jsonObject) {
        int i = roleDao.IsNotUserRole(jsonObject);
        if (i == 0){
            return CommonUtil.errorJson(ErrorEnum.E_10012);
        }
        roleDao.delUserRole(jsonObject);
        return CommonUtil.successJson();
}

    @Override
    public JSONObject setRoleStat(JSONObject jsonObject) {
        int i = roleDao.queryExistRoleId(Integer.parseInt(jsonObject.getString("roleId")));
        if (i == 0){
            return CommonUtil.errorJson(ErrorEnum.E_10013);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("updatePerson",userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("lastModifide",System.currentTimeMillis()/1000+"");
        roleDao.setRoleStat(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject setRolePermission(JSONObject jsonObject) {
        int i = roleDao.queryExistRoleId(Integer.parseInt(jsonObject.getString("roleId")));
        if (i == 0){
            return CommonUtil.errorJson(ErrorEnum.E_10013);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("updatePerson",userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("lastModifide",System.currentTimeMillis()/1000+"");
        roleDao.setRolePermission(jsonObject);
        return CommonUtil.successJson();
    }
}
