DECLARE khqc_Cursor CURSOR 						--�����α�
for select * FROM db_2012.dbo.goods
OPEN khqc_Cursor 								--���α�
BEGIN   								
   FETCH next FROM khqc_Cursor  					--��ȡ��¼���α귵��ָ������
END
CLOSE khqc_Cursor  								--�ر��α�
DEALLOCATE khqc_Cursor  							--�ͷ��α�
GO
