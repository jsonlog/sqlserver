USE db_2012
SELECT Sno,Cno,
�ȼ�=CASE 
WHEN Grade >= 90 then '����'
WHEN Grade >= 80 and Grade < 90 then '����'
WHEN Grade >= 70 and Grade < 80 then '�е�'
WHEN Grade >= 60 and Grade < 70 then '����'
ELSE '������'
END
FROM SC