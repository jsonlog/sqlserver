USE db_2012					 --�������ݿ�
DECLARE CloseCursor Cursor FOR		 --�����α�
SELECT * FROM  Student
FOR READ ONLY
OPEN CloseCursor					 --���α�
CLOSE CloseCursor					  --�ر��α�
