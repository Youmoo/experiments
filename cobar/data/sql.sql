create database dbtest1;
create database dbtest2;
create database dbtest3;

//分别在这三个数据库中创建下面的表：
CREATE TABLE `tb_user` (
  `user_id` bigint(20) NOT NULL,
  `nick_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL,
  `modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;