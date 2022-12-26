/*
 Navicat Premium Data Transfer

 Source Server         : 哈尔滨广厦学院0418服务器
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : 172.20.0.147:3306
 Source Schema         : zuioj

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 26/11/2022 10:37:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ac_contest_question
-- ----------------------------
DROP TABLE IF EXISTS `ac_contest_question`;
CREATE TABLE `ac_contest_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) DEFAULT NULL COMMENT '竞赛id',
  `question_id` bigint(20) DEFAULT NULL COMMENT '题目id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `number_of_attempts` int(11) DEFAULT NULL COMMENT '尝试次数',
  `first_ac` datetime DEFAULT NULL COMMENT '第一次ac时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建人时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人name',
  `update_time` datetime DEFAULT NULL COMMENT '修改人时间',
  `status` int(2) DEFAULT NULL COMMENT '状态 0 AC 1 war 2警告 3取消资格',
  `del_flag` int(2) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `key_question_id` (`question_id`) USING BTREE,
  KEY `key_user_id` (`user_id`) USING BTREE,
  KEY `questionanduserid` (`question_id`,`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ac_contest_question
-- ----------------------------
BEGIN;
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (31, 28, 25, 667, 2, '2022-11-15 19:00:30', 667, '刘朝甲', '2022-11-15 19:00:30', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (32, 28, 26, 667, 0, '2022-11-15 19:02:51', 667, '刘朝甲', '2022-11-15 19:02:51', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (33, 28, 10001, 667, 0, '2022-11-15 19:03:00', 667, '刘朝甲', '2022-11-15 19:03:00', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (34, 28, 10001, 666, 0, '2022-11-15 19:03:38', 666, '刘港', '2022-11-15 19:03:38', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (35, 28, 26, 666, 0, '2022-11-15 19:03:44', 666, '刘港', '2022-11-15 19:03:44', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (36, 28, 25, 666, 1, '2022-11-15 19:04:12', 666, '刘港', '2022-11-15 19:03:50', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (37, 28, 25, 1, 0, '2022-11-15 19:04:59', 1, '杨易达(Bryan yang)', '2022-11-15 19:04:59', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (38, 28, 26, 1, 0, '2022-11-15 19:05:13', 1, '杨易达(Bryan yang)', '2022-11-15 19:05:13', NULL, NULL, NULL, 1, 0);
INSERT INTO `ac_contest_question` (`id`, `contest_id`, `question_id`, `user_id`, `number_of_attempts`, `first_ac`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (39, 28, 10001, 1, 0, '2022-11-15 19:05:27', 1, '杨易达(Bryan yang)', '2022-11-15 19:05:27', NULL, NULL, NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for ac_question
-- ----------------------------
DROP TABLE IF EXISTS `ac_question`;
CREATE TABLE `ac_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `questionId` bigint(20) DEFAULT NULL COMMENT '题目id',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建人时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人name',
  `update_time` datetime DEFAULT NULL COMMENT '修改人时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `del_flag` int(2) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ac_question
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for competition_info
-- ----------------------------
DROP TABLE IF EXISTS `competition_info`;
CREATE TABLE `competition_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) NOT NULL COMMENT '比赛id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_by_name` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(0) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人name',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `del_flag` int(2) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='参赛信息';

-- ----------------------------
-- Records of competition_info
-- ----------------------------
BEGIN;
INSERT INTO `competition_info` (`id`, `contest_id`, `user_id`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (17, 28, 1, 1, '杨易达(Bryan yang)', '2022-11-15 19:02:15', NULL, NULL, NULL, 0, 0);
INSERT INTO `competition_info` (`id`, `contest_id`, `user_id`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (18, 28, 666, 666, 'Liugang', '2022-11-15 19:01:53', NULL, NULL, NULL, 0, 0);
INSERT INTO `competition_info` (`id`, `contest_id`, `user_id`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (19, 28, 667, 667, 'Liuchaogang', '2022-11-15 18:59:08', NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for competition_question_bank
-- ----------------------------
DROP TABLE IF EXISTS `competition_question_bank`;
CREATE TABLE `competition_question_bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL COMMENT '题目id',
  `contest_id` bigint(20) DEFAULT NULL COMMENT '比赛id',
  PRIMARY KEY (`id`),
  KEY `keys` (`question_id`,`contest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of competition_question_bank
-- ----------------------------
BEGIN;
INSERT INTO `competition_question_bank` (`id`, `question_id`, `contest_id`) VALUES (2, 25, 28);
INSERT INTO `competition_question_bank` (`id`, `question_id`, `contest_id`) VALUES (1, 26, 28);
INSERT INTO `competition_question_bank` (`id`, `question_id`, `contest_id`) VALUES (3, 10001, 28);
COMMIT;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '考试昵称',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `parsing_rule` int(3) DEFAULT NULL COMMENT '解析规则',
  `contest_type` int(3) DEFAULT NULL COMMENT '比赛类别',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `post_match_processing` int(2) DEFAULT NULL COMMENT '赛后处理',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '修改name',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) DEFAULT NULL,
  `del_flag` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of contest
-- ----------------------------
BEGIN;
INSERT INTO `contest` (`id`, `name`, `start_time`, `parsing_rule`, `contest_type`, `end_time`, `post_match_processing`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (17, '123', '2022-11-03 13:48:00', 1, 1, '2022-11-03 13:48:00', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `contest` (`id`, `name`, `start_time`, `parsing_rule`, `contest_type`, `end_time`, `post_match_processing`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (18, '123', '2022-11-03 13:48:00', 1, 1, '2022-11-03 13:48:00', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `contest` (`id`, `name`, `start_time`, `parsing_rule`, `contest_type`, `end_time`, `post_match_processing`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (28, '哈尔滨广厦学院信息学院专业社团周赛第一次周赛', '2022-11-15 18:59:50', 1, 1, '2022-12-02 22:11:40', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for contest_detailed
-- ----------------------------
DROP TABLE IF EXISTS `contest_detailed`;
CREATE TABLE `contest_detailed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) DEFAULT NULL COMMENT '比赛id',
  `md_text` mediumtext CHARACTER SET utf8 COLLATE utf8_bin,
  `html_text` mediumtext CHARACTER SET utf8 COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of contest_detailed
-- ----------------------------
BEGIN;
INSERT INTO `contest_detailed` (`id`, `contest_id`, `md_text`, `html_text`) VALUES (20, 28, '欢迎来到第 专业社团周测 竞赛\n\n此比赛只为 **哈尔滨广厦学院信息学院专业社团** 提供服务\n\n本场竞赛由「 哈尔滨广厦学院信息学院专业社团TLM Team & 哈尔滨广厦学院信息学院专业社团」联合主办\n\n【实物奖励】\n\n排名第 1  名的参赛者可获得神秘奖品\n\n本场竞赛奖品将于比赛后 20 个工作日 内进行发放，请获奖选手多加关注**信息学院专业社团** System通知。如遇疫情或不可抗力等因素，奖品可能发生调换，敬请谅解。\n\n** 重要提示**\n**信息学院专业社团**一向非常重视竞赛的公平和公正。为保障每一位参赛者的权益，给大家带来更好的竞赛体验，营造健康积极的竞赛环境，我们对竞赛规则进行了更详细的约定和规范，点击 [查看全部](查看全部)。\n请注意，每个错误提交的惩罚时间为 5 分钟 。\n为保障竞赛的公平性，**信息学院专业社团**将在竞赛中隐藏部分用例。当参赛者在竞赛中提交未通过时，**信息学院专业社团**不会显示给参赛者被隐藏的错误用例。\n本场竞赛的最终排名会在竞赛结束后的 **15 个工作日**内确认。\n**竞赛中的违规行为：**\n一人使用多账号提交\n多账号提交雷同代码（抄袭，此系统包含查重模式）\n使用不正当手段影响他人竞赛的\n竞赛结束前在讨论区发布答案的\n使用百度、Google搜索引擎查看答案，此网页会开启录屏模式。\n如有用户被检查出竞赛中存在违规行为，**信息学院专业社团**会坚持以 零容忍 的态度维护竞赛的 公平、公正，严格按照以下处罚规则执行：\n\n第一次违规：账号封禁 1 个月，**以后所有TLMOJ承办的比赛平台都将无法参赛**\n第二次违规：**永久封号**，从此此用户信息无法注册TLMOJ系统\n同时我们也鼓励大家共同维护竞赛的公平和公正，我们会给于举报成功的用户额外的奖励，我们会保证用户的隐私，为了保证您的信息隐私，我们会向您展示我们接口源码，绝不会透露您的信息。\n\n~~~java\n    /**\n     * @author (Bryan yang)杨易达\n     * 比赛选手举报\n     * @param reportMessageText 举报信息文本\n     * @return\n     */\n    @ApiOperation(\"比赛选手举报\")\n    @GetMapping(\"/competitorReport\")\n    public String competitorReport(@RequestParam(\"reportMessageText\") String reportMessageText){\n        return conTestSerivce.competitorReport(reportMessageText);\n    }\n~~~\n\n\n另外，为了保证竞赛的公正、公平，请勿在竞赛结束前在讨论区发布、讨论可能会影响竞赛正常进行的内容，包括且不限于发布竞赛题答案、解题思路、方法、提醒注意等，社区管理员将有权根据实际情况予以警告、删除、禁言、封禁其相应帐号的功能，情节严重者，将酌情封禁对应 IP。\n\n**通知**\n您必须先 **报名** 后方能参加该场竞赛。我们祝愿您参赛愉快！祝你好运！', '<p>欢迎来到第 专业社团周测 竞赛</p>\n<p>此比赛只为 <strong>哈尔滨广厦学院信息学院专业社团</strong> 提供服务</p>\n<p>本场竞赛由「 哈尔滨广厦学院信息学院专业社团TLM Team &amp; 哈尔滨广厦学院信息学院专业社团」联合主办</p>\n<p>【实物奖励】</p>\n<p>排名第 1  名的参赛者可获得神秘奖品</p>\n<p>本场竞赛奖品将于比赛后 20 个工作日 内进行发放，请获奖选手多加关注<strong>信息学院专业社团</strong> System通知。如遇疫情或不可抗力等因素，奖品可能发生调换，敬请谅解。</p>\n<p>** 重要提示**<br />\n<strong>信息学院专业社团</strong>一向非常重视竞赛的公平和公正。为保障每一位参赛者的权益，给大家带来更好的竞赛体验，营造健康积极的竞赛环境，我们对竞赛规则进行了更详细的约定和规范，点击 <a href=\"%E6%9F%A5%E7%9C%8B%E5%85%A8%E9%83%A8\" target=\"_blank\">查看全部</a>。<br />\n请注意，每个错误提交的惩罚时间为 5 分钟 。<br />\n为保障竞赛的公平性，<strong>信息学院专业社团</strong>将在竞赛中隐藏部分用例。当参赛者在竞赛中提交未通过时，<strong>信息学院专业社团</strong>不会显示给参赛者被隐藏的错误用例。<br />\n本场竞赛的最终排名会在竞赛结束后的 <strong>15 个工作日</strong>内确认。<br />\n<strong>竞赛中的违规行为：</strong><br />\n一人使用多账号提交<br />\n多账号提交雷同代码（抄袭，此系统包含查重模式）<br />\n使用不正当手段影响他人竞赛的<br />\n竞赛结束前在讨论区发布答案的<br />\n使用百度、Google搜索引擎查看答案，此网页会开启录屏模式。<br />\n如有用户被检查出竞赛中存在违规行为，<strong>信息学院专业社团</strong>会坚持以 零容忍 的态度维护竞赛的 公平、公正，严格按照以下处罚规则执行：</p>\n<p>第一次违规：账号封禁 1 个月，<strong>以后所有TLMOJ承办的比赛平台都将无法参赛</strong><br />\n第二次违规：<strong>永久封号</strong>，从此此用户信息无法注册TLMOJ系统<br />\n同时我们也鼓励大家共同维护竞赛的公平和公正，我们会给于举报成功的用户额外的奖励，我们会保证用户的隐私，为了保证您的信息隐私，我们会向您展示我们接口源码，绝不会透露您的信息。</p>\n<pre><div class=\"hljs\"><code class=\"lang-java\">    <span class=\"hljs-comment\">/**\n     * <span class=\"hljs-doctag\">@author</span> (Bryan yang)杨易达\n     * 比赛选手举报\n     * <span class=\"hljs-doctag\">@param</span> reportMessageText 举报信息文本\n     * <span class=\"hljs-doctag\">@return</span>\n     */</span>\n    <span class=\"hljs-meta\">@ApiOperation(&quot;比赛选手举报&quot;)</span>\n    <span class=\"hljs-meta\">@GetMapping(&quot;/competitorReport&quot;)</span>\n    <span class=\"hljs-keyword\">public</span> String <span class=\"hljs-title function_\">competitorReport</span><span class=\"hljs-params\">(<span class=\"hljs-meta\">@RequestParam(&quot;reportMessageText&quot;)</span> String reportMessageText)</span>{\n        <span class=\"hljs-keyword\">return</span> conTestSerivce.competitorReport(reportMessageText);\n    }\n</code></div></pre>\n<p>另外，为了保证竞赛的公正、公平，请勿在竞赛结束前在讨论区发布、讨论可能会影响竞赛正常进行的内容，包括且不限于发布竞赛题答案、解题思路、方法、提醒注意等，社区管理员将有权根据实际情况予以警告、删除、禁言、封禁其相应帐号的功能，情节严重者，将酌情封禁对应 IP。</p>\n<p><strong>通知</strong><br />\n您必须先 <strong>报名</strong> 后方能参加该场竞赛。我们祝愿您参赛愉快！祝你好运！</p>\n');
COMMIT;

-- ----------------------------
-- Table structure for language_command
-- ----------------------------
DROP TABLE IF EXISTS `language_command`;
CREATE TABLE `language_command` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `language_id` int(3) DEFAULT NULL COMMENT '编程语言',
  `command` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '计算机命令',
  `sequence` int(3) DEFAULT NULL COMMENT '次序',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(2) DEFAULT NULL COMMENT '0.编译过程 1.运行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of language_command
-- ----------------------------
BEGIN;
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (1, 1, '/usr/bin/javac', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (2, 1, '-encoding', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (3, 1, 'utf-8', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (4, 1, 'Main.java', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (5, 1, '/usr/bin/java', 0, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (6, 1, 'Main', 1, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (7, 2, '/usr/bin/g++', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (8, 2, 'Main.cc', 1, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (9, 2, '-o', 2, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (10, 2, 'a', 3, NULL, NULL, NULL, NULL, NULL, NULL, 0);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (11, 2, 'a', 0, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (12, 1, 'Main.java', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (13, 1, 'Main.class', 1, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (14, 2, 'Main.cc', 0, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO `language_command` (`id`, `language_id`, `command`, `sequence`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`) VALUES (15, 2, 'a', 1, NULL, NULL, NULL, NULL, NULL, NULL, 2);
COMMIT;

-- ----------------------------
-- Table structure for problem_limit
-- ----------------------------
DROP TABLE IF EXISTS `problem_limit`;
CREATE TABLE `problem_limit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL COMMENT '问题id',
  `stdout_max` int(9) DEFAULT NULL COMMENT '文件大小限制 mb',
  `stderr_max` int(9) DEFAULT NULL COMMENT '文件大小限制 mb',
  `cpu_limit` int(9) DEFAULT NULL COMMENT 'cpu时间限制 秒',
  `memory_limit` int(9) DEFAULT NULL COMMENT '内存大小限制 mb',
  `procLimit` int(9) DEFAULT NULL COMMENT '线程数量限制',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人name',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `del_flag` int(3) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of problem_limit
-- ----------------------------
BEGIN;
INSERT INTO `problem_limit` (`id`, `question_id`, `stdout_max`, `stderr_max`, `cpu_limit`, `memory_limit`, `procLimit`, `create_by_id`, `create_by_name`, `create_time`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (1, 1, 10240, 10240, 5, 256, 20, 1, 'Yida yang', '2022-10-23 05:54:06', NULL, NULL, NULL, 0, 0);
INSERT INTO `problem_limit` (`id`, `question_id`, `stdout_max`, `stderr_max`, `cpu_limit`, `memory_limit`, `procLimit`, `create_by_id`, `create_by_name`, `create_time`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (2, 26, 10240, 10240, 5, 256, 20, 1, 'Yida yang', '2022-11-10 14:33:21', NULL, NULL, NULL, 0, 0);
INSERT INTO `problem_limit` (`id`, `question_id`, `stdout_max`, `stderr_max`, `cpu_limit`, `memory_limit`, `procLimit`, `create_by_id`, `create_by_name`, `create_time`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (3, 10001, 10240, 10240, 5, 256, 20, 1, 'Yida yang', '2022-11-14 21:12:43', NULL, NULL, NULL, 0, 0);
INSERT INTO `problem_limit` (`id`, `question_id`, `stdout_max`, `stderr_max`, `cpu_limit`, `memory_limit`, `procLimit`, `create_by_id`, `create_by_name`, `create_time`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (4, 25, 10240, 10240, 5, 256, 20, 1, 'Yida yang', '2022-11-14 22:19:58', NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for programming_language
-- ----------------------------
DROP TABLE IF EXISTS `programming_language`;
CREATE TABLE `programming_language` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编程语言的Id',
  `language` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '编程语言',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人name',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `del_flag` int(3) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of programming_language
-- ----------------------------
BEGIN;
INSERT INTO `programming_language` (`id`, `language`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (1, 'Java', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `programming_language` (`id`, `language`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (2, 'C/C++', NULL, NULL, NULL, NULL, NULL, NULL, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `short_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `english_name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '英文名字',
  `chinese_name` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '中文名字',
  `grade` int(3) DEFAULT NULL COMMENT '等级',
  `question_type` int(3) DEFAULT NULL COMMENT '题目类型',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人name',
  `create_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人name',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `del_flag` int(3) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10002 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of question_bank
-- ----------------------------
BEGIN;
INSERT INTO `question_bank` (`id`, `short_name`, `english_name`, `chinese_name`, `grade`, `question_type`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (1, 'P10001', 'Teacher Zhang Yijia\'s math problems', '张义嘉老师的数学题', 1, 1, 0, 'Yida Yang', NULL, 0, NULL, NULL, 0, 0);
INSERT INTO `question_bank` (`id`, `short_name`, `english_name`, `chinese_name`, `grade`, `question_type`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (25, 'P10002', 'operations on arrays', '对数组的操作', 1, 2, 1, '杨易达', '2022-11-09 22:15:28', 0, NULL, NULL, 1, 0);
INSERT INTO `question_bank` (`id`, `short_name`, `english_name`, `chinese_name`, `grade`, `question_type`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (26, 'P10003', 'Helloworld', '你好世界', 1, 2, 1, '杨易达', '2022-11-10 14:23:32', 0, NULL, NULL, 1, 0);
INSERT INTO `question_bank` (`id`, `short_name`, `english_name`, `chinese_name`, `grade`, `question_type`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `update_time`, `status`, `del_flag`) VALUES (10001, 'P10004', 'printf', '打印人数', 1, 2, 1, '杨易达', '2022-11-12 21:46:01', 0, NULL, NULL, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for question_bank_detailed
-- ----------------------------
DROP TABLE IF EXISTS `question_bank_detailed`;
CREATE TABLE `question_bank_detailed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) DEFAULT NULL,
  `md_cn` text COLLATE utf8_bin,
  `html_cn` text COLLATE utf8_bin,
  `md_en` text COLLATE utf8_bin,
  `html_en` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of question_bank_detailed
-- ----------------------------
BEGIN;
INSERT INTO `question_bank_detailed` (`id`, `question_id`, `md_cn`, `html_cn`, `md_en`, `html_en`) VALUES (1, 1, '### 张义家老师的数学题\n张义嘉老师最近在做一道数学题，当数字太大的时候张老师运算会很久，他想设计一个程序来帮助他做题。\n\n**案例1**\n**input**\n~~~c++\n1 1\n~~~\n**output**\n~~~c++\n 2\n~~~\n\n**案例2**\n**input**\n~~~c++\n10086 10086\n~~~\n**output**\n~~~c++\n20172\n~~~\n\n**注意，输出案例可能会大于2147483647或者小于-2147483648**', '<h3><a id=\"_0\"></a>张义家老师的数学题</h3>\n<p>张义嘉老师最近在做一道数学题，当数字太大的时候张老师运算会很久，他想设计一个程序来帮助他做题。</p>\n<p><strong>案例1</strong><br />\n<strong>input</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">1</span> <span class=\"hljs-number\">1</span>\n</code></div></pre>\n<p><strong>output</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"> <span class=\"hljs-number\">2</span>\n</code></div></pre>\n<p><strong>案例2</strong><br />\n<strong>input</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">10086</span> <span class=\"hljs-number\">10086</span>\n</code></div></pre>\n<p><strong>output</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">20172</span>\n</code></div></pre>\n<p><strong>注意，输出案例可能会大于2147483647或者小于-2147483648</strong></p>\n', '', '<h3><a id=\"_0\"></a>张义家老师的数学题</h3>\n<p>张义嘉老师最近在做一道数学题，当数字太大的时候张老师运算会很久，他想设计一个程序来帮助他做题。</p>\n<p><strong>案例1</strong><br />\n<strong>input</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">1</span> <span class=\"hljs-number\">1</span>\n</code></div></pre>\n<p><strong>output</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"> <span class=\"hljs-number\">2</span>\n</code></div></pre>\n<p><strong>案例2</strong><br />\n<strong>input</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">10086</span> <span class=\"hljs-number\">10086</span>\n</code></div></pre>\n<p><strong>output</strong></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\"><span class=\"hljs-number\">20172</span>\n</code></div></pre>\n<p><strong>注意，输出案例可能会大于2147483647或者小于-2147483648</strong></p>\n');
INSERT INTO `question_bank_detailed` (`id`, `question_id`, `md_cn`, `html_cn`, `md_en`, `html_en`) VALUES (24, 25, '请输入一个数组长度n，和n个数组数据\n\n你需要对数组执行 n - 1 步操作，其中第 i 步操作（从 0 开始计数）要求对 nums 中第 i 个元素执行下述指令：\n\n如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。\n在执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。\n\n例如，数组 [1,0,2,0,0,1] 将所有 0 移动到末尾后变为 [1,2,1,0,0,0] 。\n返回结果数组。\n\n注意 操作应当 依次有序 执行，而不是一次性全部执行。\n\n \n~~~c++\n示例 1：\n\n输入：nums = [1,2,2,1,1,0]\n输出：[1,4,2,0,0,0]\n解释：执行以下操作：\n- i = 0: nums[0] 和 nums[1] 不相等，跳过这步操作。\n- i = 1: nums[1] 和 nums[2] 相等，nums[1] 的值变成原来的 2 倍，nums[2] 的值变成 0 。数组变成 [1,4,0,1,1,0] 。\n- i = 2: nums[2] 和 nums[3] 不相等，所以跳过这步操作。\n- i = 3: nums[3] 和 nums[4] 相等，nums[3] 的值变成原来的 2 倍，nums[4] 的值变成 0 。数组变成 [1,4,0,2,0,0] 。\n- i = 4: nums[4] 和 nums[5] 相等，nums[4] 的值变成原来的 2 倍，nums[5] 的值变成 0 。数组变成 [1,4,0,2,0,0] 。\n执行完所有操作后，将 0 全部移动到数组末尾，得到结果数组 [1,4,2,0,0,0] 。\n~~~\n~~~c++\n示例 2：\n\n输入：nums = [0,1]\n输出：[1,0]\n解释：无法执行任何操作，只需要将 0 移动到末尾。\n~~~', '<p>请输入一个数组长度n，和n个数组数据</p>\n<p>你需要对数组执行 n - 1 步操作，其中第 i 步操作（从 0 开始计数）要求对 nums 中第 i 个元素执行下述指令：</p>\n<p>如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。<br />\n在执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。</p>\n<p>例如，数组 [1,0,2,0,0,1] 将所有 0 移动到末尾后变为 [1,2,1,0,0,0] 。<br />\n返回结果数组。</p>\n<p>注意 操作应当 依次有序 执行，而不是一次性全部执行。</p>\n<p></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">示例 <span class=\"hljs-number\">1</span>：\n\n输入：nums = [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>]\n输出：[<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>]\n解释：执行以下操作：\n- i = <span class=\"hljs-number\">0</span>: nums[<span class=\"hljs-number\">0</span>] 和 nums[<span class=\"hljs-number\">1</span>] 不相等，跳过这步操作。\n- i = <span class=\"hljs-number\">1</span>: nums[<span class=\"hljs-number\">1</span>] 和 nums[<span class=\"hljs-number\">2</span>] 相等，nums[<span class=\"hljs-number\">1</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">2</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>] 。\n- i = <span class=\"hljs-number\">2</span>: nums[<span class=\"hljs-number\">2</span>] 和 nums[<span class=\"hljs-number\">3</span>] 不相等，所以跳过这步操作。\n- i = <span class=\"hljs-number\">3</span>: nums[<span class=\"hljs-number\">3</span>] 和 nums[<span class=\"hljs-number\">4</span>] 相等，nums[<span class=\"hljs-number\">3</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">4</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n- i = <span class=\"hljs-number\">4</span>: nums[<span class=\"hljs-number\">4</span>] 和 nums[<span class=\"hljs-number\">5</span>] 相等，nums[<span class=\"hljs-number\">4</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">5</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n执行完所有操作后，将 <span class=\"hljs-number\">0</span> 全部移动到数组末尾，得到结果数组 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n</code></div></pre>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">示例 <span class=\"hljs-number\">2</span>：\n\n输入：nums = [<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">1</span>]\n输出：[<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>]\n解释：无法执行任何操作，只需要将 <span class=\"hljs-number\">0</span> 移动到末尾。\n</code></div></pre>\n', '', '<p>请输入一个数组长度n，和n个数组数据</p>\n<p>你需要对数组执行 n - 1 步操作，其中第 i 步操作（从 0 开始计数）要求对 nums 中第 i 个元素执行下述指令：</p>\n<p>如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。<br />\n在执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。</p>\n<p>例如，数组 [1,0,2,0,0,1] 将所有 0 移动到末尾后变为 [1,2,1,0,0,0] 。<br />\n返回结果数组。</p>\n<p>注意 操作应当 依次有序 执行，而不是一次性全部执行。</p>\n<p></p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">示例 <span class=\"hljs-number\">1</span>：\n\n输入：nums = [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>]\n输出：[<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>]\n解释：执行以下操作：\n- i = <span class=\"hljs-number\">0</span>: nums[<span class=\"hljs-number\">0</span>] 和 nums[<span class=\"hljs-number\">1</span>] 不相等，跳过这步操作。\n- i = <span class=\"hljs-number\">1</span>: nums[<span class=\"hljs-number\">1</span>] 和 nums[<span class=\"hljs-number\">2</span>] 相等，nums[<span class=\"hljs-number\">1</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">2</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>] 。\n- i = <span class=\"hljs-number\">2</span>: nums[<span class=\"hljs-number\">2</span>] 和 nums[<span class=\"hljs-number\">3</span>] 不相等，所以跳过这步操作。\n- i = <span class=\"hljs-number\">3</span>: nums[<span class=\"hljs-number\">3</span>] 和 nums[<span class=\"hljs-number\">4</span>] 相等，nums[<span class=\"hljs-number\">3</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">4</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n- i = <span class=\"hljs-number\">4</span>: nums[<span class=\"hljs-number\">4</span>] 和 nums[<span class=\"hljs-number\">5</span>] 相等，nums[<span class=\"hljs-number\">4</span>] 的值变成原来的 <span class=\"hljs-number\">2</span> 倍，nums[<span class=\"hljs-number\">5</span>] 的值变成 <span class=\"hljs-number\">0</span> 。数组变成 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n执行完所有操作后，将 <span class=\"hljs-number\">0</span> 全部移动到数组末尾，得到结果数组 [<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">4</span>,<span class=\"hljs-number\">2</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">0</span>] 。\n</code></div></pre>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">示例 <span class=\"hljs-number\">2</span>：\n\n输入：nums = [<span class=\"hljs-number\">0</span>,<span class=\"hljs-number\">1</span>]\n输出：[<span class=\"hljs-number\">1</span>,<span class=\"hljs-number\">0</span>]\n解释：无法执行任何操作，只需要将 <span class=\"hljs-number\">0</span> 移动到末尾。\n</code></div></pre>\n');
INSERT INTO `question_bank_detailed` (`id`, `question_id`, `md_cn`, `html_cn`, `md_en`, `html_en`) VALUES (25, 26, '对于我们程序员来说我们第一个程序就是你好世界，来打印一个Hello World吧。\n输入\n~~~c++\nHello World\n~~~', '<p>对于我们程序员来说我们第一个程序就是你好世界，来打印一个Hello World吧。<br />\n输入</p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">Hello World\n</code></div></pre>\n', '', '<p>对于我们程序员来说我们第一个程序就是你好世界，来打印一个Hello World吧。<br />\n输入</p>\n<pre><div class=\"hljs\"><code class=\"lang-c++\">Hello World\n</code></div></pre>\n');
INSERT INTO `question_bank_detailed` (`id`, `question_id`, `md_cn`, `html_cn`, `md_en`, `html_en`) VALUES (26, 10001, '打印13', '<p>打印13</p>\n', '', '<p>打印13</p>\n');
COMMIT;

-- ----------------------------
-- Table structure for question_case
-- ----------------------------
DROP TABLE IF EXISTS `question_case`;
CREATE TABLE `question_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL COMMENT '问题主键',
  `inputs` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '输入样例',
  `answer` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '答案',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人name',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `upate_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `del_flag` int(3) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of question_case
-- ----------------------------
BEGIN;
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (1, 1, '1 1', '2\\n', 1, 'Yida Yang', '2022-10-23 16:18:37', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (2, 1, '1 2', '3\\n', 1, 'Yida Yang', '2022-10-23 16:21:13', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (3, 1, '1 3', '4\\n', 1, 'ashjkda', '2022-10-26 00:32:53', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (4, 1, '2 2', '4\\n', 1, 'asjdhasjk', '2022-10-26 00:38:41', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (5, 1, '2 3', '5\\n', 1, 'hasuidhkasjhduiahjdkah', '2022-10-26 13:30:21', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (6, 1, '9 9', '18\\n', 1, 'ASDNAS', '2022-10-26 14:07:01', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (7, 26, NULL, 'Hello World\\n', 1, 'Yida Yang', '2022-11-10 14:26:05', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (8, 10001, '', '13\\n', 1, 'Yida Yang', '2022-11-14 21:11:50', NULL, NULL, NULL, 0, 0);
INSERT INTO `question_case` (`id`, `question_id`, `inputs`, `answer`, `create_by_id`, `create_by_name`, `create_time`, `update_by_id`, `update_by_name`, `upate_time`, `status`, `del_flag`) VALUES (9, 25, '6 1 2 2 1 1 0', '1 4 2 0 0 0\\n', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `student_id` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '学号',
  `name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '名字',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `password` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `id_card` varchar(21) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
  `college_id` int(3) DEFAULT NULL COMMENT '学院id',
  `major_id` int(3) DEFAULT NULL COMMENT '专业id',
  `class_id` int(3) DEFAULT NULL COMMENT '班级id',
  `role` int(3) DEFAULT NULL COMMENT '权限',
  `grade` int(5) DEFAULT NULL COMMENT '年级',
  `year` int(5) DEFAULT NULL COMMENT '年',
  `mouth` int(3) DEFAULT NULL COMMENT '月份',
  `head_portrait` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_by_name` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `update_by_name` datetime DEFAULT NULL COMMENT '修改人name',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `del_flag` int(2) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=668 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `student_id`, `name`, `sex`, `password`, `id_card`, `college_id`, `major_id`, `class_id`, `role`, `grade`, `year`, `mouth`, `head_portrait`, `create_time`, `create_by_id`, `create_by_name`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (1, 'y51288033@outlook.com', '2021102082', '杨易达(Bryan yang)', 1, 'admin', '我就不告诉你', 1, 1, 1, 5, 0, NULL, NULL, 'https://dazuiblog.oss-cn-hangzhou.aliyuncs.com/users/y51288033/headImage/headImage.jpg', '2022-10-23 05:43:40', NULL, 'admin', NULL, NULL, NULL, 0, 0);
INSERT INTO `user` (`id`, `username`, `student_id`, `name`, `sex`, `password`, `id_card`, `college_id`, `major_id`, `class_id`, `role`, `grade`, `year`, `mouth`, `head_portrait`, `create_time`, `create_by_id`, `create_by_name`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (666, 'Liugang', '2021102089', '刘港', 1, 'admin', '123456789', 1, 1, 1, 1, 0, NULL, NULL, 'hangzhou.aliyuncs.com/users/y51288033/headImage/headImage.jpg', '2022-11-05 12:02:51', NULL, NULL, NULL, NULL, NULL, 0, 0);
INSERT INTO `user` (`id`, `username`, `student_id`, `name`, `sex`, `password`, `id_card`, `college_id`, `major_id`, `class_id`, `role`, `grade`, `year`, `mouth`, `head_portrait`, `create_time`, `create_by_id`, `create_by_name`, `update_time`, `update_by_id`, `update_by_name`, `status`, `del_flag`) VALUES (667, 'Liuchaogang', '20211020821', '刘朝甲', 0, 'wasd123', '', 0, 0, 0, 1, 2021, 1991, 2, '', NULL, 0, NULL, NULL, 0, NULL, 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
