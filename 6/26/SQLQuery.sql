use db_2012
go
select ����,������,SUM(����) as �ܼƽ�� from books
group by GROUPING sets(����,������)
