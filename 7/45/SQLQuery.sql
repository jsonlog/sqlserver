




USE db_2012   --�������ݿ�
CREATE TABLE mytable    --�������ݱ�
 (USERID int,
 USERNAME varchar(20),
 USERSEX nvarchar (2),
 USERBIRTHDAY DATETIME,
 USERADDRESS TEXT,
 )
GO    --ʹ��COL_LENGTH���������ֶε����ͳ���
SELECT COL_LENGTH ( 'mytable' , 'USERID' ) AS 'int���ͳ���',
       COL_LENGTH ( 'mytable' , 'USERNAME' ) AS 'varchar���ͳ���',
	       COL_LENGTH ( 'mytable' , 'USERSEX' ) AS 'nvarchar���ͳ���',
	       COL_LENGTH ( 'mytable' , 'USERBIRTHDAY' ) AS 'DATETIME���ͳ���',
	       COL_LENGTH ( 'mytable' , 'USERADDRESS' )AS 'TEXT���ͳ���'
	GO
	DROP table mytable    --ɾ�����ݱ�
