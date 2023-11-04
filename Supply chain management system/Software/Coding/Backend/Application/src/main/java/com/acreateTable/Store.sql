-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS store;
CREATE TABLE store(
	store_key VARCHAR (50) NOT NULL COMMENT '主键',
	store_name VARCHAR (50) COMMENT '仓储商名称',
	store_address VARCHAR (50) COMMENT '仓储地点',
	store_person VARCHAR (50) COMMENT '管理员',
	store_intime VARCHAR (50) COMMENT '入库时间',
	store_fresh VARCHAR (50) COMMENT '入库新鲜度',
	store_c VARCHAR (50) COMMENT '仓储温度',
	store_humidity VARCHAR (50) COMMENT '仓储湿度',
	store_info VARCHAR (50) COMMENT '仓储其他信息',
	store_other VARCHAR (50) COMMENT '其他',
	store_user_id BIGINT (15) COMMENT '用户id',
	PRIMARY KEY (store_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'store';
