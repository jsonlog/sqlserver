USE db_2012
IF object_id('insert_student') is not null 	--�жϸô������Ƿ����
DROP TRIGGER insert_student
GO
CREATE TRIGGER insert_student--����������
ON Student--����ѧ����
FOR INSERT--Լ��INSERT����
AS
DECLARE @Age INT--����ֲ�����
SELECT @Age = Sage FROM INSERTED--�洢���������	
IF(@Age > 50 OR @Age < 10)--Լ�����䷶Χ
BEGIN
	PRINT '���䲻�������²���'
	ROLLBACK TRANSACTION--�ع�����
END
GO
--INSERT INTO STUDENT VALUES(201109010,'XX','DF',100)
