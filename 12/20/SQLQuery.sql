CREATE TRIGGER mr--����һ��������mr
ON employee3--�����ڱ�employee3
AFTER INSERT--ִ��insert����֮��
AS 
Print ('��������в�������');--��ӡ��ʾ��Ϣ	
go
Insert into employee3 values(5,'������trigger',25)
