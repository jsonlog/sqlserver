USE db_2012
BEGIN TRANSACTION T_A				--开始事务T_A
	BEGIN TRANSACTION T_B			--开始事务T_B
	INSERT INTO Student VALUES(201109011,'AA','男',26)
	COMMIT TRANSACTION T_B			--提交事务T_A
ROLLBACK TRANSACTION T_A			--回滚事务T_B
--SELECT * FROM Student				--查看Student表
