USE db_2012
DECLARE Cur_Emp_01 CURSOR FOR
SELECT * FROM Employee
FOR READ ONLY    --ֻ���α�
GO
