-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS transport_user;
CREATE TABLE transport_user(
	account VARCHAR (50) COMMENT 'account',
	`password` VARCHAR (50) COMMENT 'password',
	user_id BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'userId',
	user_type VARCHAR (50) COMMENT 'userType',
	user_name VARCHAR (50) COMMENT '用户名或者商家名称',
	PRIMARY KEY (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'transport_user';
