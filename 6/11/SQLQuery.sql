use db_2012
go
select *,
��ע=case 
when Grade>=90 then '�ɼ�����'
when Grade<90 and Grade>=80  then '�ɼ�����'
when Grade<80 and Grade>=70  then '�ɼ�����'
else '������'
end 
from tb_Grade
