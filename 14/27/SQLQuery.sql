--��Employee����ΪΩһ����
CREATE UNIQUE CLUSTERED INDEX MR_Emp_ID_FIND ON Employee (ID)
WITH IGNORE_DUP_KEY
--�ж�db_2012���ݿ��Ƿ���Դ���ȫ������
if (select DatabaseProperty('db_2012','IsFulltextEnabled'))=0 
EXEC sp_fulltext_database 'enable'   --���ݿ�����ȫ������
EXEC sp_fulltext_catalog 'ML_Employ','create'    --����ȫ������Ŀ¼ΪML_Employ
EXEC sp_fulltext_table 'Employee','create','ML_Employ','MR_Emp_ID_FIND' --������ȫ���������
EXEC sp_fulltext_column 'Employee','Name','add'    --���ȫ�������ֶ�
EXEC sp_fulltext_table 'Employee','activate'   --����ȫ������
EXEC sp_fulltext_catalog 'ML_Employ','start_full'    --�������ȫ����������ȫ���


 