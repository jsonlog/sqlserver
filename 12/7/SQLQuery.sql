ALTER TRIGGER T_DDL_DATABASE	--�޸Ĵ�����
ON DATABASE	--Ӧ���ڵ�ǰ���ݿ�
FOR ALTER_TABLE
AS
RAISERROR ('ֻ�С�T_DDL_DATABASE����������Чʱ���ſ����޸ı�', 16, 10)
ROLLBACK    --�ع�����
