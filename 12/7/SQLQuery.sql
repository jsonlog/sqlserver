ALTER TRIGGER T_DDL_DATABASE	--修改触发器
ON DATABASE	--应用于当前数据库
FOR ALTER_TABLE
AS
RAISERROR ('只有“T_DDL_DATABASE”触发器无效时，才可以修改表。', 16, 10)
ROLLBACK    --回滚事务
