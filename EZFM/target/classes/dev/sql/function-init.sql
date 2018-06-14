DROP TABLE IF EXISTS  yjwy_pub_function;
CREATE TABLE
    yjwy_pub_function
    (
        id_ CHAR(20) NOT NULL,
        pid_ VARCHAR(50),
        name_ VARCHAR(50),
        code_ VARCHAR(50),
        url_ VARCHAR(300),
        type_ VARCHAR(50),
        memo_ VARCHAR(50),
        icon_ VARCHAR(50),
        order_ INT,
        PRIMARY KEY (id_)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000008', 'root', '系统管理', 'SYS|', null, 'menu', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000009', 'root', '基础设置', 'JCSZ|', null, 'menu', '基础设置模块菜单', 'yjwy_fun_baseinfo1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000A', 'root', '品质核查', 'PZHZ|', null, 'menu', '品质核查模块菜单', 'yjwy_fun_quality1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000B', 'root', '设备管理', 'SBGL|', null, 'menu', null, 'yjwy_fun_device1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000C', 'root', '报事管理', 'BS|', null, 'menu', null, 'yjwy_fun_problem1', 100);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000D', 'root', '工单管理', 'GD|', null, 'menu', null, 'yjwy_fun_worktask_1', 100);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000E', 'root', '绩效管理', 'JS|', null, 'menu', null, 'yjwy_fun_defult1', 200);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000F', '00000020160921000008', '数据字典管理', 'SYS|01|', 'yjwy/system/dict/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000G', '00000020160921000008', '个人信息维护', 'SYS|02', 'yjwy/system/user_update/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000H', '00000020160921000009', '区域管理', 'JCSZ|01|', 'yjwy/baseinfo/area/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000I', '00000020160921000009', '项目管理', 'yjwy_project', 'yjwy/baseinfo/project/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000J', '00000020160921000009', '组织管理', 'yjwy_org', 'yjwy/baseinfo/org/index', 'function', null, 'yjwy_fun_baseinfo1_1', 5);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000K', '00000020160921000009', '岗位管理', 'yjwy_station', 'yjwy/baseinfo/station/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000L', '00000020160921000009', '资源管理', 'yjwy_resources', 'yjwy/baseinfo/resources/index', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000M', '00000020160921000008', '授权管理', 'yjwy_role', 'yjwy/baseinfo/role/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000N', '00000020160921000009', '用户管理', 'yjwy_user', 'yjwy/baseinfo/user/index', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000O', '0000002016092100000A', '数据统计', 'project_project_statistics', null, 'menu', null, 'yjwy_fun_quality1_2', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000P', '0000002016092100000O', '人员数据统计表', 'quality_renyuan', 'yjwy/quality/datastatistics/personnel/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000Q', '0000002016092100000O', '人员整改数据表', 'quality_ry_zhenggai', 'yjwy/quality/datastatistics/persrectification/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000R', '0000002016092100000O', '项目数据统计', 'quality_xiangmushujutj', 'yjwy/quality/datastatistics/projectdata/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000S', '0000002016092100000O', '整改率统计', 'quality_zhenggailv', 'yjwy/quality/datastatistics/rectification/index', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000T', '0000002016092100000O', '项目整改数据表', 'project_xiangmuzgsjb', 'yjwy/quality/datastatistics/projectrectify/index', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000U', '0000002016092100000A', '项目核查', 'project_project_inspect', null, 'menu', null, 'yjwy_fun_quality1_1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000V', '0000002016092100000U', '标准版本', 'standard_edition', 'yjwy/quality/proinspect/inspect/stanedition/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000W', '0000002016092100000U', '核查标准', 'inspect|standard', 'yjwy/quality/proinspect/inspect/standard/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000X', '0000002016092100000U', '核查任务', 'quality_hecharenwu', 'yjwy/quality/proinspect/inspect/insptask/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000Y', '0000002016092100000U', '核查完成记录', 'quality_heawanchengjilu', ' yjwy/quality/proinspect/inspect/insprecord/index/finish', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100000Z', '0000002016092100000U', '核查整改记录', 'quality_heazhenggaijilu', 'yjwy/quality/proinspect/inspect/insprecord/index/rectiry', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000010', '0000002016092100000U', '问题类型', 'quality_wentileixing', 'yjwy/quality/proinspect/inspect/probtype/index', 'function', null, 'yjwy_fun_defult1', 60);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000011', '0000002016092100000B', '设备概况', 'device_a_rough', 'yjwy/device/rough/index', 'function', null, 'yjwy_fun_device1_1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000012', '0000002016092100000B', '设备台账', 'device_b_account', null, 'menu', null, 'yjwy_fun_device1_2', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000013', '00000020160921000012', '设备档案', 'device_list', 'yjwy/device/list/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000014', '00000020160921000012', '设备预警列表', 'device_warning', 'yjwy/device/warn/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000015', '0000002016092100000B', '巡检管理', 'device_c_check', null, 'menu', null, 'yjwy_fun_device1_3', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000016', '00000020160921000015', '巡检计划', 'device_checkplan', 'yjwy/patrol/plan/checkindex', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000017', '00000020160921000015', '巡检记录', 'device_checkrecord', 'yjwy/patrol/record/checkindex', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000018', '00000020160921000015', '巡检任务', 'device_checktask', 'yjwy/patrol/task/checkindex', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000019', '0000002016092100000B', '维保管理', 'device_d_maint', null, 'menu', null, 'yjwy_fun_device1_4', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001A', '00000020160921000019', '维保计划', 'device_maintplan', 'yjwy/patrol/plan/maintindex', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001B', '00000020160921000019', '维保记录', 'device_maintrecord', 'yjwy/patrol/record/maintindex', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001C', '00000020160921000019', '维保任务', 'device_mainttask', 'yjwy/patrol/task/maintindex', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001D', '0000002016092100000B', '基础设置', 'device_e_basic', null, 'menu', null, 'yjwy_fun_device1_5', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001E', '0000002016092100001D', '项目关联', 'device_cognate', 'yjwy/device/project/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001F', '0000002016092100001D', '人员分组', 'device_executor', 'yjwy/basic/executor/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001G', '0000002016092100001D', '数据同步', 'device_fmsynchro', 'yjwy/device/fmdata/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001H', '0000002016092100000C', '基础设置', 'yjwy_problem_basics', null, 'menu', null, 'yjwy_fun_worktask1_3', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001I', '0000002016092100000C', '报事操作', 'yjwy_problem_operation', null, 'menu', null, 'yjwy_fun_problem1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001J', '0000002016092100000C', '报事统计', 'yjwy_problem_statistics', null, 'menu', null, 'yjwy_fun_problem1_3', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001K', '0000002016092100001H', '报事分类', 'yjwy_problem_class', 'yjwy/problem/classadmin/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001L', '0000002016092100001H', '项目定义', 'yjwy_problem_project_info', 'yjwy/nexus/proandclass/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001M', '0000002016092100001I', '待办任务', 'yjwy_details_1', 'yjwy/problem/details/index?operation_flag=1', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001N', '0000002016092100001I', '全部任务', 'yjwy_details_2', 'yjwy/problem/details/index?operation_flag=2', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001O', '0000002016092100001I', '处理中', '处理中', 'yjwy/problem/details/index?operation_flag=3', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001P', '0000002016092100001I', '已完成待回访', 'yjwy_details_4', 'yjwy/problem/details/index?operation_flag=4', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001Q', '0000002016092100001I', '已回访', 'yjwy_details_5', 'yjwy/problem/details/index?operation_flag=5', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001R', '0000002016092100001J', '报事及时率统计表', 'problem_statistics_regularly', 'yjwy/problem/regularly/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001S', '0000002016092100000D', '基础设置', 'yjwy_work_task_basics', null, 'menu', null, 'yjwy_fun_worktask1_3', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001T', '0000002016092100000D', '工单处理', 'yjwy_work_task_handle', null, 'menu', null, 'yjwy_fun_problem1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001U', '0000002016092100000D', '工单统计', 'work_task_statistics', null, 'menu', null, 'yjwy_fun_worktask1_2', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001V', '0000002016092100001S', '维修种类', 'yjwy_work_task_repair_class', 'yjwy/orktask/repairclass/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001W', '0000002016092100001S', '片区人员', 'yjwy_work_task_area_user', 'yjwy/worktask/areapersonnel/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001X', '0000002016092100001S', '人员定义', 'work_task_user_definition', 'yjwy/worktask/projectuser/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001Y', '0000002016092100001S', '片区管理', 'yjwy_work_task_area_details', 'yjwy/worktask/areadetails/index', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('0000002016092100001Z', '0000002016092100001T', '待办任务', 'yjwy_work_ task_1', 'yjwy/worktask/details/index?operation_flag=1', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000020', '0000002016092100001T', '全部任务', 'yjwy_work_ task_2', 'yjwy/worktask/details/index?operation_flag=2', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000021', '0000002016092100001T', '待接单', 'yjwy_work_ task_3', 'yjwy/worktask/details/index?operation_flag=3', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000022', '0000002016092100001T', '维修中', 'yjwy_work_ task_4', 'yjwy/worktask/details/index?operation_flag=4', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000023', '0000002016092100001T', '已完成', 'yjwy_work_ task_5', 'yjwy/worktask/details/index?operation_flag=5', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000024', '0000002016092100001U', '片区维修种类统计表', 'work_s_maintclass', 'yjwy/worktask/maintclass/index', 'function', null, 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000025', '0000002016092100001U', '维修单量工时统计表', 'work_s_manhours', 'yjwy/worktask/manhours/index', 'function', null, 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000026', '0000002016092100001U', '抢派单情况对比表', 'work_s_ordermatter', 'yjwy/worktask/ordermatter/index', 'function', null, 'yjwy_fun_defult1', 30);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000027', '0000002016092100001U', '绩效考核统计表', 'work_s_performance', 'yjwy/worktask/performance/index', 'function', null, 'yjwy_fun_defult1', 40);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('00000020160921000028', '0000002016092100001U', '工单明细表', 'work_s_workorder', 'yjwy/worktask/workorder/index', 'function', null, 'yjwy_fun_defult1', 50);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('000000201609210005CZ', '0000002016092100000E', '休假备案', 'performance_leave', 'yjwy/performance/leave/index', 'function', '绩效管理下面的休假备案', 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('000000201609210005D0', '0000002016092100000E', '签到管理', 'performance_sign', 'yjwy/performance/sign/index', 'function', '绩效管理下的签到管理', 'yjwy_fun_defult1', 20);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('crop_initfunction', 'crop_menu', '企业初始化', 'CROP|01|', 'yjwy/system/crop/index', 'corp_function', '企业初始化管理员专用', 'yjwy_fun_defult1', 0);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('crop_menu', 'root', '企业接入', 'CROP|', '', 'corp_menu', '企业初始化管理员专用', 'yjwy_fun_defult1', 10);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('dms_sysfunction', 'dms_sysmenu', '功能管理', 'DMS|01|', 'yjwy/system/function/index', 'dms_function', '达美盛系统管理员专用', 'yjwy_fun_defult1', 0);
INSERT INTO yjwy_pub_function (id_, pid_, name_, code_, url_, type_, memo_, icon_, order_) VALUES ('dms_sysmenu', 'root', '达美盛应用', 'DMS|', '', 'dms_menu', '达美盛系统管理员专用', 'yjwy_fun_defult1', 0);

