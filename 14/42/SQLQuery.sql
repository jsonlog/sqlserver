--������ͼ
CREATE  VIEW V_Score 
WITH SCHEMABINDING		--�󶨵��ܹ�
AS 
   SELECT stu.Sname,stu.Sex,stu1.BestScore
   FROM   dbo.student as stu, dbo.student1 as stu1 
   WHERE  stu.Sno = stu1.ID
GO
--����ͼV_Score�ϴ�������
create unique clustered index I_Score on V_Score (Sname,Sex,BestScore)
select * from V_Score		--����ͼV_Score�ϲ�ѯ����
with(index(I_Score))		--ʹ������I_Score	
