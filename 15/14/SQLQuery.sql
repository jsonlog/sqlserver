USE db_2012
BEGIN TRANSACTION T_A				--��ʼ����T_A
	BEGIN TRANSACTION T_B			--��ʼ����T_B
	INSERT INTO Student VALUES(201109011,'AA','��',26)
	COMMIT TRANSACTION T_B			--�ύ����T_A
ROLLBACK TRANSACTION T_A			--�ع�����T_B
--SELECT * FROM Student				--�鿴Student��