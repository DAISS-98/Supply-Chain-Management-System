-- auto Generated on 2022-08-26
-- DROP TABLE IF EXISTS file_path_entity;
CREATE TABLE file_path(
	file_path VARCHAR (200) COMMENT '文件路径（映射本地文件名）',
	appendix_size VARCHAR (200) COMMENT '附件大小;',
	file_name VARCHAR (200) COMMENT '文件名称',
	guid VARCHAR (200) NOT NULL COMMENT '文件唯一id标识',
	PRIMARY KEY (guid)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'file_path';
