USE db_2012   	--�����ݿ�
SELECT * FROM Employee		--��ʾEmployee������
BEGIN TRANSACTION UPDATE_DAT			        --��ʼ����
  UPDATE Employee SET Name = '��˫'  	        --�޸�Ա����Ϣ
  WHERE ID = 16
  DELETE Employee WHERE ID = 16	        	    --ɾ��ָ����Ա����¼
COMMIT TRANSACTION UPDATE_DATA			           --�ύ����

