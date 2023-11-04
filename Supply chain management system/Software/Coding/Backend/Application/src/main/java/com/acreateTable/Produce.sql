-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS produce;
CREATE TABLE produce(
	produce_key VARCHAR (50) NOT NULL COMMENT '主键',
	produce_name VARCHAR (50) COMMENT '农产品名称',
	produce_num VARCHAR (50) COMMENT '批次号',
	produce_type VARCHAR (50) COMMENT '品种',
	produce_date DATETIME COMMENT '生产日期',
	produce_address VARCHAR (50) COMMENT '生产地点',
	produce_cycle VARCHAR (50) COMMENT '生产周期',
	produce_person VARCHAR (50) COMMENT '生产人员',
	produce_fresh VARCHAR (50) COMMENT '新鲜度',
	produce_c VARCHAR (50) COMMENT '环境温度',
	produce_humidity VARCHAR (50) COMMENT '环境湿度',
	produce_supplier_name VARCHAR (50) COMMENT '生产商名称',
	produce_sub_name VARCHAR (50) COMMENT '下级商家名称',
	produce_user_id BIGINT (15) COMMENT '用户id',
	PRIMARY KEY (produce_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'produce';
