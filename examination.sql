/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : newsysytem

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 16/07/2023 09:40:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `t_admin` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员id',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_admin`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('123', '123456');

-- ----------------------------
-- Table structure for t_electives
-- ----------------------------
DROP TABLE IF EXISTS `t_electives`;
CREATE TABLE `t_electives`  (
  `student_id` int NOT NULL,
  `score` int NULL DEFAULT NULL,
  `subject_id` int NOT NULL,
  UNIQUE INDEX `sid与subid唯一`(`student_id` ASC, `subject_id` ASC) USING BTREE,
  INDEX `sub_id外键`(`subject_id` ASC) USING BTREE,
  INDEX `stu_id外键`(`student_id` ASC) USING BTREE,
  CONSTRAINT `sid外键` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sub_id外键` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`subject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_electives
-- ----------------------------
INSERT INTO `t_electives` VALUES (1, NULL, 1);
INSERT INTO `t_electives` VALUES (11, NULL, 2);
INSERT INTO `t_electives` VALUES (11, 70, 5);
INSERT INTO `t_electives` VALUES (11, NULL, 6);
INSERT INTO `t_electives` VALUES (11, NULL, 7);
INSERT INTO `t_electives` VALUES (15, NULL, 3);
INSERT INTO `t_electives` VALUES (15, 90, 8);
INSERT INTO `t_electives` VALUES (15, 88, 10);

-- ----------------------------
-- Table structure for t_exam_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_answer`;
CREATE TABLE `t_exam_answer`  (
  `student_id` int NOT NULL COMMENT '学生id。',
  `course_id` int NOT NULL COMMENT '哪张卷子',
  `title_id` int NOT NULL COMMENT '哪一题',
  `stu_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生作答',
  `get_score` int NULL DEFAULT NULL COMMENT '学生得分'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_exam_answer
-- ----------------------------
INSERT INTO `t_exam_answer` VALUES (11, 2, 1, '作答1', 1);
INSERT INTO `t_exam_answer` VALUES (11, 2, 2, '作答2', NULL);
INSERT INTO `t_exam_answer` VALUES (11, 2, 11, '作答3', NULL);
INSERT INTO `t_exam_answer` VALUES (15, 3, 7, '作答7', 2);
INSERT INTO `t_exam_answer` VALUES (15, 3, 16, '作答9', NULL);
INSERT INTO `t_exam_answer` VALUES (12, 2, 1, '', NULL);
INSERT INTO `t_exam_answer` VALUES (11, 1, 4, 'dq', 2);

-- ----------------------------
-- Table structure for t_exam_schedule
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_schedule`;
CREATE TABLE `t_exam_schedule`  (
  `course_id` int NOT NULL,
  `exam_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher_id` int NOT NULL,
  `room` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime NULL DEFAULT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE,
  UNIQUE INDEX `不能同时同地`(`room` ASC, `start_time` ASC) USING BTREE COMMENT '不能同时同地点开始',
  INDEX `teacher_id`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `t_exam_schedule_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `课程名外键` FOREIGN KEY (`course_id`) REFERENCES `t_subject` (`subject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `check_endtime_greater` CHECK (`end_time` > `start_time`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_exam_schedule
-- ----------------------------
INSERT INTO `t_exam_schedule` VALUES (1, 'c++期末考', 13, '108', NULL, NULL);
INSERT INTO `t_exam_schedule` VALUES (2, '吉林省化学中考', 13, '109', '2023-07-14 10:02:57', '2023-08-01 10:03:03');
INSERT INTO `t_exam_schedule` VALUES (3, '广东省化学中考', 16, '110', '2023-07-14 10:03:00', '2023-08-01 10:03:07');
INSERT INTO `t_exam_schedule` VALUES (4, 'JAVA期末考', 14, '101', NULL, NULL);
INSERT INTO `t_exam_schedule` VALUES (5, '大学语文', 16, '220', '2023-07-13 20:00:32', '2023-07-14 20:00:36');
INSERT INTO `t_exam_schedule` VALUES (6, '大学英语期末', 13, '210', '2012-10-09 12:15:57', '2013-12-04 14:29:59');
INSERT INTO `t_exam_schedule` VALUES (7, '高数上期末考', 16, '222', '2019-12-31 13:39:41', '2020-01-19 23:00:10');
INSERT INTO `t_exam_schedule` VALUES (8, '线性代数期末', 15, '304', '2014-06-11 19:01:58', '2015-07-30 15:26:25');
INSERT INTO `t_exam_schedule` VALUES (9, '数据结构期末', 21, 'C04j4BzDRM', '2022-07-01 16:54:09', '2023-04-12 07:41:50');

-- ----------------------------
-- Table structure for t_exampaper_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_exampaper_detail`;
CREATE TABLE `t_exampaper_detail`  (
  `cource_id` int NOT NULL,
  `title_id` int NOT NULL,
  INDEX `title_id`(`title_id` ASC) USING BTREE,
  INDEX `subject_id`(`cource_id` ASC) USING BTREE,
  CONSTRAINT `t_exampaper_detail_ibfk_1` FOREIGN KEY (`title_id`) REFERENCES `t_question` (`question_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_exampaper_detail_ibfk_2` FOREIGN KEY (`cource_id`) REFERENCES `t_exam_schedule` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_exampaper_detail
-- ----------------------------
INSERT INTO `t_exampaper_detail` VALUES (7, 13);
INSERT INTO `t_exampaper_detail` VALUES (4, 9);
INSERT INTO `t_exampaper_detail` VALUES (8, 8);
INSERT INTO `t_exampaper_detail` VALUES (2, 2);
INSERT INTO `t_exampaper_detail` VALUES (3, 10);
INSERT INTO `t_exampaper_detail` VALUES (5, 14);
INSERT INTO `t_exampaper_detail` VALUES (8, 1);
INSERT INTO `t_exampaper_detail` VALUES (1, 12);
INSERT INTO `t_exampaper_detail` VALUES (5, 10);
INSERT INTO `t_exampaper_detail` VALUES (2, 6);
INSERT INTO `t_exampaper_detail` VALUES (2, 2);
INSERT INTO `t_exampaper_detail` VALUES (4, 10);
INSERT INTO `t_exampaper_detail` VALUES (5, 13);
INSERT INTO `t_exampaper_detail` VALUES (6, 9);
INSERT INTO `t_exampaper_detail` VALUES (9, 4);
INSERT INTO `t_exampaper_detail` VALUES (6, 6);
INSERT INTO `t_exampaper_detail` VALUES (2, 1);
INSERT INTO `t_exampaper_detail` VALUES (2, 2);
INSERT INTO `t_exampaper_detail` VALUES (2, 3);
INSERT INTO `t_exampaper_detail` VALUES (2, 4);
INSERT INTO `t_exampaper_detail` VALUES (2, 5);
INSERT INTO `t_exampaper_detail` VALUES (3, 6);
INSERT INTO `t_exampaper_detail` VALUES (3, 7);
INSERT INTO `t_exampaper_detail` VALUES (3, 8);
INSERT INTO `t_exampaper_detail` VALUES (3, 9);
INSERT INTO `t_exampaper_detail` VALUES (3, 10);
INSERT INTO `t_exampaper_detail` VALUES (2, 11);
INSERT INTO `t_exampaper_detail` VALUES (2, 12);
INSERT INTO `t_exampaper_detail` VALUES (3, 13);
INSERT INTO `t_exampaper_detail` VALUES (3, 14);
INSERT INTO `t_exampaper_detail` VALUES (2, 15);
INSERT INTO `t_exampaper_detail` VALUES (2, 16);

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目名',
  `score` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL COMMENT '0选择题1简单题2综合题',
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '答案',
  `subject_id` int NOT NULL COMMENT '课程名',
  `option` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '选项',
  PRIMARY KEY (`question_id`) USING BTREE,
  INDEX `课程id外键约束`(`subject_id` ASC) USING BTREE,
  CONSTRAINT `课程id外键约束` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`subject_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (1, '空气中含量最多的气体是', 3, 0, '氮气', 2, '[{\"value\":\"氮气\"},{\"value\":\"氧气\"},{\"value\":\"二氧化碳\"},{\"value\":\"水蒸气\"}]');
INSERT INTO `t_question` VALUES (2, '下列变化属于化学变化的是', 3, 0, '甲烷燃烧', 2, '[{\"value\":\"玻璃破碎\"},{\"value\":\"冰雪融化\"},{\"value\":\"酒精挥发\"},{\"value\":\"甲烷燃烧\"}]');
INSERT INTO `t_question` VALUES (3, '适当增加蛋白质的摄入量有助于提高人体免疫力。下列食物富含蛋白质的是', 4, 0, '鸡蛋', 2, '[{\"value\":\"菠菜\"},{\"value\":\"香蕉\"},{\"value\":\"鸡蛋\"},{\"value\":\"馒头\"}]');
INSERT INTO `t_question` VALUES (4, '下列物质由分子构成的是', 5, 0, '氧气', 2, '[{\"value\":\"铜\"},{\"value\":\"氧气\"},{\"value\":\"金刚石\"},{\"value\":\"氯化钠\"}]');
INSERT INTO `t_question` VALUES (5, '下列物质属于合成材料的是', 5, 0, '涤纶', 2, '[{\"value\":\"棉花\"},{\"value\":\"羊毛\"},{\"value\":\"涤纶\"},{\"value\":\"天然橡胶\"}]');
INSERT INTO `t_question` VALUES (6, '下列防疫措施中涉及到化学变化的是', 4, 0, '勤消毒', 3, '[{\"value\":\"测体温\"},{\"value\":\"戴口罩\"},{\"value\":\"常通风\"},{\"value\":\"勤消毒\"}]');
INSERT INTO `t_question` VALUES (7, '下列气体属于空气污染物的是', 3, 0, '二氧化氮', 3, '[{\"value\":\"二氧化氮\"},{\"value\":\"二氧化碳\"},{\"value\":\"氧气\"},{\"value\":\"氮气\"}]');
INSERT INTO `t_question` VALUES (8, '下列食物富含蛋白质的是', 6, 0, '鸡肉', 3, '[{\"value\":\"苹果\"},{\"value\":\"海带\"},{\"value\":\"鸡肉\"},{\"value\":\"米饭\"}]');
INSERT INTO `t_question` VALUES (9, '王安石在《梅花》中写道:墙角数枝梅，凌寒独自开。遥知不是雪，为有暗香来。闻到 暗香 说明了', 4, 0, '分子在不断运动', 3, '[{\"value\":\"分子在不断运动\"},{\"value\":\"分子间有间隔\"},{\"value\":\"分子的质量很小\"},{\"value\":\"分子由原子构成\"}]');
INSERT INTO `t_question` VALUES (10, '碳元素能组成多种单质。下列关于碳单质的说法中正确的是', 5, 0, '常温下化学性质都不活泼', 3, '[{\"value\":\"硬度都很大\"},{\"value\":\"都可用于制造铅笔芯\"},{\"value\":\"都是黑色固体\"},{\"value\":\"常温下化学性质都不活泼\"}]');
INSERT INTO `t_question` VALUES (11, '铁是人体必须的微量元素，缺体会引起', 4, 1, '贫血,发育问题，免疫力下降', 2, NULL);
INSERT INTO `t_question` VALUES (12, '生活中常用的硬水软化方法是', 4, 1, '煮沸', 2, NULL);
INSERT INTO `t_question` VALUES (13, 'CO有____性，可用于冶炼金属', 3, 1, '还原', 3, NULL);
INSERT INTO `t_question` VALUES (14, '胃液中含有的酸是__', 5, 1, 'HCl', 3, NULL);
INSERT INTO `t_question` VALUES (15, '综合题题目1', 20, 2, '综合题1答案', 2, '[{\"value\":\"题目一题干具体内容\"}]');
INSERT INTO `t_question` VALUES (16, '综合题题目2', 20, 2, '综合题答案2', 3, '[{\"value\":\"题目二题干具体内容\"}]');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1, 'xuesheng1', '123456', '12345678', '123');
INSERT INTO `t_student` VALUES (5, '叶詩涵', '123456', '5461 663754', 'yeshi@hotmail.com');
INSERT INTO `t_student` VALUES (6, '常致远', '123456', '838-922-0991', 'chang3@gmail.com');
INSERT INTO `t_student` VALUES (7, '贾嘉伦', '123456', '142-5477-3909', 'jialunjia2@mail.com');
INSERT INTO `t_student` VALUES (8, '贾震南', '123456', '74-940-9130', 'zhennan521@yahoo.com');
INSERT INTO `t_student` VALUES (9, '刘晓明', '123456', '52-469-4961', 'xiaomingliu@gmail.com');
INSERT INTO `t_student` VALUES (10, '罗安琪', '123456', '7479 341614', 'al7@yahoo.com');
INSERT INTO `t_student` VALUES (11, '徐云熙', '123456', '66-280-3184', 'yunxi2@hotmail.com');
INSERT INTO `t_student` VALUES (12, '汤致远', '123456', '52-206-6984', 'zhiyuta@mail.com');
INSERT INTO `t_student` VALUES (13, '冯璐', '123456', '11-371-3554', 'lufeng@hotmail.com');
INSERT INTO `t_student` VALUES (14, '赵秀英', '123456', '614-528-4881', 'zhao1999@outlook.com');
INSERT INTO `t_student` VALUES (15, '叶云熙', '123456', '187-0383-3452', 'yy9@outlook.com');
INSERT INTO `t_student` VALUES (16, '杜震南', '123456', '5451 986784', 'duzhen10@outlook.com');
INSERT INTO `t_student` VALUES (17, '顾詩涵', '123456', '3-3623-9202', 'gu2@mail.com');
INSERT INTO `t_student` VALUES (18, '曾安琪', '123456', '5573 879991', 'zeng1117@icloud.com');
INSERT INTO `t_student` VALUES (19, '李晓明', '123456', '838-088-6348', 'xiaomingl122@hotmail.com');
INSERT INTO `t_student` VALUES (20, '王安琪', '123456', '10-5547-9191', 'anqi930@hotmail.com');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`  (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher_id` int NOT NULL,
  PRIMARY KEY (`subject_id`) USING BTREE,
  INDEX `教师外键`(`teacher_id` ASC) USING BTREE,
  CONSTRAINT `教师外键` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`teacher_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES (1, 'c++', 13);
INSERT INTO `t_subject` VALUES (2, '初中化学', 13);
INSERT INTO `t_subject` VALUES (3, '高中化学', 16);
INSERT INTO `t_subject` VALUES (4, 'JAVA', 12);
INSERT INTO `t_subject` VALUES (5, '大学语文', 16);
INSERT INTO `t_subject` VALUES (6, '大学英语2级', 13);
INSERT INTO `t_subject` VALUES (7, '高等数学上', 16);
INSERT INTO `t_subject` VALUES (8, '线性代数', 19);
INSERT INTO `t_subject` VALUES (9, '数据结构', 13);
INSERT INTO `t_subject` VALUES (10, 'Mysql数据库', 11);
INSERT INTO `t_subject` VALUES (11, '机器学习', 13);

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher`  (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------
INSERT INTO `t_teacher` VALUES (11, 'abc', '123456', '111', '111');
INSERT INTO `t_teacher` VALUES (12, '叶杰宏', '123456', '52-170-3575', 'yejiehong107@outlook.com');
INSERT INTO `t_teacher` VALUES (13, '秦杰宏', '123456', '173-5573-8363', 'jqin@yahoo.com');
INSERT INTO `t_teacher` VALUES (14, '蒋璐', '123456', '760-8660-8603', 'jiang930@outlook.com');
INSERT INTO `t_teacher` VALUES (15, '陶璐', '123456', '769-363-9240', 'tlu417@hotmail.com');
INSERT INTO `t_teacher` VALUES (16, '陶秀英', '123456', '28-3435-8126', 'txiuying1206@gmail.com');
INSERT INTO `t_teacher` VALUES (17, '尹岚', '123456', '70-7294-6637', 'yla@gmail.com');
INSERT INTO `t_teacher` VALUES (18, '任詩涵', '123456', '614-298-4669', 'shihr7@yahoo.com');
INSERT INTO `t_teacher` VALUES (19, '林子韬', '123456', '(161) 882 7455', 'zitaoli3@gmail.com');
INSERT INTO `t_teacher` VALUES (20, '薛嘉伦', '123456', '80-5921-6158', 'xuejialun10@icloud.com');
INSERT INTO `t_teacher` VALUES (21, '史睿', '123456', '212-564-6874', 'shirui@icloud.com');

-- ----------------------------
-- Triggers structure for table t_electives
-- ----------------------------
DROP TRIGGER IF EXISTS `prevent_delete_row`;
delimiter ;;
CREATE TRIGGER `prevent_delete_row` BEFORE DELETE ON `t_electives` FOR EACH ROW BEGIN
    IF OLD.score IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot delete row with non-null score';
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
