use db_2012--�����ݿ�
if EXISTS (SELECT name 	--�жϴ������Ƿ��Ѿ�����
	FROM   sysobjects 
	WHERE  name = 'delete_employee' --������delete_employee
	AND type = 'TR')--������DDL������
drop trigger delete_employee--����Ѿ�������ɾ��
go
  create trigger delete_employee--���´���delete_employee������
   on employee3	--�����ڱ�employee3
    after delete as --ɾ����¼���
       declare @rowcount int --����һ�����ͱ���
       select @rowcount = @@rowcount --��@rowcount������ֵ
  if @rowcount>1 --���ɾ����¼����1����
       begin
		print('��ǰɾ���ļ�¼��������һ����һ��ֻ����ɾ��һ��')
          rollback transaction	--��������
       end
  if @rowcount=1 --ɾ����¼Ϊ1��
      begin
          declare @Name varchar(50) 		
          select @Name = Name from deleted	--��ȡҪɾ��������
      	delete from employee3 where Name = @Name --ɾ����Ӧ�����ļ�¼
      end
go
