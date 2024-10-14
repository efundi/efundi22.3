CREATE TABLE `cm_curriculum_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `campus` varchar(8) NOT NULL,
  `enrolment_year` int(4) NOT NULL,
  `term` varchar(16) NOT NULL,
  `course_code` varchar(12) NOT NULL,
  `course_descr` varchar(99) NOT NULL,
  `section_code` varchar(8) NOT NULL,
  `section_descr` varchar(99) NULL,
  `efundi_site_id` varchar(99) NULL,
  `audit_date_time` datetime NOT NULL,
  `term_start_date` date NOT NULL,
  `term_end_date` date NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cm_unique_index` (`campus`,`enrolment_year`,`term`,`course_code`,`section_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cm_course_section_instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id`int(11) NOT NULL,
  `instructor_number` int(20) NOT NULL,
  `instructor_name` varchar(160) NULL,
  `audit_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cm_inst_unique_index` (`course_id`),
  CONSTRAINT `fk_inst_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gb_lesson_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id`int(11) NOT NULL,
  `class_test_number` int(3) NOT NULL,
  `class_test_code` varchar(8) NOT NULL,
  `class_test_name` varchar(40) NULL,
  `class_test_max_score` DOUBLE(3,0) NOT NULL,
  `efundi_gradebook_id` bigint(20) NULL,
  `audit_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_lp_unique_index` (`course_id`,`class_test_number`,`class_test_code`),
  CONSTRAINT `fk_lp_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cm_student_enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `nwu_number` int(20) NOT NULL,
  `course_status` VARCHAR(30) NOT NULL,
  `faculty` VARCHAR(45) NOT NULL,
  `audit_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_stud_unique_index` (`course_id`,`nwu_number`),
  CONSTRAINT `fk_stud_course_id` FOREIGN KEY (`course_id`) REFERENCES `cm_curriculum_course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `gb_lesson_grades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id`int(11) NOT NULL,
  `nwu_number` int(20) NOT NULL,  
  `grade` DOUBLE(3,0) NOT NULL,
  `audit_date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gb_lg_unique_index` (`lesson_id`,`nwu_number`),
  CONSTRAINT `fk_lg_course_id` FOREIGN KEY (`lesson_id`) REFERENCES `gb_lesson_plan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
