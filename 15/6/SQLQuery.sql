SET TRANSACTION ISOLATION LEVEL Read Committed
BEGIN TRANSACTION
SELECT * FROM Employee
ROLLBACK TRANSACTION
SET TRANSACTION ISOLATION LEVEL Read Committed    --�����ύ�����뼶��
UPDATE Employee SET Name = '����'
