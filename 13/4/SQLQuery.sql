USE db_2012
DECLARE Emp_01 CURSOR FOR     --�����α�
SELECT * FROM Employee
WHERE ID = '1'
OPEN Emp_01                   --���α�
GO
