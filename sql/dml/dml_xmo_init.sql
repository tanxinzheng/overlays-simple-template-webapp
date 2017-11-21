/*
 Navicat MySQL Data Transfer

 Source Server         : docker-mysql
 Source Server Version : 50719
 Source Host           : 127.0.0.1
 Source Database       : xmo_demo

 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 11/08/2017 01:10:11 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
--  Records of `xmo_user`
-- ----------------------------
BEGIN;
INSERT INTO `xmo_user` VALUES ('f1a70ab2961c11e69aef080027b274d7', 'admin', '系统管理员', 'd4cb830ca964470f9d3866ae5e9a0c7b', '16f4980184b01e06a79f4f5a4039c4dc', 'admin@xmomen.com', '15000084483', '07f8eeb1ad7745d399509dead280f8aa', '0', '2017-07-29 11:21:31', '2017-11-08 01:05:23'), ('f60a85d77b8f11e7bfe902004c4f4f50', 'test', 'ces', '4ea33c9cc71944bd935e251cf424e45d', 'f0df6d45459a6ae0ebf54d44773b3b43', null, null, null, null, '2017-08-08 00:46:32', '2017-08-23 01:24:01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
