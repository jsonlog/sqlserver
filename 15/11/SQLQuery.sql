Set Xact_Abort on
Begin DISTRIBUTED TRANSACTION
Update Employee set Name = '����' where ID = 1	
Update [MR].[ db_CSharp].[dbo].[ Employee] set Name  = '����' where ID = 1	
COMMIT TRANSACTION	
