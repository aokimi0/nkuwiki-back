-- ------------ 修复开始 ------------

-- 1. 修复核心问题：数据库方言不匹配
-- 说明：您执行的是 PostgreSQL (PG) 脚本，但您的数据库是 MySQL。很多数据类型和语法不兼容。

-- 2. 修复因数据类型 'bytea' 导致创建失败的 'infra_file_content' 表
-- 错误日志：[42000][1064] You have an error in your SQL syntax; ... near 'bytea ...'
-- 失败原因：MySQL 没有 'bytea' 类型。这导致 `CREATE TABLE infra_file_content` 语句完全失败。
-- 修复操作：先确保删除可能存在的错误表，然后用 MySQL 正确的语法重新创建它。
DROP TABLE IF EXISTS `infra_file_content`;
CREATE TABLE `infra_file_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `config_id` bigint NOT NULL COMMENT '配置编号',
  `path` varchar(512) NOT NULL COMMENT '文件路径',
  `content` longblob NOT NULL COMMENT '文件内容 (原 bytea 类型)',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='文件内容表';


-- 3. 修复上一轮交互中提到的 'system_oauth2_access_token' 表缺失 'user_info' 字段的问题
-- 错误日志：Unknown column 'user_info' in 'field list'
-- 失败原因：您的代码版本较新，需要 `user_info` 字段，但数据库表结构是旧的。
-- 修复操作：为该表添加 `user_info` 字段。
-- 我们先检查字段是否存在，避免重复添加导致报错。
DELIMITER $$
CREATE PROCEDURE AddUserInfoColumn()
BEGIN
    IF NOT EXISTS(SELECT * FROM information_schema.columns WHERE table_schema=DATABASE() AND table_name='system_oauth2_access_token' AND column_name='user_info') THEN
        ALTER TABLE `system_oauth2_access_token` ADD COLUMN `user_info` TEXT NULL DEFAULT NULL COMMENT '用户信息' AFTER `user_type`;
    END IF;
END $$
DELIMITER ;
CALL AddUserInfoColumn();
DROP PROCEDURE AddUserInfoColumn;


-- 4. 修复因无效日期导致 'system_dict_type' 表数据插入失败的问题
-- 错误日志：Data truncation: Incorrect datetime value: '1970-01-01 00:00:00' for column 'deleted_time'
-- 失败原因：MySQL 在严格模式下，不允许 '1970-01-01 00:00:00' 作为 DATETIME 的有效值。
-- 修复操作：将 `deleted_time` 字段中这个无效的默认值更新为 NULL。
UPDATE `system_dict_type` SET `deleted_time` = NULL WHERE `deleted_time` = '1970-01-01 00:00:00';


-- 5. 修复因无效日期导致 'system_tenant' 表数据插入失败的问题
-- 错误日志：Data truncation: Incorrect datetime value: '2099-02-19 17:14:16' for column 'expire_time'
-- 失败原因：这个通常是因为 `expire_time` 字段被错误地创建为了 `TIMESTAMP` 类型，它的范围只到 2038 年。
-- 修复操作：先将字段类型修正为 `DATETIME`，然后更新可能被错误插入的值。
ALTER TABLE `system_tenant` MODIFY COLUMN `expire_time` DATETIME NULL DEFAULT NULL COMMENT '过期时间';
-- 尝试重新插入或更新之前失败的数据行（此处需要您根据业务手动操作，因为原始 INSERT 语句已失败）。
-- 如果您知道是哪条租户数据，可以手动将其 `expire_time` 设置为 '2099-02-19 17:14:16'。


-- 6. (说明) 关于 'dual' 表的错误
-- 错误日志：You have an error in your SQL syntax; ... near 'dual'
-- 失败原因：`dual` 是 Oracle 等数据库特有的伪表，MySQL 不需要它。
-- 修复操作：无需任何操作。这个错误可以安全地忽略。


-- 7. (可选但建议) 整体修正 PostgreSQL 特有数据类型
-- 说明：PG 脚本中的 int8, int4, int2, bool 在 MySQL 中可能被不精确地创建。
-- 下面的语句可以修正这些潜在的问题，建议执行。
ALTER TABLE `infra_api_access_log` MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT, MODIFY COLUMN `user_id` BIGINT NOT NULL DEFAULT 0, MODIFY COLUMN `user_type` TINYINT NOT NULL DEFAULT 0;
ALTER TABLE `infra_api_error_log` MODIFY COLUMN `id` INT NOT NULL AUTO_INCREMENT, MODIFY COLUMN `user_id` INT NOT NULL DEFAULT 0, MODIFY COLUMN `user_type` TINYINT NOT NULL DEFAULT 0;
ALTER TABLE `infra_codegen_table` MODIFY COLUMN `scene` TINYINT NOT NULL DEFAULT 1;
ALTER TABLE `infra_file_config` MODIFY COLUMN `storage` TINYINT NOT NULL, MODIFY COLUMN `master` bit(1) NOT NULL;
ALTER TABLE `system_users` MODIFY COLUMN `sex` TINYINT DEFAULT 1;


-- ------------ 修复结束 ------------