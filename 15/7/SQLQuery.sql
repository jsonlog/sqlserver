SET TRANSACTION ISOLATION LEVEL Repeatable Read
BEGIN TRANSACTION
SELECT * FROM Employee
ROLLBACK TRANSACTION 
SET TRANSACTION  ISOLATION LEVEL Repeatable Read  --���ÿ��ظ������뼶��
INSERT INTO Employee values ('18','����','��','22,'���տƼ�'')


