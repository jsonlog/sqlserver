UPDATE db_2012.dbo.employee4 SET �Ա� ='Ů' 
DECLARE cur1 CURSOR  --�����α�cur1
FOR SELECT ���,����,�Ա�,����,�绰����	--�α�����
FROM db_2012.dbo.employee4--������employee4
where ���=5	--Լ������¼�¼Ϊ5
FOR UPDATE OF ����,�Ա�	--�����α���ָ�����������Ա��ֶ�
OPEN cur1	--���α�
GO
FETCH NEXT FROM cur1  --��ȡ��ǰ��֮��Ľ���е�����
GO
UPDATE db_2012.dbo.employee1 SET ���� ='��С��'	--���±��е������ֶ�
WHERE CURRENT OF cur1
GO
CLOSE cur1	--�ر��α�
DEALLOCATE cur1	--�ͷ��α�
GO
