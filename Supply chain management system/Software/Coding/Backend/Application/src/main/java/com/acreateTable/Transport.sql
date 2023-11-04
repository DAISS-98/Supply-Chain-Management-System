-- auto Generated on 2023-01-16
-- DROP TABLE IF EXISTS transport;
CREATE TABLE transport(
	transport_key VARCHAR (50) NOT NULL COMMENT '主键',
	transport_name VARCHAR (50) COMMENT '运输商名称',
	transport_person VARCHAR (50) COMMENT '运输人',
	transport_begin_address VARCHAR (50) COMMENT '出发地',
	transport_begin_date VARCHAR (50) COMMENT '出发时间',
	transport_begin_fresh VARCHAR (50) COMMENT '出发时新鲜度',
	transport_end_address VARCHAR (50) COMMENT '目的地',
	transport_end_date VARCHAR (50) COMMENT '到达时间',
	transport_end_fresh VARCHAR (50) COMMENT '到达时新鲜度',
	transport_c VARCHAR (50) COMMENT '运输温度',
	transport_humidity VARCHAR (50) COMMENT '运输湿度',
	transport_info VARCHAR (50) COMMENT '运输行为信息',
	transport_user_id BIGINT (15) COMMENT '用户id',
	PRIMARY KEY (transport_key)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'transport';
