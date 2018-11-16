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


CREATE TABLE `contactus` (
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

ALTER TABLE `question` 
ADD `multiple_ans` bit(1) DEFAULT NULL;

CREATE TABLE `show_register` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `view_register_page` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `show_register` (`view_register_page`)
VALUES
(1);

ALTER TABLE `USER_EXAM`
ADD `TOTAL_QUESTIONS` int DEFAULT 0;

ALTER TABLE `USER_EXAM`
ADD `ANSWERED_QUESTIONS` int DEFAULT 0;

ALTER TABLE `USER_EXAM`
ADD `TOTAL_MARKS` DOUBLE(40,2) DEFAULT 0.0;

ALTER TABLE `sub_resource`
ADD `order_id` bigint(20) DEFAULT 0;


