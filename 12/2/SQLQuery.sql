CREATE TRIGGER T_DDL_DATABASE	--����DDL������
ON DATABASE	--���ô�����Ӧ���ڵ�ǰ���ݿ�
FOR DROP_TABLE, ALTER_TABLE	--�Ա��޸�ʱ����ʾ��Ϣ
AS
PRINT 'ֻ�С�T_DDL_DATABASE����������Чʱ���ſ���ɾ�����޸ı�'
ROLLBACK		--�ع�����
