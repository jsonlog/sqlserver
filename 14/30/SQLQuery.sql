USE db_2012
GO
EXEC sp_fulltext_database 'enable'    --���ݿ�����ȫ������
EXEC sp_fulltext_catalog 'QWML','rebuild'
