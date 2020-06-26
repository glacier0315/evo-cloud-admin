-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details  (
  client_id varchar(256) NOT NULL,
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity int(11),
  refresh_token_validity int(11),
  additional_information varchar(4096),
  autoapprove varchar(256),
  PRIMARY KEY (client_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权客户端' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('app', 'rid', '$2a$10$GOzKSM9MR0OQMl32hiYp9uNUxnfk4u1dSnHwKrcWnNiJyn.5apB1G', 'all', 'authorization_code,refresh_token,password', 'https://www.getpostman.com/oauth2/callback', null, 3600, 3600, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('auth-client', null, '$2a$10$oK1FopDCVsaG76.p8YFRcuuSbqsKbayv9v7NlViAjJaK3HlU1LT16', 'all', 'authorization_code,client_credentials,refresh_token,password', 'http://192.168.10.1:8530/login/oauth2/code/uas', null, 3600, 3600, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('eboot-sys', null, '$2a$10$pq1Jjw6cM.Rj3ZZhBOhvMOTEiTxWOuizkXn7RDbb9r2ik1isBK20S', 'all', 'authorization_code,client_credentials,refresh_token,password', 'http://localhost:8081/login/oauth2/code/uas', null, 3600, 36000, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('order-client', null, '$2a$10$q7R4ap1/Qi3sXp9VKOKpzORD4DWAA2ol9lgg1XsO37cI5TIONTPhK', 'all', 'authorization_code,refresh_token,password', 'http://192.168.10.1:8530', null, 3600, 36000, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('user-client', null, '$2a$10$v6.UycDNQSzZtvV9jjWQo.JTepd4d4j2fVxSK8Qyj7QVQqMrp8wWu', 'all', 'authorization_code,refresh_token,password', 'http://192.168.10.1:8530', null, 3600, 36000, null, '1');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code  (
  code varchar(255),
  authentication blob NULL,
  INDEX code_index(code) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权码' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user  (
  id varchar(64) NOT NULL COMMENT '主键',
  username varchar(200)  COMMENT '用户名',
  password varchar(255)  COMMENT '密码',
  nickname varchar(200)  COMMENT '昵称',
  salt varchar(255)  COMMENT '盐值',
  id_card varchar(20)  COMMENT '身份证号',
  birthday date  COMMENT '生日',
  sex int(4) COMMENT '性别',
  email varchar(100)  COMMENT '邮箱',
  mobile varchar(20)  COMMENT '手机号',
  dept_id varchar(64)  COMMENT '单位',
  status varchar(4)  COMMENT '状态',
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
INSERT INTO sys_user (id, username, password, nickname, salt, id_card, birthday, sex, email, mobile, dept_id, status, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', 'admin', '$2a$10$RCvUyaqalDebEuYPVSOqCOlpRBO9hfslhp4SEN1dK/kJcc0FYY0aW', '', null, '11111111111111111111', null, null, '14151', '1414', '4c963d38ae25416ea3c1f141656b8f78', '1', null, '2019-11-07 17:01:42', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_user (id, username, password, nickname, salt, id_card, birthday, sex, email, mobile, dept_id, status, create_by, create_date, update_by, update_date, del_flag) VALUES ('c35d5b94aca547799fbe56ddd90bcfc4', 'ceshi', '$2a$10$RP6MGP8lFqKhh0.2/SroKepKV25y8u2XSjvD8dzRXBzwAZGZYSYl.', '', null, null, null, null, '14151', '1414', '4c963d38ae25416ea3c1f141656b8f78', '1', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu  (
  id varchar(64) NOT NULL DEFAULT '1' COMMENT '主键',
  name varchar(200)  COMMENT '名称',
  path varchar(255)  COMMENT '端点路径',
  component varchar(255)  COMMENT '组件',
  icon varchar(255)  COMMENT '图标',
  type int(4) DEFAULT 1 COMMENT '类型',
  visible int(4) DEFAULT 1 COMMENT '1 显示  2 隐藏',
  is_frame int(4) DEFAULT 2 COMMENT '是否外链 1 是  2 否',
  permission varchar(255)  COMMENT '权限',
  parent_id varchar(255)  COMMENT '父级id',
  order_num int(4)  COMMENT '排序号',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp  COMMENT '更新时间',
  del_flag varchar(4) default '0' COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_menu_1(parent_id) USING BTREE,
  INDEX sys_menu_2(type) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', 'permission', '/permission', 'Layout', 'lock', 1, 1, 2, null, null, 1, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('2', 'PagePermission', '/permission/page', 'permission/page', null, 2, 1, 2, null, '1', 2, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('3', 'directive', '/permission/directive', 'permission/directive', null, 2, 1, 2, null, '1', 3, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_menu (id, name, path, component, icon, type, visible, is_frame, permission, parent_id, order_num, create_by, create_date, update_by, update_date, del_flag) VALUES ('4', 'role', '/permission/role', 'permission/role', null, 2, 1, 2, null, '1', 4, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

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
INSERT INTO sys_role (id, name, code, status, description, create_by, create_date, update_by, update_date, del_flag) VALUES ('1', '超级管理员', 'admin', '1', null, null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

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
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('1', '1', '1');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('2', '1', '2');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('3', '1', '3');
INSERT INTO sys_role_menu (id, role_id, menu_id) VALUES ('4', '1', '4');

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
CREATE TABLE sys_dept  (
  id varchar(64) NOT NULL COMMENT '主键',
  name varchar(200)  COMMENT '名称',
  code varchar(255)  COMMENT '编码',
  type int(4)  COMMENT '类型',
  status int(4)  COMMENT '状态',
  order_num int(4)  COMMENT '排序号',
  level int(4)  COMMENT '层级',
  parent_id varchar(255)  COMMENT '父级id',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp  COMMENT '更新时间',
  del_flag varchar(4) default '0'  COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_dept_1(parent_id) USING BTREE,
  INDEX sys_dept_2(type) USING BTREE,
  INDEX sys_dept_3(status) USING BTREE,
  INDEX sys_dept_4(code) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('21173617daf14ae68bfd3b5550ff7264', '测试3', 'cs3', 0, 0, 8, 2, 'b18e58a251d6415cb05dd77c2a0fb198', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('4c963d38ae25416ea3c1f141656b8f78', '湖北英泽', 'hb001002', 0, 0, 2, 0, 'd018f06a3a194ac68a3d0f5677e4cfbe', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('90e56a2a4e6a4add9a5b9a26fc212db2', '测试1', 'cs1', 0, 0, 3, 0, '0', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('b18e58a251d6415cb05dd77c2a0fb198', '测试2', 'cs2', 0, 0, 2, 0, '90e56a2a4e6a4add9a5b9a26fc212db2', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('ca06d9feadf143a7adb7977923e4e3cc', '湖北科尔', 'hb001001', 0, 0, 1, 0, 'd018f06a3a194ac68a3d0f5677e4cfbe', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');
INSERT INTO sys_dept (id, name, code, type, status, order_num, level, parent_id, create_by, create_date, update_by, update_date, del_flag) VALUES ('d018f06a3a194ac68a3d0f5677e4cfbe', '湖北软件', 'hb001', 0, 0, 0, 0, '0', null, '2020-06-25 07:45:59', null, '2020-06-25 07:45:59', '0');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict  (
  id varchar(64) NOT NULL COMMENT '主键',
  code varchar(50)  COMMENT '值',
  name varchar(50)  COMMENT '名称',
  type varchar(255)  COMMENT '类型',
  parent_id varchar(255)  COMMENT '父级id',
  description varchar(500)  COMMENT '描述',
  order_num int(4)  COMMENT '排序',
  remarks varchar(500)  COMMENT '备注',
  create_by varchar(64)  COMMENT '创建人',
  create_date timestamp DEFAULT current_timestamp  COMMENT '创建时间',
  update_by varchar(64)  COMMENT '更新人',
  update_date timestamp DEFAULT current_timestamp  COMMENT '更新时间',
  del_flag varchar(4) default '0'  COMMENT '删除标记',
  PRIMARY KEY (id) USING BTREE,
  INDEX sys_dict_1(type) USING BTREE,
  INDEX sys_dict_2(code) USING BTREE,
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

