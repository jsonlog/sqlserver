USE db_2012
BEGIN TRANSACTION INSERT_DATA	--��ʼ����
	INSERT INTO employee4 VALUES(6,'������','Ů',25,1111111) --��������
	SELECT * FROM employee4			--��ѯ���
COMMIT TRANSACTION INSERT_DATA	--��������		
GO
