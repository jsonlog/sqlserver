USE db_2012
IF EXISTS(SELECT NAME FROM SYSINDEXES WHERE NAME = 'I_Stu')
DROP INDEX Student.I_Stu
GO
CREATE INDEX I_Stu
ON Student(Sno)
GO
DECLARE @Inx_ID INT
SET @Inx_ID = (SELECT INDEX_ID 
		FROM SYS.indexes 
		WHERE OBJECT_ID = OBJECT_ID('Student')
		AND NAME = 'I_Stu')
DBCC CHECKTABLE('Student',@Inx_ID)