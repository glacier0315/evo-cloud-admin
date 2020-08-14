# eboot-platform
## 平台简介

* 采用前后端分离的模式，后端采用微服务。
* 后端采用Spring Boot、Spring Cloud & Alibaba。
* 注册中心、配置中心选型Nacos，为权限认证使用OAuth2。
* 注册中心、配置中心选型Nacos，为权限认证使用OAuth2。

## 系统模块

~~~
com.glacier     
├── gateway                 // 网关模块
│       └── common          // 网关模块 [8000]
├── authorization           // 认证模块
│       └── server          // 认证中心 [8001]
│       └── resource        // 认证资源服务示例 [随机]
├── common                  // 通用模块
│       └── core            // 核心模块
├── modules                 // 业务模块
│       └── sys             // 系统模块 [随机]
│       └── fdfs            // 文件模块 [随机]
│       └── gen             // 代码生成 [随机]
├── monitor                 // 图形化监控模块
│       └── admin-server    // 监控中心 [随机]
├──build.gradle             // 依赖配置
├──settings.gradle          // 全局配置
~~~


## 内置功能

1.  用户管理：用于系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
8.  登录日志：系统登录日志记录查询包含登录异常。
9.  代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
10. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
11. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
