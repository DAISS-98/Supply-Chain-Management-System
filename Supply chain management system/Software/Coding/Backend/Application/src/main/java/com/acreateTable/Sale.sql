-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS sale;
CREATE TABLE sale(
	sale_key VARCHAR (50) NOT NULL COMMENT '主键',
	sale_name VARCHAR (50) COMMENT '本级经销商名称',
	sale_on_date DATETIME COMMENT '上架时间',
	sale_fresh VARCHAR (50) COMMENT '新鲜度',
	sale_c VARCHAR (50) COMMENT '环境温度',
	sale_humidity VARCHAR (50) COMMENT '环境温度',
	sale_person VARCHAR (50) COMMENT '销售员',
	sale_price VARCHAR (50) COMMENT '销售单价（元/斤）',
	user_id BIGINT (15) COMMENT '用户id',
	PRIMARY KEY (sale_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'sale';
