USE db_2012
SELECT * FROM student WHERE Sno='201109008'	--��ѯ�޸�ǰ�ı�
BEGIN TRANSACTION update_data				--��ʼ����
	UPDATE student SET Sname='��ѩ'			--�޸�����
	WHERE Sno='201109008'					
COMMIT TRANSACTION update_data				--��������
SELECT * FROM student WHERE Sno='201109008'	--��ѯ�޸ĺ�ı�
