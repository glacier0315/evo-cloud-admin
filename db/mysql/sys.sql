CREATE DATABASE eboot_sys CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

use eboot_sys;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user  (
  id varchar(64) NOT NULL COMMENT '主键',
  username varchar(50)  COMMENT '用户名',
  password varchar(255)  COMMENT '密码',
  nickname varchar(50)  COMMENT '昵称',
  id_card varchar(20)  COMMENT '身份证号',
  birthday date  COMMENT '生日',
  sex int(4) COMMENT '性别',
  email varchar(100)  COMMENT '邮箱',
  mobile varchar(20)  COMMENT '手机号',
  dept_id varchar(64)  COMMENT '单位',
  dept_name varchar(64)  COMMENT '单位',
  status varchar(4)  COMMENT '状态',
  avatar varchar(500)  COMMENT '头像',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp COMMENT '更新时间',
  del_flag varchar(4) default '0'  COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_user_1(username) USING BTREE,
  INDEX sys_user_2(id_card) USING BTREE,
  INDEX sys_user_3(status) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user (id, username, password, nickname, id_card, birthday, sex, email, mobile, dept_id, dept_name, status, avatar, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', 'admin', '$2a$10$RCvUyaqalDebEuYPVSOqCOlpRBO9hfslhp4SEN1dK/kJcc0FYY0aW', '超级管理员', '11111111111111111111', null, 1, '14151@126.com', '15926081406', '4c963d38ae25416ea3c1f141656b8f78', '湖北英泽', '1', 'http://192.168.10.132:8001/group1/M00/00/00/wKgKhF8elwOAbe2IAACn8hIebpk33.jpeg', null, '2019-11-07 17:01:42', null, '2020-07-27 09:02:19', '0');
INSERT INTO sys_user (id, username, password, nickname, id_card, birthday, sex, email, mobile, dept_id, dept_name, status, avatar, create_by, create_date, update_by, update_date, del_flag) VALUES ('3d908373717c4539ba02011e2bfc2398', 'ceshi2', '$2a$10$68/saBjPxR8aLuhTI7reR.OFj0pnIhx/CG/bopFsbCbEIp17I5Pe2', '测试22', '1111111', '1990-06-08', 1, '321@126.com', '142536', 'ca06d9feadf143a7adb7977923e4e3cc', '湖北科尔', '1', null, null, '2020-08-08 05:26:38', null, '2020-08-08 05:26:38', '0');
INSERT INTO sys_user (id, username, password, nickname, id_card, birthday, sex, email, mobile, dept_id, dept_name, status, avatar, create_by, create_date, update_by, update_date, del_flag) VALUES ('651c74980f0f40729b28d6bd9fe32c80', 'ceshi1', '$2a$10$aJ/mxyNZZe3AfYpykFA8G.ubIDfHboYMtJH/ejgkk3h4bUDs3Dvsy', '测试11', '1111111111111111', '1988-07-08', 1, '123@126.com', '1142536475', '4c963d38ae25416ea3c1f141656b8f78', '湖北英泽', '1', null, null, '2020-08-08 05:22:02', null, '2020-08-08 05:22:02', '0');
INSERT INTO sys_user (id, username, password, nickname, id_card, birthday, sex, email, mobile, dept_id, dept_name, status, avatar, create_by, create_date, update_by, update_date, del_flag) VALUES ('c35d5b94aca547799fbe56ddd90bcfc4', 'ceshi', '$2a$10$RP6MGP8lFqKhh0.2/SroKepKV25y8u2XSjvD8dzRXBzwAZGZYSYl.', '普通用户', '11111111111111111111', null, 2, '14151@126.com', '15926081406', '4c963d38ae25416ea3c1f141656b8f78', '湖北英泽', '1', 'http://192.168.10.132:8001/group1/M00/00/00/wKgKhF8elwOAbe2IAACn8hIebpk33.jpeg', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu  (
                           id          varchar(64) NOT NULL DEFAULT '1' COMMENT '主键',
                           name        varchar(200) COMMENT '名称',
                           path        varchar(255) COMMENT '端点路径',
                           component   varchar(255) COMMENT '组件',
                           icon        varchar(255) COMMENT '图标',
                           type        int(4)               DEFAULT 1 COMMENT '类型',
                           visible     int(4)               DEFAULT 1 COMMENT '1 显示  2 隐藏',
                           is_frame    int(4)               DEFAULT 2 COMMENT '是否外链 1 是  2 否',
                           permission  varchar(255) COMMENT '权限',
                           parent_id   varchar(255) COMMENT '父级id',
                           grade       int(4) COMMENT '层级',
                           order_num   int(4) COMMENT '排序号',
                           create_by   varchar(64) COMMENT '创建人',
                           create_date timestamp            DEFAULT current_timestamp COMMENT '创建时间',
                           update_by   varchar(64) COMMENT '更新人',
                           update_date timestamp            DEFAULT current_timestamp COMMENT '更新时间',
                           del_flag    varchar(4)           default '0' COMMENT '删除标记',
                           PRIMARY KEY (id) USING BTREE,
                           INDEX sys_menu_1 (parent_id) USING BTREE,
                           INDEX sys_menu_2 (type) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', 'permission', '/permission', 'Layout', 'lock', 1, 1, 2, null, null, 1, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('2', 'PagePermission', '/permission/page', 'permission/page', null, 2, 1, 2, null, '1', 2, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('3', 'directive', '/permission/directive', 'permission/directive', null, 2, 1, 2, null, '1', 3, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('4', 'role', '/permission/role', 'permission/role', null, 2, 1, 2, null, '1', 4, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('5', '系统设置', '/sys', '', 'el-icon-setting', 1, 1, 2, null, null, 5, null, '2020-06-27 03:29:29', null, '2020-06-27 03:29:29', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('6', '用户列表', '/sys/user', 'sys/user', 'user', 2, 1, 2, null, '5', 6, null, '2020-06-27 03:31:00', null, '2020-07-04 03:39:23', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('7', '角色列表', '/sys/role', 'sys/role', 'people', 2, 1, 2, null, '5', 7, null, '2020-06-27 03:31:33', null, '2020-06-27 03:31:33', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('8', '菜单列表', '/sys/menu', 'sys/menu', 'tree', 2, 1, 2, null, '5', 8, null, '2020-06-27 03:32:20', null, '2020-06-27 03:32:20', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('9', '组织机构', '/sys/dept', 'sys/dept', 'tree', 2, 1, 2, null, '5', 10, null, '2020-06-27 03:32:54', null, '2020-07-04 02:36:30', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('0b2c28fb18bc213a2ea7143cbf724702', 'icon', '/icon', 'Layout', null, 1, 1, 2, null, null, 11, null, '2020-07-04 02:37:46', null, '2020-07-04 02:37:46', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('a38df56dd7f59f030a0bc449fcc97d57', 'icons', '/icon/index', 'icons', 'icon', 2, 1, 2, null, '0b2c28fb18bc213a2ea7143cbf724702', 12, null, '2020-07-04 02:38:52', null, '2020-07-04 02:38:52', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('be14507626f7f301fbfc21ddbd8e6380', 'pdf', '/pdf', null, null, 1, 1, 2, null, null, 13, null, '2020-07-04 03:04:29', null, '2020-07-04 03:04:29', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('54f41ef8740a67b56a122453d0323ded', 'pdf', '/pdf/index', 'pdf/index', 'pdf', 2, 1, 2, null, 'be14507626f7f301fbfc21ddbd8e6380', 14, null, '2020-07-04 03:05:13', null, '2020-07-04 03:05:13', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role  (
  id varchar(64) NOT NULL COMMENT '主键',
  name varchar(200)  COMMENT '角色名',
  code varchar(255)  COMMENT '角色编码',
  status varchar(4) default '1'  COMMENT '状态',
  description varchar(500)  COMMENT '说明',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp  COMMENT '更新时间',
  del_flag varchar(4) default '0' COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_role_1(code) USING BTREE,
  INDEX sys_role_2(status) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role (id, name, code, status, description, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', '超级管理员', 'admin', '1', null, null, '2020-06-25 07:45:59', null, '2020-07-04 03:39:09', '0');
INSERT INTO sys_role (id, name, code, status, description, create_by, create_date, update_by, update_date, del_flag) VALUES ('d1ece8903278fe25e8be2804fd755025', '普通用户', 'user', '1', '普通用户', null, '2020-07-04 01:49:03', null, '2020-07-04 03:39:13', '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS sys_role_dept;
CREATE TABLE sys_role_dept  (
  id varchar(64) NOT NULL COMMENT '主键',
  role_id varchar(64)  COMMENT '角色主键',
  dept_id varchar(64)  COMMENT '组织机构主键',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_role_dept_1(dept_id) USING BTREE,
  INDEX sys_role_dept_2(role_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和组织机构关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu  (
  id varchar(64) NOT NULL COMMENT '主键',
  role_id varchar(64)  COMMENT '角色主键',
  menu_id varchar(64)  COMMENT '菜单主键',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_role_menu_1(menu_id) USING BTREE,
  INDEX sys_role_menu_2(role_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('04617accfdf6bdd41f05a773d0b25603', '1', '2');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('22c6eb304c58b0fd02a37e0762687284', '1', '9');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('4b5f896e41cc7d873e706b4b98ba31ab', '1', '7');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('61e07b1e50bc91014700976febe9becb', '1', '5');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('85c60245ad524a9cc1a003f361bac03a', '1', '6');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('b2682444e18c7dc7675da8c0c70bc9ac', '1', '4');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('beb7fe8b7012b00459fa8c3e949382d1', '1', '8');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('e2ecf86253fb7c665d82c7742efc1c82', '1', '3');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('e8ca22cf7f7a17ede9dcfd17b10cc32a', '1', '1');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('eaac030189c43012f6a82db1b301790e', 'd1ece8903278fe25e8be2804fd755025', '8');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('ebbf70df4d99d6cca88449facc0cdc5b', 'd1ece8903278fe25e8be2804fd755025', '3');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role  (
  id varchar(64) NOT NULL COMMENT '主键',
  user_id varchar(64)  COMMENT '用户主键',
  role_id varchar(64)  COMMENT '角色主键',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_user_role_1(user_id) USING BTREE,
  INDEX sys_user_role_2(role_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role (id, user_id, role_id) VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config  (
  id varchar(64) NOT NULL COMMENT '主键',
  value varchar(255)  COMMENT '值',
  label varchar(255)  COMMENT '名称',
  type varchar(255)  COMMENT '类型',
  description varchar(500)  COMMENT '描述',
  order_num int(4)  COMMENT '排序',
  remarks varchar(500)  COMMENT '备注',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp  COMMENT '更新时间',
  del_flag varchar(4) default '0'  COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_config_1(type) USING BTREE,
  INDEX sys_config_2(value) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS sys_dept;
CREATE TABLE sys_dept
(
    id          varchar(64) NOT NULL COMMENT '主键',
    name        varchar(200) COMMENT '名称',
    code        varchar(255) COMMENT '编码',
    type        int(4) COMMENT '类型',
    status      int(4) COMMENT '状态',
    order_num   int(4) COMMENT '排序号',
    grade       int(4) COMMENT '层级',
    parent_id   varchar(255) COMMENT '父级id',
    parent_name varchar(255) COMMENT '父级单位',
    parent_ids  varchar(255) COMMENT '所有父级id,格式：,id1,id2,',
    create_by   varchar(64) COMMENT '创建人',
    create_date timestamp  DEFAULT current_timestamp COMMENT '创建时间',
    update_by   varchar(64) COMMENT '更新人',
    update_date timestamp  DEFAULT current_timestamp COMMENT '更新时间',
    del_flag    varchar(4) default '0' COMMENT '删除标记',
    PRIMARY KEY (id) USING BTREE,
    INDEX sys_dept_1 (parent_id) USING BTREE,
    INDEX sys_dept_2 (type) USING BTREE,
    INDEX sys_dept_3 (status) USING BTREE,
    INDEX sys_dept_4 (code) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '组织机构表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('d018f06a3a194ac68a3d0f5677e4cfbe', '湖北', 'hb001', 0, 0, 0, 1, null, null, null, null, '2020-06-25 07:45:59',
        null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('90e56a2a4e6a4add9a5b9a26fc212db2', '湖南', 'cs1', 0, 0, 3, 1, null, null, null, null, '2020-06-25 07:45:59',
        null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('ca06d9feadf143a7adb7977923e4e3cc', '湖北武汉', 'hb001001', 0, 0, 1, 2, 'd018f06a3a194ac68a3d0f5677e4cfbe', '湖北',
        ',d018f06a3a194ac68a3d0f5677e4cfbe,', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('4c963d38ae25416ea3c1f141656b8f78', '湖北荆州', 'hb001002', 0, 0, 2, 2, 'd018f06a3a194ac68a3d0f5677e4cfbe', '湖北',
        ',d018f06a3a194ac68a3d0f5677e4cfbe,', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('b18e58a251d6415cb05dd77c2a0fb198', '长沙', 'cs2', 0, 0, 2, 2, '90e56a2a4e6a4add9a5b9a26fc212db2', '湖南',
        ',90e56a2a4e6a4add9a5b9a26fc212db2,', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, grade, parent_id, parent_name, parent_ids, create_by,
                      create_date, update_by, update_date, del_flag)
VALUES ('21173617daf14ae68bfd3b5550ff7264', '长沙1', 'cs3', 0, 0, 8, 3, 'b18e58a251d6415cb05dd77c2a0fb198', '长沙',
        ',90e56a2a4e6a4add9a5b9a26fc212db2,b18e58a251d6415cb05dd77c2a0fb198,', null, '2020-06-25 07:45:59', null,
        '2020-06-25 07:45:59', '0');


-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict
(
    id          varchar(64) NOT NULL COMMENT '主键',
    code        varchar(50) COMMENT '值',
    name        varchar(50) COMMENT '名称',
    type        varchar(255) COMMENT '类型',
    parent_id   varchar(255) COMMENT '父级id',
    description varchar(500) COMMENT '描述',
    grade       int(4) COMMENT '层级',
    order_num   int(4) COMMENT '排序',
    remarks     varchar(500) COMMENT '备注',
    create_by   varchar(64) COMMENT '创建人',
    create_date timestamp  DEFAULT current_timestamp COMMENT '创建时间',
    update_by   varchar(64) COMMENT '更新人',
    update_date timestamp  DEFAULT current_timestamp COMMENT '更新时间',
    del_flag    varchar(4) default '0' COMMENT '删除标记',
    PRIMARY KEY (id) USING BTREE,
    INDEX sys_dict_1 (type) USING BTREE,
    INDEX sys_dict_2 (code) USING BTREE,
  INDEX sys_dict_3(parent_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('023d32e793844de1803407e4dea29ded', 'man', '男', 'sex', '9f4bb4c5e1ea409bb05e8a7668c2da5b', '', 0, '', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('0e1a286107ca4519b3ec43b4a511b49d', 'cs', '测试', 'cs', '0', 'cs', 1, 'cs', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '1');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('1f92faf87c6843b19938e156973a9f69', 'cs1', '测试2', 'cs1', '0', 'cs1', 2, 'cs1', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('6a804fa0642a4e3aa660f21fc443c17f', 'female', '女', 'sex', '9f4bb4c5e1ea409bb05e8a7668c2da5b', 'af', 0, 'af', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('83a28cb2a9574fa6969cb4740177d462', 'cs32', 'cs32', 'cs21', 'dfe403957ef14f6ea47b71c2e7c17fb8', 'cs32', 4, 'cs32', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('9f4bb4c5e1ea409bb05e8a7668c2da5b', 'sex', '性别', 'sex', '0', '', 0, '', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('a9087cc36b504136a9d5790f832eb1b5', '1', 'ce测试', 'cs', 'cebeca4d875b40e0a2f44c7e4843bc23', '擦', 1, 'ca ', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '1');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('acac16f2a6854de489ee72eb839ef607', 'cs2', 'cs2', '1', 'a9087cc36b504136a9d5790f832eb1b5', 'av', 0, 'va', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '1');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('cebeca4d875b40e0a2f44c7e4843bc23', 'cs', '测试', 'cs', '0', 'cs', 3, 'cs', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '1');
INSERT INTO sys_dict (id, code, name, type, parent_id, description, order_num, remarks, create_by, create_date, update_by, update_date, del_flag) VALUES ('dfe403957ef14f6ea47b71c2e7c17fb8', 'cs21', 'cs21', 'cs1', '1f92faf87c6843b19938e156973a9f69', 'cs21', 2, 'cs21', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS sys_log;
CREATE TABLE sys_log  (
  id varchar(64) NOT NULL COMMENT '主键',
  user_id varchar(64)  COMMENT '登录id',
  url varchar(255)  COMMENT 'url',
  ip varchar(255)  COMMENT '登录ip',
  method varchar(255)  COMMENT '请求方法',
  params varchar(255)  COMMENT '请求参数',
  user_agent varchar(255)  COMMENT '登录客户端',
  use_time int(10)  COMMENT '耗时毫秒',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_log_1(user_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS sys_login_log;
CREATE TABLE sys_login_log  (
  id varchar(64) NOT NULL COMMENT '主键',
  user_id varchar(64)  COMMENT '登录id',
  ip varchar(255)  COMMENT '登录ip',
  user_agent varchar(255)  COMMENT '登录客户端',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_login_log_1(user_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志表' ROW_FORMAT = Dynamic;

