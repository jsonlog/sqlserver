use db_2012
go
--������ͼ
create view vv1
as
--����SELECT��ѯ���
select s.sname,s.sage ,b.grade from student as s inner join Sc as b on s.Sno=b.Sno
go
--��ȡ��ͼ����Ϣ
exec sp_helptext 'vv1'
