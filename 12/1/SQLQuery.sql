CREATE TRIGGER T_DML_Emp3--����������T_DML_Emp3
ON employee3	--�����ڱ�employee3
AFTER INSERT	--ִ�в������֮��
AS 
RAISERROR ('��������в�������', 16, 10); --��ʾ��Ϣ
