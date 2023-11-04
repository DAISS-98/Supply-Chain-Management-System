-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS `process`;
CREATE TABLE `process`(
	process_key VARCHAR (50) NOT NULL COMMENT '主键',
	process_name VARCHAR (50) COMMENT '加工商名称',
	process_get_date DATETIME COMMENT '进货时间',
	process_time DATETIME COMMENT '加工日期',
	process_address VARCHAR (50) COMMENT '加工地点',
	process_person VARCHAR (50) COMMENT '加工人员',
	process_fresh VARCHAR (50) COMMENT '新鲜度',
	process_c VARCHAR (50) COMMENT '环境温度',
	process_humidity VARCHAR (50) COMMENT '环境湿度',
	process_user_id BIGINT (15) COMMENT '用户id',
	PRIMARY KEY (process_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'process';
