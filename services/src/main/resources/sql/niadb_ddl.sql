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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `permanent_addr` varchar(255) DEFAULT NULL,
  `permanent_pincode` varchar(255) DEFAULT NULL,
  `permanent_state` varchar(255) DEFAULT NULL,
  `registration_id` varchar(255) DEFAULT NULL,
  `residential_addr` varchar(255) DEFAULT NULL,
  `residential_pincode` varchar(255) DEFAULT NULL,
  `residential_state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `application_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_register_id` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `question_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB  AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `question_option` (
  `option_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `option_desc` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`option_id`),
  KEY `FKmmdv54rmm5hkgxbn1008ix87n` (`question_id`),
  CONSTRAINT `FKmmdv54rmm5hkgxbn1008ix87n` FOREIGN KEY (`question_id`) REFERENCES `question` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_exam` (
  `user_exam_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
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

ALTER TABLE user_register 
ADD CONSTRAINT constr_email UNIQUE (email);

--SET SQL_SAFE_UPDATES = 0;

--Delete script

delete from user_response;
delete from user_exam;

delete from question_option;
delete from question;
delete from exam where exam_id != 1

