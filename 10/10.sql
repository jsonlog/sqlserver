USE db_2012
GO
--������ͼ
CREATE VIEW Lesson_Profession_view
WITH ENCRYPTION	--����(��ԭ�ı�ת��Ϊģ����ʽ)
AS
--����SELECT��ѯ���
SELECT a.sname,a.sage, b.grade, a.sno FROM Student AS a INNER JOIN  SC AS b ON a.sno = b.sno
GO
--�鿴��������ͼ�е�����
EXEC sp_helptext Lesson_Profession_view
