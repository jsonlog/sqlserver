ALTER TRIGGER T_DML_Emp3			
ON employee3
AFTER INSERT,UPDATE,DELETE
AS 
RAISERROR ('��������в��롢�޸Ļ�ɾ������', 16, 10);
