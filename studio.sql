/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80011
Source Host           : 127.0.0.1:3306
Source Database       : studio

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-02-15 12:59:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单 id',
  `menu_superior` int(11) DEFAULT '0' COMMENT '上级id',
  `menu_order` bigint(1) DEFAULT '0' COMMENT '排序',
  `menu_name` varchar(20) DEFAULT '' COMMENT '菜单名称',
  `menu_URL` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '路由（URL）',
  `meun_img` varchar(20) DEFAULT '' COMMENT '图标',
  `menu_type` bigint(1) DEFAULT '0' COMMENT '类型（0目录、1菜单、2按钮）',
  `create_time` int(11) DEFAULT '0' COMMENT '创建时间',
  `create_person` bigint(11) DEFAULT '0' COMMENT '创建人',
  `last_modifide` int(11) DEFAULT '0' COMMENT '最后修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '备注',
  `update_person` bigint(20) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '1', '系统管理', '', null, '0', '1581676215', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('2', '1', '1', '用户管理', '/user/listUser', null, '1', '1581676452', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '1', '2', '菜单管理', '/menu/listMenu', null, '1', '1581677103', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('4', '1', '3', '角色管理', '/role/listRole', null, '1', '1581677228', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('5', '1', '3', '日志管理', null, null, '1', '1581677251', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('6', '2', '1', '添加用户', '/user/addUser', null, '2', '1581677421', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('8', '2', '1', '修改用户', '/user/updataUser', null, '2', '1581680959', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('9', '2', '1', '删除用户', '/user/delUser', null, '2', '1581681026', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '2', '1', '用户信息', '/user/oneUser', null, '2', '1581681058', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('11', '3', '1', '添加菜单', '/menu/addMenu', null, '2', '1581681143', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('12', '3', '1', '菜单信息', '/menu/oneMenu', null, '2', '1581681189', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '3', '1', '修改菜单', '/menu/updataMenu', null, '2', '1581681204', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '3', '1', '删除菜单', '/menu/delMenu', null, '2', '1581681219', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('15', '4', '1', '角色菜单', '/menu/listRoleMenu', null, '2', '1581681322', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('16', '4', '1', '添加角色菜单', '/menu/addRoleMenu', null, '2', '1581681386', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '4', '1', '删除角色菜单', '/menu/delRoleMenu', null, '2', '1581681403', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('18', '4', '1', '添加角色', '/role/addRole', null, '2', '1581681450', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('19', '4', '1', '修改角色', '/role/updataRole', null, '2', '1581681466', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '4', '1', '角色信息', '/role/oneRole', null, '2', '1581681614', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('21', '4', '1', '删除角色', '/role/delRole', null, '2', '1581681631', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('22', '2', '1', '添加用户角色', '/role/addUserRole', null, '2', '1581681695', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '2', '1', '用户角色信息', '/role/listUserRole', null, '2', '1581681727', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '2', '1', '删除用户角色信息', '/role/delUserRole', null, '2', '1581681769', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '4', '1', '修改角色状态', '/role/setRoleStat', null, '2', '1581681972', '6', '0', '0', null, '0');
INSERT INTO `sys_menu` VALUES ('26', '4', '1', '修改角色权限', '/role/setRolePermission', null, '2', '1581682176', '6', '0', '0', null, '0');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `oper_description` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求接口',
  `oper_method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '方法名称',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '操作人员',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` int(10) DEFAULT '0' COMMENT '主机地址',
  `oper_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '请求参数',
  `oper_status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` int(11) DEFAULT '0' COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=667 DEFAULT CHARSET=utf8 COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES ('590', '角色列表信息', 'com.llx.studio.controller.RoleController.listUser()', 'test2', '/role/listRole', '2130706433', '', '0', null, '1581658393');
INSERT INTO `sys_oper_log` VALUES ('591', '角色信息', 'com.llx.studio.controller.RoleController.oneRole()', 'test2', '/role/oneRole', '2130706433', '{\"roleId\":\"1\"}', '0', null, '1581658714');
INSERT INTO `sys_oper_log` VALUES ('592', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"0\",\"menuOrder\":\"1\",\"menuName\":\"系统管理\",\"menuType\":\"0\"}', '1', null, '1581675983');
INSERT INTO `sys_oper_log` VALUES ('593', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"0\",\"menuOrder\":\"1\",\"menuURL\":\"\\\"\\\"\",\"menuName\":\"系统管理\",\"menuType\":\"0\"}', '1', '\r\n### Error updating database.  Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,           `last_modifide`, `del_flag`, `remark`, `update_person`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           ?, ?);\r\n### Cause: java.sql.SQLException: Column count doesn\'t match value count at row 1\n; bad SQL grammar []; nested exception is java.sql.SQLException: Column count doesn\'t match value count at row 1', '1581676035');
INSERT INTO `sys_oper_log` VALUES ('594', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"0\",\"menuOrder\":\"1\",\"menuURL\":\"\\\"\\\"\",\"menuName\":\"系统管理\",\"menuType\":\"0\"}', '0', '', '1581676215');
INSERT INTO `sys_oper_log` VALUES ('595', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"0\",\"menuOrder\":\"1\",\"menuURL\":\"\\\"\\\"\",\"menuName\":\"系统管理\",\"menuType\":\"0\"}', '0', '', '1581676393');
INSERT INTO `sys_oper_log` VALUES ('596', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"1\",\"menuURL\":\"\\\"\\\"\",\"menuName\":\"用户管理\",\"menuType\":\"0\"}', '0', '', '1581676452');
INSERT INTO `sys_oper_log` VALUES ('597', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"2\",\"menuURL\":\"\",\"menuName\":\"角色用管理\",\"menuType\":\"0\"}', '1', null, '1581676667');
INSERT INTO `sys_oper_log` VALUES ('598', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"2\",\"menuName\":\"菜单管理\",\"menuType\":\"0\"}', '1', null, '1581677011');
INSERT INTO `sys_oper_log` VALUES ('599', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"2\",\"menuName\":\"菜单管理\",\"menuType\":\"0\"}', '1', null, '1581677040');
INSERT INTO `sys_oper_log` VALUES ('600', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"2\",\"menuName\":\"菜单管理\",\"menuType\":\"0\"}', '0', '0', '1581677103');
INSERT INTO `sys_oper_log` VALUES ('601', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"3\",\"menuName\":\"角色管理\",\"menuType\":\"0\"}', '0', '0', '1581677228');
INSERT INTO `sys_oper_log` VALUES ('602', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"1\",\"menuOrder\":\"3\",\"menuName\":\"日志管理\",\"menuType\":\"0\"}', '0', '0', '1581677251');
INSERT INTO `sys_oper_log` VALUES ('603', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/addUser\",\"menuName\":\"添加用户\",\"menuType\":\"1\"}', '0', '0', '1581677421');
INSERT INTO `sys_oper_log` VALUES ('604', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/addUser\",\"menuName\":\"添加用户A\",\"menuType\":\"2\"}', '0', '0', '1581677906');
INSERT INTO `sys_oper_log` VALUES ('605', '修改菜单信息', 'com.llx.studio.controller.MenuController.updataMenu()', 'test2', '/menu/updataMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/listUser\",\"menuId\":\"7\",\"menuName\":\"用户列表\",\"menuType\":\"1\"}', '0', '0', '1581678056');
INSERT INTO `sys_oper_log` VALUES ('606', '删除菜单', 'com.llx.studio.controller.MenuController.delMenu()', 'test2', '/menu/delMenu', '2130706433', '{\"menuId\":\"7\"}', '0', '0', '1581678146');
INSERT INTO `sys_oper_log` VALUES ('607', '为角色添加菜单', 'com.llx.studio.controller.MenuController.addRoleMenu()', 'test2', '/menu/addRoleMenu', '2130706433', '{\"roleId\":\"1\",\"menuId\":\"1\"}', '1', 'Mapper method \'com.llx.studio.dao.MenuDao.addRoleMenu\' has an unsupported return type: class com.alibaba.fastjson.JSONObject', '1581678400');
INSERT INTO `sys_oper_log` VALUES ('608', '为角色添加菜单', 'com.llx.studio.controller.MenuController.addRoleMenu()', 'test2', '/menu/addRoleMenu', '2130706433', '{\"roleId\":\"1\",\"menuId\":\"1\"}', '0', '0', '1581678505');
INSERT INTO `sys_oper_log` VALUES ('609', '为角色添加菜单', 'com.llx.studio.controller.MenuController.addRoleMenu()', 'test2', '/menu/addRoleMenu', '2130706433', '{\"roleId\":\"2\",\"menuId\":\"1\"}', '0', '0', '1581678926');
INSERT INTO `sys_oper_log` VALUES ('610', '为角色删除菜单', 'com.llx.studio.controller.MenuController.delRoleMenu()', 'test2', '/menu/delRoleMenu', '2130706433', '{\"roleId\":\"1\",\"menuId\":\"1\"}', '1', 'Mapper method \'com.llx.studio.dao.MenuDao.delRoleMenu\' has an unsupported return type: class com.alibaba.fastjson.JSONObject', '1581679240');
INSERT INTO `sys_oper_log` VALUES ('611', '为角色删除菜单', 'com.llx.studio.controller.MenuController.delRoleMenu()', 'test2', '/menu/delRoleMenu', '2130706433', '{\"roleId\":\"1\",\"menuId\":\"1\"}', '0', '0', '1581679293');
INSERT INTO `sys_oper_log` VALUES ('612', '为角色删除菜单', 'com.llx.studio.controller.MenuController.delRoleMenu()', 'test2', '/menu/delRoleMenu', '2130706433', '{\"roleId\":\"2\",\"menuId\":\"1\"}', '0', '0', '1581679320');
INSERT INTO `sys_oper_log` VALUES ('613', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/updataUser\",\"menuName\":\"修改用户\",\"menuType\":\"2\"}', '0', '0', '1581680959');
INSERT INTO `sys_oper_log` VALUES ('614', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/delUser\",\"menuName\":\"删除用户\",\"menuType\":\"2\"}', '0', '0', '1581681026');
INSERT INTO `sys_oper_log` VALUES ('615', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/oneUser\",\"menuName\":\"用户信息\",\"menuType\":\"2\"}', '0', '0', '1581681058');
INSERT INTO `sys_oper_log` VALUES ('616', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"3\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/addMenu\",\"menuName\":\"添加菜单\",\"menuType\":\"2\"}', '0', '0', '1581681143');
INSERT INTO `sys_oper_log` VALUES ('617', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"3\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/oneMenu\",\"menuName\":\"菜单信息\",\"menuType\":\"2\"}', '0', '0', '1581681189');
INSERT INTO `sys_oper_log` VALUES ('618', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"3\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/updataMenu\",\"menuName\":\"修改菜单\",\"menuType\":\"2\"}', '0', '0', '1581681204');
INSERT INTO `sys_oper_log` VALUES ('619', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"3\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/delMenu\",\"menuName\":\"删除菜单\",\"menuType\":\"2\"}', '0', '0', '1581681219');
INSERT INTO `sys_oper_log` VALUES ('620', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/listRoleMenu\",\"menuName\":\"角色菜单\",\"menuType\":\"2\"}', '0', '0', '1581681322');
INSERT INTO `sys_oper_log` VALUES ('621', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/addRoleMenu\",\"menuName\":\"添加菜单\",\"menuType\":\"2\"}', '0', '0', '1581681351');
INSERT INTO `sys_oper_log` VALUES ('622', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/addRoleMenu\",\"menuName\":\"添加角色菜单\",\"menuType\":\"2\"}', '0', '0', '1581681386');
INSERT INTO `sys_oper_log` VALUES ('623', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/menu/delRoleMenu\",\"menuName\":\"删除角色菜单\",\"menuType\":\"2\"}', '0', '0', '1581681403');
INSERT INTO `sys_oper_log` VALUES ('624', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/addRole\",\"menuName\":\"添加角色\",\"menuType\":\"2\"}', '0', '0', '1581681450');
INSERT INTO `sys_oper_log` VALUES ('625', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/updataRole\",\"menuName\":\"修改角色\",\"menuType\":\"2\"}', '0', '0', '1581681466');
INSERT INTO `sys_oper_log` VALUES ('626', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/updataRole\",\"menuName\":\"修改角色\",\"menuType\":\"2\"}', '0', '0', '1581681586');
INSERT INTO `sys_oper_log` VALUES ('627', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/oneRole\",\"menuName\":\"角色信息\",\"menuType\":\"2\"}', '0', '0', '1581681614');
INSERT INTO `sys_oper_log` VALUES ('628', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/delRole\",\"menuName\":\"删除角色\",\"menuType\":\"2\"}', '0', '0', '1581681631');
INSERT INTO `sys_oper_log` VALUES ('629', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/addUserRole\",\"menuName\":\"用户用户角色\",\"menuType\":\"2\"}', '0', '0', '1581681695');
INSERT INTO `sys_oper_log` VALUES ('630', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/listUserRole\",\"menuName\":\"用户角色信息\",\"menuType\":\"2\"}', '0', '0', '1581681727');
INSERT INTO `sys_oper_log` VALUES ('631', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/delUserRole\",\"menuName\":\"删除用户角色信息\",\"menuType\":\"2\"}', '0', '0', '1581681769');
INSERT INTO `sys_oper_log` VALUES ('632', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"修改角色状态\",\"menuURL\":\"/role/setRoleStat\",\"menuType\":\"2\"}', '1', null, '1581681951');
INSERT INTO `sys_oper_log` VALUES ('633', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRoleStat\",\"menuType\":\"2\",\"menuName\":\"修改角色状态\"}', '0', '0', '1581681973');
INSERT INTO `sys_oper_log` VALUES ('634', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,`del_flag`, `remark`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           0, ?);\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\n; Data truncation: Data too long for column \'menu_URL\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1', '1581682004');
INSERT INTO `sys_oper_log` VALUES ('635', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,`del_flag`, `remark`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           0, ?);\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\n; Data truncation: Data too long for column \'menu_URL\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1', '1581682041');
INSERT INTO `sys_oper_log` VALUES ('636', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,`del_flag`, `remark`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           0, ?);\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\n; Data truncation: Data too long for column \'menu_URL\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1', '1581682071');
INSERT INTO `sys_oper_log` VALUES ('637', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,`del_flag`, `remark`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           0, ?);\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\n; Data truncation: Data too long for column \'menu_URL\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1', '1581682089');
INSERT INTO `sys_oper_log` VALUES ('638', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '1', '\r\n### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: INSERT INTO sys_menu         (`menu_superior`, `menu_order`, `menu_name`,           `menu_URL`, `meun_img`, `menu_type`, `create_time`, `create_person`,`del_flag`, `remark`)         VALUES          (?, ?, ?, ?, ?, ?, ?, ?,           0, ?);\r\n### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1\n; Data truncation: Data too long for column \'menu_URL\' at row 1; nested exception is com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Data too long for column \'menu_URL\' at row 1', '1581682149');
INSERT INTO `sys_oper_log` VALUES ('639', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '0', '0', '1581682179');
INSERT INTO `sys_oper_log` VALUES ('640', '删除用户', 'com.llx.studio.controller.UserController.delUser()', 'test2', '/user/delUser', '2130706433', '{\"userId\":\"9\"}', '0', '0', '1581683942');
INSERT INTO `sys_oper_log` VALUES ('641', '添加用户', 'com.llx.studio.controller.UserController.addUser()', 'test2', '/user/addUser', '2130706433', '{\"userPasswd\":\"123456\",\"userPhone\":\"15012312312\",\"userName\":\"test4\"}', '0', '0', '1581738442');
INSERT INTO `sys_oper_log` VALUES ('642', '修改用户', 'com.llx.studio.controller.UserController.updataUser()', 'test2', '/user/updataUser', '2130706433', '{\"userSchoolLocation\":\"江西环境工程职业学院-通信与信息学院-移动互联用\",\"userSchoolIdentity\":\"0\",\"userFullName\":\"lightage\",\"userMail\":\"15083589721@qq.com\",\"userGender\":\"0\",\"userAdder\":\"江西赣州宁都\",\"userIntroduce\":\"一个人\",\"userSchool\":\"江西环境工程职业学院\",\"userSchoolStudentId\":\"20173153\"}', '0', '0', '1581738463');
INSERT INTO `sys_oper_log` VALUES ('643', '删除用户', 'com.llx.studio.controller.UserController.delUser()', 'test2', '/user/delUser', '2130706433', '{\"userId\":\"10\"}', '0', '0', '1581738478');
INSERT INTO `sys_oper_log` VALUES ('644', '添加角色', 'com.llx.studio.controller.RoleController.addUser()', 'test2', '/role/addRole', '2130706433', '{\"roleName\":\"学生\",\"rolePowe\":\"study：study\",\"roleOrder\":\"3\"}', '0', '0', '1581738512');
INSERT INTO `sys_oper_log` VALUES ('645', '修改角色信息', 'com.llx.studio.controller.RoleController.updataRole()', 'test2', '/role/updataRole', '2130706433', '{\"roleStat\":\"\",\"roleId\":\"2\",\"roleName\":\"\",\"rolePowe\":\"\",\"roleOrder\":\"2\"}', '0', '0', '1581738529');
INSERT INTO `sys_oper_log` VALUES ('646', '角色信息', 'com.llx.studio.controller.RoleController.oneRole()', 'test2', '/role/oneRole', '2130706433', '{\"roleId\":\"1\"}', '0', '0', '1581738535');
INSERT INTO `sys_oper_log` VALUES ('647', '为用户添加角色', 'com.llx.studio.controller.RoleController.addUserRole()', 'test2', '/role/addUserRole', '2130706433', '{\"roleId\":\"2\",\"userId\":\"6\"}', '0', '0', '1581738540');
INSERT INTO `sys_oper_log` VALUES ('648', '为用户删除角色', 'com.llx.studio.controller.RoleController.delUserRole()', 'test2', '/role/delUserRole', '2130706433', '{\"roleId\":\"1\",\"userId\":\"6\"}', '0', '0', '1581738547');
INSERT INTO `sys_oper_log` VALUES ('649', '修改角色状态', 'com.llx.studio.controller.RoleController.setRoleStat()', 'test2', '/role/setRoleStat', '2130706433', '{\"roleStat\":\"1\",\"roleId\":\"1\"}', '0', '0', '1581738551');
INSERT INTO `sys_oper_log` VALUES ('650', '修改角色权限', 'com.llx.studio.controller.RoleController.setRolePermission()', 'test2', '/role/setRolePermission', '2130706433', '{\"roleId\":\"1\",\"rolePowe\":\"abc:abc\"}', '0', '0', '1581738555');
INSERT INTO `sys_oper_log` VALUES ('651', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '0', '0', '1581738560');
INSERT INTO `sys_oper_log` VALUES ('652', '修改菜单信息', 'com.llx.studio.controller.MenuController.updataMenu()', 'test2', '/menu/updataMenu', '2130706433', '{\"menuSuperior\":\"2\",\"menuOrder\":\"1\",\"menuURL\":\"/user/listUser\",\"menuId\":\"7\",\"menuName\":\"用户列表\",\"menuType\":\"1\"}', '0', '0', '1581738572');
INSERT INTO `sys_oper_log` VALUES ('653', '删除菜单', 'com.llx.studio.controller.MenuController.delMenu()', 'test2', '/menu/delMenu', '2130706433', '{\"menuId\":\"7\"}', '0', '0', '1581738576');
INSERT INTO `sys_oper_log` VALUES ('654', '为角色添加菜单', 'com.llx.studio.controller.MenuController.addRoleMenu()', 'test2', '/menu/addRoleMenu', '2130706433', '{\"roleId\":\"2\",\"menuId\":\"1\"}', '0', '0', '1581738581');
INSERT INTO `sys_oper_log` VALUES ('655', '为角色删除菜单', 'com.llx.studio.controller.MenuController.delRoleMenu()', 'test2', '/menu/delRoleMenu', '2130706433', '{\"roleId\":\"2\",\"menuId\":\"1\"}', '0', '0', '1581738593');
INSERT INTO `sys_oper_log` VALUES ('656', '添加用户', 'com.llx.studio.controller.UserController.addUser()', 'test2', '/user/addUser', '2130706433', '{\"userPasswd\":\"123456\",\"userPhone\":\"15012312312\",\"userName\":\"test5\"}', '0', '0', '1581739132');
INSERT INTO `sys_oper_log` VALUES ('657', '修改用户', 'com.llx.studio.controller.UserController.updataUser()', 'test2', '/user/updataUser', '2130706433', '{\"userSchoolLocation\":\"江西环境工程职业学院-通信与信息学院-移动互联用\",\"userSchoolIdentity\":\"0\",\"userFullName\":\"lightage\",\"userMail\":\"15083589721@qq.com\",\"userGender\":\"0\",\"userAdder\":\"江西赣州宁都\",\"userIntroduce\":\"一个人\",\"userSchool\":\"江西环境工程职业学院\",\"userSchoolStudentId\":\"20173153\"}', '0', '0', '1581739156');
INSERT INTO `sys_oper_log` VALUES ('658', '删除用户', 'com.llx.studio.controller.UserController.delUser()', 'test2', '/user/delUser', '2130706433', '{\"userId\":\"11\"}', '0', '0', '1581739196');
INSERT INTO `sys_oper_log` VALUES ('659', '添加角色', 'com.llx.studio.controller.RoleController.addUser()', 'test2', '/role/addRole', '2130706433', '{\"roleName\":\"老师\",\"rolePowe\":\"teacher:teacher\",\"roleOrder\":\"2\"}', '0', '0', '1581739254');
INSERT INTO `sys_oper_log` VALUES ('660', '为用户添加角色', 'com.llx.studio.controller.RoleController.addUserRole()', 'test2', '/role/addUserRole', '2130706433', '{\"roleId\":\"3\",\"userId\":\"6\"}', '0', '0', '1581739288');
INSERT INTO `sys_oper_log` VALUES ('661', '为用户删除角色', 'com.llx.studio.controller.RoleController.delUserRole()', 'test2', '/role/delUserRole', '2130706433', '{\"roleId\":\"2\",\"userId\":\"6\"}', '0', '0', '1581739318');
INSERT INTO `sys_oper_log` VALUES ('662', '修改角色状态', 'com.llx.studio.controller.RoleController.setRoleStat()', 'test2', '/role/setRoleStat', '2130706433', '{\"roleStat\":\"1\",\"roleId\":\"1\"}', '0', '0', '1581739329');
INSERT INTO `sys_oper_log` VALUES ('663', '修改角色权限', 'com.llx.studio.controller.RoleController.setRolePermission()', 'test2', '/role/setRolePermission', '2130706433', '{\"roleId\":\"1\",\"rolePowe\":\"admin:admin\"}', '0', '0', '1581739343');
INSERT INTO `sys_oper_log` VALUES ('664', '添加菜单', 'com.llx.studio.controller.MenuController.addMenu()', 'test2', '/menu/addMenu', '2130706433', '{\"menuSuperior\":\"4\",\"menuOrder\":\"1\",\"menuURL\":\"/role/setRolePermission\",\"menuType\":\"2\",\"menuName\":\"修改角色权限\"}', '0', '0', '1581739354');
INSERT INTO `sys_oper_log` VALUES ('665', '为角色删除菜单', 'com.llx.studio.controller.MenuController.delRoleMenu()', 'test2', '/menu/delRoleMenu', '2130706433', '{\"roleId\":\"2\",\"menuId\":\"1\"}', '0', '0', '1581739403');
INSERT INTO `sys_oper_log` VALUES ('666', '添加用户', 'com.llx.studio.controller.UserController.addUser()', 'test2', '/user/addUser', '2130706433', '{\"userPasswd\":\"123456\",\"userPhone\":\"15012312312\",\"userName\":\"test6\"}', '0', '0', '1581741436');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) DEFAULT '' COMMENT '角色名称',
  `role_powe` varchar(20) DEFAULT '' COMMENT '角色权限字符串',
  `role_order` bigint(2) DEFAULT '0' COMMENT '显示顺序',
  `role_stat` bigint(1) DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `create_time` int(11) DEFAULT '0' COMMENT '创建时间',
  `create_person` bigint(11) DEFAULT '0' COMMENT '创建人',
  `last_modifide` int(11) DEFAULT '0' COMMENT '最后修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `update_person` bigint(20) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin:admin', '1', '1', '1581507695', '6', '1581739343', '0', '', '6');
INSERT INTO `sys_role` VALUES ('2', '老师', 'teacher:teacher', '2', '0', '1581507873', '6', '1190564689', '0', '', '6');
INSERT INTO `sys_role` VALUES ('3', '学生', 'study：study', '3', '0', '1581738512', '6', '0', '0', '', '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `menu_id` int(11) NOT NULL COMMENT '菜单id',
  `create_time` int(11) DEFAULT '0' COMMENT '创建时间',
  `create_person` bigint(11) DEFAULT '0' COMMENT '创建人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('2', '1', '1581738581', '6');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `user_name` varchar(11) NOT NULL COMMENT '用户名',
  `user_passwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_encryption` varchar(25) DEFAULT '' COMMENT '盐加密',
  `user_type` bigint(1) DEFAULT '1' COMMENT '用户类型（0系统用户）',
  `user_state` bigint(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `user_last_ip` int(10) DEFAULT '0' COMMENT '最后登陆IP',
  `user_last_time` int(11) DEFAULT '0' COMMENT '最后登陆时间',
  `user_full_name` varchar(11) DEFAULT '' COMMENT '用户姓名',
  `user_mail` varchar(25) DEFAULT '' COMMENT '用户邮箱',
  `user_phone` char(20) DEFAULT '' COMMENT '手机号码',
  `user_gender` bigint(1) DEFAULT '2' COMMENT '用户性别（0男 1女 2未知）',
  `user_head` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '头像路径',
  `user_school` varchar(50) DEFAULT '' COMMENT '学校',
  `user_school_location` varchar(250) DEFAULT '' COMMENT '学校位置',
  `user_school_identity` bigint(1) DEFAULT '0' COMMENT '在校身份(0学生 1老师 2教授）',
  `user_school_student_id` varchar(20) DEFAULT '' COMMENT '学号',
  `user_adder` varchar(250) DEFAULT '' COMMENT '个人地址',
  `user_introduce` varchar(250) DEFAULT '' COMMENT '个人介绍',
  `create_time` int(11) DEFAULT '0' COMMENT '创建时间',
  `create_person` bigint(11) DEFAULT '0' COMMENT '创建人',
  `last_modifide` int(11) DEFAULT '0' COMMENT '最后修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  `update_person` bigint(20) DEFAULT '0' COMMENT '更新人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('6', 'test2', 'b04581de921a20b65493876437f5fd94', '1581418699541', '1', '0', '0', '0', 'lightage', '15083589721@qq.com', '', '0', '', '江西环境工程职业学院', '江西环境工程职业学院-通信与信息学院-移动互联用', '0', '20173153', '江西赣州宁都', '一个人', '0', '0', '1191191067', '0', '', '0');
INSERT INTO `sys_user` VALUES ('7', 'test3', '921aa53aa180f51851ea088414e65808', '1581473754629', '1', '0', '0', '0', '', '', '', '2', '', '', '', '0', '', '', '', '0', '0', '943960241', '2', '', '0');
INSERT INTO `sys_user` VALUES ('8', 'test4', '588ff2c914aa48d4c31a5baa9a1d9311', '1581474221400', '1', '0', '0', '0', '', '', '15012312312', '2', '', '', '', '0', '', '', '', '0', '0', '0', '0', '', '0');
INSERT INTO `sys_user` VALUES ('9', 'test4', '632270960a6e670cd7b950b469304e7e', '1581477401932', '1', '0', '0', '0', '', '', '15012312312', '2', '', '', '', '0', '', '', '', '0', '0', '1135977633', '2', '', '0');
INSERT INTO `sys_user` VALUES ('10', 'test4', '12f0535280e6272ce4679b07924ed9db', '1581738442504', '1', '0', '0', '0', '', '', '15012312312', '2', '', '', '', '0', '', '', '', '0', '0', '1190513942', '2', '', '0');
INSERT INTO `sys_user` VALUES ('11', 'test5', '6cb3acd6e9b45fda519487f15f15b4d5', '1581739132684', '1', '0', '0', '0', '', '', '15012312312', '2', '', '', '', '0', '', '', '', '0', '0', '1191231217', '2', '', '0');
INSERT INTO `sys_user` VALUES ('12', 'test6', '99a4adc7a43a53990d63b24348183fe2', '1581741436354', '1', '0', '0', '0', '', '', '15012312312', '2', '', '', '', '0', '', '', '', '0', '0', '0', '0', '', '0');

-- ----------------------------
-- Table structure for sys_user_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
  `user_id` int(11) DEFAULT '0' COMMENT '用户id',
  `user_type` bigint(2) DEFAULT '0' COMMENT '用户类型（00系统用户）',
  `user_ip` int(10) DEFAULT '0' COMMENT '登陆IP',
  `user_time` int(11) DEFAULT '0' COMMENT '登陆时间',
  `user_stat` bigint(1) DEFAULT '1' COMMENT '登入状态（0失败 1成功）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_log
-- ----------------------------
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '942101404', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '942214832', '0');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '945604427', '0');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '945638715', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '945695298', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '945764580', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '945962574', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '946060475', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '946351965', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '946448432', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '958853069', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '959317976', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '959727017', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '959848362', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '960198636', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '960774401', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '960855490', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '962161715', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '962703527', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '962941723', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1022859670', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1022930289', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1023067822', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1023161680', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1023792267', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1023862407', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1024893964', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1110098535', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1110422523', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1110698949', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1127891012', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1128247839', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1128425733', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1129026309', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1129128293', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1129760717', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1129865355', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1130535346', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1130840070', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1130947666', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1131194377', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1131325501', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1132005856', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1132647273', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1132988077', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1134520715', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1134593678', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1134740332', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1135875712', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1190445085', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1190459936', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1191104502', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1191150657', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1192751843', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1192806271', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1192946552', '1');
INSERT INTO `sys_user_log` VALUES ('6', '1', '2130706433', '1193437821', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `create_time` int(11) DEFAULT '0' COMMENT '创建时间',
  `create_person` bigint(11) DEFAULT '0' COMMENT '创建人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('6', '3', '1581739288', '6');
