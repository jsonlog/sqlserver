USE db_2012
if exists(select name from sysindexes where name='ind_em')
drop index employee.ind_em	--����������ڣ�����ɾ��
GO
create index ind_em		--��������	
on employee(Name,Age)		--Ϊemployee���Name��Age�ֶδ���
GO
Select * from Employee
With (index(ind_em))		--ǿ��ʹ�õ�һ���ҵ�������
GO
