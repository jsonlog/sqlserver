USE db_2012
--�жϱ����Ƿ���Ҫɾ��������
If EXISTS(Select * from sysindexes where name='IX _Stu_Sno1')
 Drop Index Student.IX_Stu_Sno1
