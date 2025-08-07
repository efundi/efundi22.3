CREATE TABLE `cm_curriculum_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` varchar(8) NOT NULL,
  `term` varchar(16) NOT NULL,
  `course_code` varchar(12) NOT NULL,
  `course_descr` varchar(99) NOT NULL,
  `section_code` varchar(8) NOT NULL,
  `section_descr` varchar(99) DEFAULT NULL,
  `efundi_site_id` varchar(99) DEFAULT NULL,
  `action` varchar(20) NOT NULL,
  `audit_date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cm_unique_index` (`campus`,`term`,`course_code`,`section_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cm_course_section_instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id`int(11) NOT NULL,
  `instructor_number` varchar(20) NOT NULL,
  `instructor_name` varchar(160) NULL,
  `audit_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cm_inst_unique_index` (`course_id`),
  CONSTRAINT `fk_inst_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gb_lesson_plan` (
  `source_system_id` varchar(100) NOT NULL,
  `course_id` int(11) NOT NULL,
  `efundi_gradebook_id` bigint(20) DEFAULT NULL,
  `lesson_code` varchar(15) NOT NULL DEFAULT '',
  `class_test_number` int(3) NOT NULL,
  `class_test_code` varchar(16) NOT NULL,
  `class_test_name` varchar(40) NOT NULL,
  `class_test_max_score` double(3,0) NOT NULL,
  `processed` tinyint(4) NOT NULL DEFAULT '0',
  `controlNote` varchar(20) NOT NULL DEFAULT 'N',
  `action` varchar(20) NOT NULL DEFAULT '',
  `audit_date_time` datetime NOT NULL,
  PRIMARY KEY (`source_system_id`),
  UNIQUE KEY `gb_lp_unique_index` (`source_system_id`,`course_id`),
  KEY `fk_lp_course_id_idx` (`course_id`),
  CONSTRAINT `fk_lp_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cm_student_enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `nwu_number` int(20) NOT NULL,
  `course_status` varchar(30) NOT NULL,
  `program_version_code` varchar(25) NOT NULL DEFAULT '""',
  `controlNote` varchar(20) NOT NULL DEFAULT 'N',
  `audit_date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_stud_unique_index` (`course_id`,`program_version_code`,`nwu_number`),
  CONSTRAINT `fk_stud_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gb_lesson_grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` varchar(100) NOT NULL,
  `nwu_number` int(20) NOT NULL,
  `grade` double(3,0) NOT NULL,
  `audit_date_time` datetime NOT NULL,
  `section_code` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_lg_unique_index` (`lesson_id`,`nwu_number`),
  CONSTRAINT `fk_lesson_plan` FOREIGN KEY (`lesson_id`) REFERENCES `gb_lesson_plan` (`source_system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gb_exam_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` varchar(8) NOT NULL,
  `term` varchar(16) NOT NULL,
  `course_code` varchar(12) NOT NULL,
  `section_code` varchar(8) NOT NULL,
  `exam_lesson_code` varchar(8) NOT NULL,
  `exam_lesson_number` int(4) NOT NULL,
  `exam_lesson_name` varchar(40) NULL,
  `percentage` DOUBLE(6,3) NOT NULL,
  `exam_lesson_max_score` DOUBLE(6,3) NOT NULL,
  `action` varchar(6) NOT NULL,
  `efundi_gradebook_id` bigint(20) NULL,
  `audit_date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_el_unique_index` (`campus`,`term`,`course_code`,`section_code`,`exam_lesson_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

