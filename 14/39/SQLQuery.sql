USE db_2012
Go
CREATE TABLE student3
(
����֤�� char(8)  not null  CONSTRAINT  py  PRIMARY KEY,
���� char(8)  not null  CONSTRAINT  uk  UNIQUE,
רҵ  char(12) not null,
�Ա�  bit  not null,
������ int  CHECK(������>=0 AND ������<=20) null
)
go 
