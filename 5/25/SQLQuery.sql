use db_2012
go
if OBJECT_ID('books2') is not null drop table books2
create table books2
( 
  ��� varchar(30),
  ���� varchar(50),
  ���� varchar(50),
  ������ varchar(50),
  ���� money,
  ��� text
)
--��һ���е����ݲ��뵽�±�
insert into books2
select * from books where ������ ='����'
--��ѯ���ݱ�
select * from books2
