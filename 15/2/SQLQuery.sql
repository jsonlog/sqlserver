USE db_2012	--�����ݾ�
SELECT * FROM Employee
BEGIN TRANSACTION INSERT_DATA      	--��ʼ����
  INSERT INTO Employee 
  VALUES('16','����˫','Ů','22')
COMMIT TRANSACTION INSERT_DATA    	--��������
GO
IF @@ERROR = 0 
  PRINT '�����¼�¼�ɹ���'			--�������ɹ�����Ϣ
GO
