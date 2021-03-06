--CREATE SCHEMA `niadb` DEFAULT CHARACTER SET utf8 ;

--CREATE TABLE `application_user` (
--  `id` bigint(20) NOT NULL AUTO_INCREMENT,
--  `password` varchar(255) DEFAULT NULL,
--  `username` varchar(255) DEFAULT NULL,
--  PRIMARY KEY (`id`)
--) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--CREATE SCHEMA `nakshatra_niadb` DEFAULT CHARACTER SET utf8 ;

USE `nakshatra_niadb`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO hibernate_sequence(next_val) VALUES (0);

CREATE TABLE `user_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `group1` bit(1) DEFAULT NULL,
  `group2` bit(1) DEFAULT NULL,
  `group3` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `permanent_addr` varchar(255) DEFAULT NULL,
  `permanent_pincode` varchar(255) DEFAULT NULL,
  `permanent_state` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `registration_id` varchar(255) DEFAULT NULL,
  `residential_addr` varchar(255) DEFAULT NULL,
  `residential_pincode` varchar(255) DEFAULT NULL,
  `residential_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `constr_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `application_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_register_id` bigint(20) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1` (`user_register_id`),
  CONSTRAINT `FK1` FOREIGN KEY (`user_register_id`) REFERENCES `user_register` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TRIGGER registration_auto_id BEFORE INSERT ON user_register
       FOR EACH ROW
       SET NEW.registration_id = CONCAT("NAC2018",LPAD((SELECT AUTO_INCREMENT 
       FROM information_schema.TABLES 
       WHERE TABLE_SCHEMA = DATABASE() AND 
       TABLE_NAME = 'user_register'), 4, '0'));
       
CREATE TABLE `exam` (
  `exam_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_date` datetime DEFAULT NULL,
  `exam_description` varchar(255) DEFAULT NULL,
  `exam_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_desc` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `exam_id` bigint(20) DEFAULT NULL,
  `multiple_ans` bit(1) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKhupso6ldavcx993tfnrjsdl1p` (`exam_id`),
  CONSTRAINT `FKhupso6ldavcx993tfnrjsdl1p` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `question_option` (
  `option_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `option_desc` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `is_answer` bit(1) DEFAULT NULL,
  PRIMARY KEY (`option_id`),
  KEY `FKmmdv54rmm5hkgxbn1008ix87n` (`question_id`),
  CONSTRAINT `FKmmdv54rmm5hkgxbn1008ix87n` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user_exam` (
  `user_exam_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `TOTAL_QUESTIONS` int(11) DEFAULT '0',
  `ANSWERED_QUESTIONS` int(11) DEFAULT '0',
  `TOTAL_MARKS` double(40,2) DEFAULT '0.00',
  PRIMARY KEY (`user_exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `user_response` (
  `user_response_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `option_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `user_exam_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_response_id`),
  KEY `FKap529hul3xej6o8cn27ye9wq4` (`user_exam_id`),
  CONSTRAINT `FKap529hul3xej6o8cn27ye9wq4` FOREIGN KEY (`user_exam_id`) REFERENCES `user_exam` (`user_exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `privilege` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `roles_privileges` (
  `role_id` bigint(20) NOT NULL,
  `privilege_id` bigint(20) NOT NULL,
  KEY `FK5yjwxw2gvfyu76j3rgqwo685u` (`privilege_id`),
  KEY `FK9h2vewsqh8luhfq71xokh4who` (`role_id`),
  CONSTRAINT `FK9h2vewsqh8luhfq71xokh4who` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `FK5yjwxw2gvfyu76j3rgqwo685u` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKrygrggwd04p6r14ie18e2u7iu` (`user_id`),
  CONSTRAINT `FKrygrggwd04p6r14ie18e2u7iu` FOREIGN KEY (`user_id`) REFERENCES `application_user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert statements

insert into privilege (name, id) values ('READ_PRIVILEGE', 0);
insert into privilege (name, id) values ('WRITE_PRIVILEGE', 1);

insert into role (name, id) values ('ROLE_ADMIN', 1);
insert into roles_privileges (role_id, privilege_id) values (1, 0);
insert into roles_privileges (role_id, privilege_id) values (1, 1);

insert into role (name, id) values ('ROLE_USER', 2);
insert into roles_privileges (role_id, privilege_id) values (2, 0);


ALTER TABLE application_user
ADD reset_token varchar(255) DEFAULT NULL;

--ALTER TABLE user_register 
--ADD CONSTRAINT constr_email UNIQUE (email);

CREATE TABLE `subject` (
  `subject_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sub_resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `res_link` varchar(255) DEFAULT NULL,
  `res_type` varchar(255) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `FKj1v70oougyh05j8g7jylh1w4y` (`subject_id`),
  CONSTRAINT `FKj1v70oougyh05j8g7jylh1w4y` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--insert into subject (name) values ('History');
--insert into subject (name) values ('Polytics');

--INSERT INTO sub_resource (`name`,`res_link`,`res_type`,`subject_id`) VALUES ('history engilsh part1', 'https://www.youtube.com/watch?v=RZ_SHapQOTA', 'Video', 1);
--INSERT INTO sub_resource (`name`,`res_link`,`res_type`,`subject_id`) VALUES ('history engilsh part2', 'https://www.youtube.com/watch?v=h3lTJ73dohc', 'Video', 1);
--INSERT INTO sub_resource (`name`,`res_link`,`res_type`,`subject_id`) VALUES ('test2', 'https://www.youtube.com/watch?v=Yc7W965HcPE', 'Video', 3);

update application_user set enabled= where username='';

insert into users_roles (user_id, role_id) values (1, 3);


-- Create ContactUs Table


CREATE TABLE `CONTACTUS` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email1` varchar(255) DEFAULT NULL,
  `email2` varchar(255) DEFAULT NULL,
  `phone1` varchar(255) DEFAULT NULL,
  `phone2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `contactus` (`email1`,`email2`,`phone1`,`phone2`)
VALUES
('admin@nakshatraacademy.in','','08217037454','080-43741208');

INSERT INTO `SHOW_REGISTER` (`view_register_page`)
VALUES
(1);

update SHOW_REGISTER set view_register_page =0 where id=1;

--

ALTER TABLE USER_EXAM
ADD TOTAL_QUESTIONS int DEFAULT 0;


ALTER TABLE USER_EXAM
ADD ANSWERED_QUESTIONS int DEFAULT 0;


ALTER TABLE USER_EXAM
ADD TOTAL_MARKS DOUBLE(40,2) DEFAULT 0.0;

CREATE TABLE `backup_user_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `group1` bit(1) DEFAULT NULL,
  `group2` bit(1) DEFAULT NULL,
  `group3` bit(1) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `permanent_addr` varchar(255) DEFAULT NULL,
  `permanent_pincode` varchar(255) DEFAULT NULL,
  `permanent_state` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `registration_id` varchar(255) DEFAULT NULL,
  `residential_addr` varchar(255) DEFAULT NULL,
  `residential_pincode` varchar(255) DEFAULT NULL,
  `residential_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `show_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `view_register_page` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--SET SQL_SAFE_UPDATES = 0;

ALTER TABLE `table_name` AUTO_INCREMENT=1
--Delete script

delete from user_response;
delete from user_exam;

delete from question_option;
delete from question;
delete from exam where exam_id != 1

-- cleaning up users
delete from users_roles;
delete from application_user;
delete from user_register;

