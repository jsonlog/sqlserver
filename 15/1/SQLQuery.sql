USE db_2012			--�������ݿ�
SELECT * FROM Employee WHERE ID = 001
BEGIN TRANSACTION update_data 	--��ʼ����
  UPDATE Employee SET Name = '����'	            --�޸�����
    Where ID = 1 		    --���� 
	COMMIT TRANSACTION update_data 	
	SELECT * FROM Employee  WHERE ID =001