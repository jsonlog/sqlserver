use db_2012
GO
if exists(select name from sysobjects
where name='pro_out16'and type='p')		
drop proc pro_out16
GO
create procedure pro_out16
  @id int,								--����@id
  @score int outpu
as				
--����ѯ�������@score						
set @score=(select Score from score where ID=@id)
GO
declare @stuscore int --�����������
declare @stuid int--����ID����
set @stuid=1    							--��ID������ֵ
exec pro_out16 @stuid,@stuscore output--ִ�д洢���̣���@stuscore��ֵ
--�жϷ����ȼ�
if @stuscore>=85    			
  print rtrim(@stuscore)+'����ɼ�Ϊ�ߵ�ˮƽ'
else if @stuscore>=70
  print rtrim(@stuscore)+'����ɼ�Ϊ�е�ˮƽ'
else if @stuscore<70
  print rtrim(@stuscore)+'����ɼ�Ϊһ��ˮƽ'
go
