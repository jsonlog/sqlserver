use db_2012
go
--������ͼ
create view v1
as
--����select���
select ID,Name,Sex,Age from 
Employee
go
--��ѯ����������ͼ������
select * from v1
