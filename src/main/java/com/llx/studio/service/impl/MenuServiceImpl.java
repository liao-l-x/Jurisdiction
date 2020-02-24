package com.llx.studio.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.llx.studio.dao.MenuDao;
import com.llx.studio.service.MenuService;
import com.llx.studio.util.CommonUtil;
import com.llx.studio.util.StringTools;
import com.llx.studio.util.constants.Constants;
import com.llx.studio.util.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    //#{menuSuperior}, #{menuOrder}, #{menuName}, #{menuURL}, #{meunImg}, #{menuType}, #{createTime}, #{createPerson},
    // i, #{remark}
    @Override
    public JSONObject addMenu(JSONObject jsonObject) {
        int i = menuDao.isNotMenuName(jsonObject.getString("menuName"));
        if (i > 0) {
            return CommonUtil.errorJson(ErrorEnum.E_10020);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("createPerson", userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("createTime", System.currentTimeMillis() / 1000 + "");
        menuDao.addMenu(jsonObject);
        return CommonUtil.successJson();
    }

    //列表这里要不要分页
    @Override
    public JSONObject listMenu(JSONObject jsonObject) {
        int i = menuDao.countMenu();
        List<JSONObject> listMenu = menuDao.listMenu();
        return CommonUtil.successJson(listMenu,i);
    }

    //菜单信息
    @Override
    public JSONObject oneMenu(JSONObject jsonObject) {
        String id = jsonObject.getString("menuId");
        int notMenuId = menuDao.isNotMenuId(Integer.parseInt(id));
        if (notMenuId > 0) {
            JSONObject oneMenu = menuDao.oneMenu(Integer.parseInt(id));
            return CommonUtil.successJson(oneMenu);
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_10023);
        }
    }

    @Override
    public JSONObject updataMenu(JSONObject jsonObject) {
        String id = jsonObject.getString("menuId");
        int notMenuId = menuDao.isNotMenuId(Integer.parseInt(id));
        if (notMenuId > 0) {
            //获取用户信息
            JSONObject userInfi = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
            jsonObject.put("updatePerson", userInfi.getString("userId"));
            jsonObject.put("lastModifide", (int) System.currentTimeMillis());
            menuDao.updataMenu(jsonObject);
            return CommonUtil.successJson();
        } else {
            return CommonUtil.errorJson(ErrorEnum.E_10023);
        }
    }

    @Override
    public JSONObject delMenu(JSONObject jsonObject) {
        String id = jsonObject.getString("menuId");
        int notMenuId = menuDao.isNotMenuId(Integer.parseInt(id));
        if (!(notMenuId > 0)) {
            return CommonUtil.errorJson(ErrorEnum.E_10023);
        }
        int leafMenu = menuDao.isLeafMenu(Integer.parseInt(id));
        if (leafMenu > 0 ){
            return CommonUtil.errorJson(ErrorEnum.E_10024);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("updatePerson", userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("lastModifide", System.currentTimeMillis() / 1000 + "");
        menuDao.delMenu(jsonObject);
        return CommonUtil.successJson();

    }

    @Override
    public JSONObject listUserMenu(JSONObject jsonObject) {
        if (!StringTools.isNullOrEmpty(jsonObject.getString("userId"))) {
            //获取Session的数据
            JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
            List<JSONObject> userMenu = menuDao.listUserMennu(Integer.parseInt(userSession.getString("userId")));
            return CommonUtil.successJson(userMenu);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        List<JSONObject> userMenu = menuDao.listUserMennu(Integer.parseInt(userSession.getString("userId")));
        return CommonUtil.successJson(userMenu);
    }

    @Override
    public JSONObject listRoleMenu(JSONObject jsonObject) {
        List<JSONObject> roleMenu = menuDao.listRoleMenu(Integer.parseInt(jsonObject.getString("roleId")));
        return CommonUtil.successJson(roleMenu);
    }

    @Override
    public JSONObject addRoleMenu(JSONObject jsonObject) {
        int notRoleMenu = menuDao.isNotRoleMenu(jsonObject);
        if (notRoleMenu > 0) {
            return CommonUtil.errorJson(ErrorEnum.E_10021);
        }
        //获取Session的数据
        JSONObject userSession = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
        jsonObject.put("createPerson", userSession.getString("userId"));
        //当前时间戳+“”
        jsonObject.put("createTime", System.currentTimeMillis() / 1000 + "");
        //菜单是根，还是角色有他的根
        int menuId = menuDao.isMenuSuperior(Integer.parseInt(jsonObject.getString("menuId")));
        if (menuId != 0){
            final List<JSONObject> roleId = menuDao.RoleMenuId(Integer.parseInt(jsonObject.getString("roleId")));
            for (JSONObject r : roleId) {
                if (Integer.parseInt(r.getString("menu_id")) == menuId){
                    menuDao.addRoleMenu(jsonObject);
                    return CommonUtil.successJson();
                }
            }
            return CommonUtil.errorJson(ErrorEnum.E_10025);
        }
        menuDao.addRoleMenu(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject delRoleMenu(JSONObject jsonObject) {
        int notRoleMenu = menuDao.isNotRoleMenu(jsonObject);
        if (notRoleMenu < 1) {
            return CommonUtil.errorJson(ErrorEnum.E_10022);
        }
        int leafMenu = menuDao.isLeafMenu(Integer.parseInt(jsonObject.getString("menuId")));
        if (leafMenu > 0 ){
            return CommonUtil.errorJson(ErrorEnum.E_10024);
        }
        menuDao.delRoleMenu(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public  List<JSONObject> getBooten(String uri){
        JSONObject menuId =  menuDao.URLmenuId(uri);
        if (null == menuId || menuId.size() == 0 ){
            return null;
        }
        String pid = menuId.getString("menu_id");
        return menuDao.listMennuButton(Integer.parseInt(pid));
    }
}
