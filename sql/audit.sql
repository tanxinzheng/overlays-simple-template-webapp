alter table `xmo_permission`
add column `CREATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
add column `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
add column `UPDATED_USER_ID` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
add column `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
add column `DATA_VERSION` int(10) NOT NULL DEFAULT '0' COMMENT '数据版本号';