USE db_2012						  --�������ݿ�
DECLARE ReadCursor CURSOR FOR			 --����һ���α�
SELECT * FROM Student
OPEN ReadCursor							 --���α�
FETCH NEXT FROM ReadCursor				  --ִ��ȡ������
WHILE @@FETCH_STATUS=0				   --���@@FETCH_STATUS����ȷ���Ƿ񻹿��Լ���ȡ��
BEGIN
  FETCH NEXT FROM ReadCursor
END
