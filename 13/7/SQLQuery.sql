
USE db_2012
DECLARE FreeCursor Cursor FOR			--�����α�
SELECT * FROM Student
OPEN FreeCursor							 --���α�
Close FreeCursor						 --�ر��α�
DEALLOCATE FreeCursor					--�ͷ��α�

