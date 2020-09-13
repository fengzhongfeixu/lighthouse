/*
Navicat MySQL Data Transfer

Source Server         : 本机mysql
Source Server Version : 50720
Source Host           : 192.168.88.1:3306
Source Database       : om

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-15 11:19:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for om_blueprint_conf
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_conf`;
CREATE TABLE `om_blueprint_conf` (
  `id` varchar(32) NOT NULL,
  `cname` varchar(255) NOT NULL COMMENT '配置文件名称',
  `content` text NOT NULL COMMENT '配置文件内容',
  `isIdentical` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否同步',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='om-蓝图配置文件信息';

-- ----------------------------
-- Records of om_blueprint_conf
-- ----------------------------

-- ----------------------------
-- Table structure for om_blueprint_log
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_log`;
CREATE TABLE `om_blueprint_log` (
  `hostname` varchar(255) NOT NULL COMMENT '节点主机名',
  `status` varchar(10) NOT NULL COMMENT '请求状态',
  `operation` varchar(255) DEFAULT NULL COMMENT '执行的操作',
  `content` text COMMENT '操作的返回内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_blueprint_log
-- ----------------------------

-- ----------------------------
-- Table structure for om_blueprint_net_traffic
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_net_traffic`;
CREATE TABLE `om_blueprint_net_traffic` (
  `hostId` varchar(255) NOT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `read_num` varchar(255) DEFAULT NULL,
  `write_num` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_blueprint_net_traffic
-- ----------------------------

-- ----------------------------
-- Table structure for om_blueprint_node
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_node`;
CREATE TABLE `om_blueprint_node` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `hostname` varchar(50) NOT NULL COMMENT '节点静态域名',
  `ip` varchar(16) NOT NULL COMMENT '节点ip',
  `port` varchar(5) NOT NULL COMMENT '节点服务端口',
  `role` enum('master','agent') NOT NULL,
  `pid` varchar(10) NOT NULL COMMENT '进程号',
  `username` varchar(50) DEFAULT NULL COMMENT '启动进程的用户',
  `system` varchar(50) DEFAULT NULL COMMENT '节点操作系统',
  `version` varchar(100) DEFAULT NULL COMMENT '节点操作系统版本',
  `status` enum('正常','宕机') NOT NULL COMMENT '当前状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='om-节点列表';

-- ----------------------------
-- Records of om_blueprint_node
-- ----------------------------

-- ----------------------------
-- Table structure for om_blueprint_process
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_process`;
CREATE TABLE `om_blueprint_process` (
  `id` varchar(32) NOT NULL,
  `hostname` varchar(50) NOT NULL COMMENT '主机名',
  `processname` varchar(50) NOT NULL COMMENT '进程名',
  `pid` varchar(10) DEFAULT NULL COMMENT '进程id',
  `isIdentical` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否同步',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机与进程之间的对应关系';

-- ----------------------------
-- Records of om_blueprint_process
-- ----------------------------

-- ----------------------------
-- Table structure for om_blueprint_service
-- ----------------------------
DROP TABLE IF EXISTS `om_blueprint_service`;
CREATE TABLE `om_blueprint_service` (
  `id` varchar(32) NOT NULL,
  `serverName` varchar(50) NOT NULL COMMENT '服务名称',
  `processes` varchar(255) NOT NULL COMMENT '包含的进程集合',
  `isIdentical` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否同步',
  `status` enum('health','weak','died') NOT NULL DEFAULT 'died',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机与进程之间的对应关系';

-- ----------------------------
-- Records of om_blueprint_service
-- ----------------------------

-- ----------------------------
-- Table structure for om_list_config
-- ----------------------------
DROP TABLE IF EXISTS `om_list_config`;
CREATE TABLE `om_list_config` (
  `name` varchar(255) NOT NULL COMMENT '配置文件名称',
  `service` varchar(255) NOT NULL COMMENT '所属服务',
  `category` enum('xml','cfg','text') NOT NULL DEFAULT 'text' COMMENT '配置文件类型',
  `fileDir` varchar(255) NOT NULL COMMENT '文件物理路径',
  `relationProcesses` varchar(255) NOT NULL COMMENT '影响到的进程集合',
  `introduction` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='om-配置文件列表';

-- ----------------------------
-- Records of om_list_config
-- ----------------------------

-- ----------------------------
-- Table structure for om_list_process
-- ----------------------------
DROP TABLE IF EXISTS `om_list_process`;
CREATE TABLE `om_list_process` (
  `name` varchar(255) NOT NULL COMMENT '进程名称',
  `service` varchar(255) NOT NULL COMMENT '所属服务',
  `isMaster` tinyint(1) NOT NULL COMMENT '所属服务',
  `jpsName` varchar(255) NOT NULL COMMENT 'jps中的名称',
  `className` varchar(255) NOT NULL COMMENT '主类全名',
  `orderStart` varchar(255) NOT NULL COMMENT '启动命令',
  `orderStop` varchar(255) NOT NULL COMMENT '停止命令',
  `logDir` varchar(255) NOT NULL COMMENT '日志路径',
  `degree` enum('high','middle','low') NOT NULL COMMENT '描述信息',
  `isInstall` tinyint(1) NOT NULL COMMENT '是否安装此进程',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='om-进程列表';

-- ----------------------------
-- Records of om_list_process
-- ----------------------------

-- ----------------------------
-- Table structure for om_list_service
-- ----------------------------
DROP TABLE IF EXISTS `om_list_service`;
CREATE TABLE `om_list_service` (
  `serverName` varchar(255) NOT NULL COMMENT '服务名称',
  `relyServices` varchar(255) DEFAULT '' COMMENT '依赖的服务',
  `relyConfigs` varchar(255) DEFAULT '' COMMENT '依赖的配置文件',
  `configDir` varchar(255) NOT NULL COMMENT '配置文件根目录',
  `homeDir` varchar(255) NOT NULL COMMENT '服务所包含的进程',
  `configFiles` varchar(255) NOT NULL COMMENT '支持服务的配置文件集合',
  `processes` varchar(255) NOT NULL COMMENT '支持服务的进程',
  `isInstall` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已经安装此服务',
  PRIMARY KEY (`serverName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='om-可用服务';

-- ----------------------------
-- Records of om_list_service
-- ----------------------------

-- ----------------------------
-- Table structure for om_monitor_message
-- ----------------------------
DROP TABLE IF EXISTS `om_monitor_message`;
CREATE TABLE `om_monitor_message` (
  `hostname` varchar(255) NOT NULL COMMENT '节点名称',
  `project` varchar(255) NOT NULL COMMENT '指标名称',
  `number` double NOT NULL COMMENT '指标数值',
  `unit` varchar(255) NOT NULL COMMENT '指标单位',
  `time` datetime NOT NULL COMMENT '记录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_monitor_message
-- ----------------------------

-- ----------------------------
-- Table structure for om_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `om_sys_log`;
CREATE TABLE `om_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` datetime(6) NOT NULL,
  `message` text NOT NULL,
  `level_string` varchar(254) NOT NULL,
  `logger_name` varchar(254) NOT NULL,
  `thread_name` varchar(254) DEFAULT NULL,
  `caller_filename` varchar(254) NOT NULL,
  `caller_class` varchar(254) NOT NULL,
  `caller_method` varchar(254) NOT NULL,
  `caller_line` char(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for om_sys_overview
-- ----------------------------
DROP TABLE IF EXISTS `om_sys_overview`;
CREATE TABLE `om_sys_overview` (
  `hostId` varchar(255) NOT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `read_num` varchar(255) DEFAULT NULL,
  `write_num` varchar(255) DEFAULT NULL,
  `disk` varchar(255) DEFAULT NULL,
  `disk_used` varchar(255) DEFAULT NULL,
  `memory` varchar(255) DEFAULT NULL,
  `memory_used` varchar(255) DEFAULT NULL,
  `cpu_usage` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_sys_overview
-- ----------------------------

-- ----------------------------
-- Table structure for om_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `om_sys_user`;
CREATE TABLE `om_sys_user` (
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `role` varchar(255) NOT NULL COMMENT '角色名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `updatetime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of om_sys_user
-- ----------------------------
INSERT INTO `om_sys_user` VALUES ('admin', '运维总监', 'd38758282f28c5d410f41f5522cb18106746e0115c60fa6c', '管理员', 'gaoshq@sugon.com', '2019-11-04 14:53:42', '2019-11-04 14:54:25');
