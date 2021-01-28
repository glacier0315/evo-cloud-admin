CREATE DATABASE eboot_gen CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

CREATE USER 'eboot_gen'@'%'  IDENTIFIED BY 'eboot_gen';

grant all privileges on eboot_gen.* to 'eboot_gen'@'%';

flush privileges;


use eboot_gen;

-- ----------------------------
-- Table structure for gen_datasource
-- ----------------------------
DROP TABLE IF EXISTS gen_datasource;
CREATE TABLE gen_datasource (
    id                      varchar(64) NOT NULL COMMENT '主键',
    name                    varchar(64) DEFAULT '' comment '数据源名称',
    driver_class_name       varchar(200) DEFAULT '' comment '数据源驱动',
    url                     varchar(255) DEFAULT '' comment 'jdbc url',
    username                varchar(64) DEFAULT '' comment '数据源用户名',
    password                varchar(64) DEFAULT '' comment '数据源密码',
    validation_query        varchar(64) DEFAULT '' comment '数据源连接检测',
    create_by               varchar(64)  default '' COMMENT '创建人',
    create_date             timestamp DEFAULT current_timestamp COMMENT '创建时间',
    update_by               varchar(64)  default '' COMMENT '更新人',
    update_date             timestamp DEFAULT current_timestamp COMMENT '更新时间',
    del_flag                varchar(4) default '0'  COMMENT '删除标记',
    primary key (id)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';


-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
drop table if exists gen_table;
create table gen_table
(
    id                  varchar(64) NOT NULL COMMENT '主键',
    datasource_id      varchar(200) default '' comment '数据源id',
    table_name          varchar(200) default '' comment '表名称',
    table_comment       varchar(500) default '' comment '表描述',
    class_name          varchar(100) default '' comment '实体类名称',
    template_category   varchar(200) default 'crud' comment '使用的模板（crud单表操作 tree树表操作）',
    package_name        varchar(100) default '' comment '生成包路径',
    module_name         varchar(30) default '' comment '生成模块名',
    business_name       varchar(30) default '' comment '生成业务名',
    function_name       varchar(50) default '' comment '生成功能名',
    function_author     varchar(50) default '' comment '生成功能作者',
    options             varchar(1000) default '' comment '其它生成选项',
    create_by           varchar(64)  default '' COMMENT '创建人',
    create_date         timestamp DEFAULT current_timestamp COMMENT '创建时间',
    update_by           varchar(64)  default '' COMMENT '更新人',
    update_date         timestamp DEFAULT current_timestamp COMMENT '更新时间',
    del_flag            varchar(4) default '0'  COMMENT '删除标记',
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column
(
    id             varchar(64) NOT NULL COMMENT '主键',
    table_id       varchar(64) default '' comment '归属表编号',
    column_name    varchar(200) default '' comment '列名称',
    column_comment varchar(500) default '' comment '列描述',
    column_type    varchar(100) default '' comment '列类型',
    java_type      varchar(500) default '' comment 'JAVA类型',
    java_field     varchar(200) default '' comment 'JAVA字段名',
    is_pk          char(1) default '' comment '是否主键（1是 2否）',
    is_required    char(1) default '' comment '是否必填（1是 2否）',
    is_insert      char(1) default '' comment '是否为插入字段（1是 2否）',
    is_edit        char(1) default '' comment '是否编辑字段（1是 2否）',
    is_list        char(1) default '' comment '是否列表字段（1是 2否）',
    is_query       char(1) default '' comment '是否查询字段（1是 2否）',
    query_type     varchar(200) default 'EQ' comment '查询方式（等于、不等于、大于、小于、范围）',
    html_type      varchar(200) default '' comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    dict_type      varchar(200) default '' comment '字典类型',
    sort           int default 0 comment '排序',
    create_by      varchar(64) default ''  COMMENT '创建人',
    create_date    timestamp DEFAULT current_timestamp COMMENT '创建时间',
    update_by      varchar(64) default ''  COMMENT '更新人',
    update_date    timestamp DEFAULT current_timestamp COMMENT '更新时间',
    del_flag       varchar(4) default '0'  COMMENT '删除标记',
    primary key (id)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;
