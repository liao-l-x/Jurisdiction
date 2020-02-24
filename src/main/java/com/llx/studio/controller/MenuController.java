package com.llx.studio.controller;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.config.log.SystemControllerLog;
import com.llx.studio.service.MenuService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //menuSuperior,  父菜单
    // menuOrder,  显示顺序
    // menuName, 菜单名称
    // menuURL, 菜单url
    // menuType 菜单类型
    //添加菜单
    //meunImg 菜单图标 可填可不填
    @SystemControllerLog(description = "添加菜单")
    @PostMapping("/addMenu")
    public JSONObject addMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        //判断菜单类型，目录就不要URl
        if(requestJson.getString("menuType").equals("0")){
            CommonUtil.hasAllRequired(requestJson, "menuSuperior, menuOrder, menuName, menuType");
            return menuService.addMenu(requestJson);
        }
        CommonUtil.hasAllRequired(requestJson, "menuSuperior, menuOrder, menuName, menuURL, menuType");
        return menuService.addMenu(requestJson);
    }

    @PostMapping("/listMenu")
    public JSONObject listMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        return menuService.listMenu(requestJson);
    }

    @PostMapping("/oneMenu")
    public JSONObject oneMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"menuId");
        return menuService.oneMenu(requestJson);
    }

    /**
     menuSuperior
     menuOrder
     menuName
     menuURL
     meunImg
     menuType
     * @param request
     * @return
     */
    @SystemControllerLog(description = "修改菜单信息")
    @PostMapping("/updataMenu")
    public JSONObject updataMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"menuId");
        return menuService.updataMenu(requestJson);
    }

    @SystemControllerLog(description = "删除菜单")
    @PostMapping("/delMenu")
    public JSONObject delMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"menuId");
        return menuService.delMenu(requestJson);
}

    /**
     * 根据用户id 获取菜单列表
     * @param request
     * @return 传userId 就是查看该用户的菜单列表
     *          没有userId 就是自己的菜单用户
     */
    @PostMapping("/listUserMennu")
    public JSONObject listUserMennu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        return menuService.listUserMenu(requestJson);
    }

    @PostMapping("/listRoleMenu")
    public JSONObject listRoleMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"roleId");
        return menuService.listRoleMenu(requestJson);
    }

    @SystemControllerLog(description = "为角色添加菜单")
    @PostMapping("/addRoleMenu")
    public JSONObject addRoleMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"menuId, roleId");
        return menuService.addRoleMenu(requestJson);
    }

    @SystemControllerLog(description = "为角色删除菜单")
    @PostMapping("/delRoleMenu")
    public JSONObject delRoleMenu(HttpServletRequest request){
        JSONObject requestJson = CommonUtil.request2Json(request);
        CommonUtil.hasAllRequired(requestJson,"menuId, roleId");
        return menuService.delRoleMenu(requestJson);
    }
}
