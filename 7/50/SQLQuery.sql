SELECT tb1.����Ա,tb1.���۶� AS ������۶�
from ( SELECT ����Ա,SUM(���۶�) AS ���۶� 
       FROM tb_Seller
       WHERE MONTH(����)='12'
       GROUP BY ����Ա
     )AS tb1
WHERE tb1.���۶� = ( SELECT  MAX(���۶�) AS ������۶�
                        FROM  
                             ( SELECT ����Ա,SUM(���۶�) AS ���۶� 
                               FROM tb_Seller
                               WHERE MONTH(����)='12'
                               GROUP BY ����Ա
                             )AS tb1
                   )
