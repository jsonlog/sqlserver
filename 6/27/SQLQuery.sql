use db_2012
go
declare @name varchar(10)
declare @sex varchar(20)
set @name='��СС'
set @sex='Ů'
if exists(select * from dbo.Employee where Sex=@sex and Name=@name)
begin 
  print '���ڸ���Ա��Ϣ'
end
else if not exists(select * from dbo.Employee  where Sex=@sex and Name=@name)
begin 
  print '�����ڸ���Ա��Ϣ'
end

