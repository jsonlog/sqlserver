ALTER VIEW View_Student(Sname,Sage)			--�޸��Ѵ��ڵ���ͼ
AS
SELECT Sname,Sage FROM Student WHERE sno='201109002'
go
EXEC sp_helptext 'View_student'				--�鿴��ͼ����
