declare  @x int,@y int
set @x=8
set @y=-3
if @x>0
  if @y>0 
    print'@x@yλ�ڵ�һ����'
  else 
    print'@x@yλ�ڵ�������'
else 
  if @y>0
    print'@x@yλ�ڵڶ�����'
  else 
    print'@x@yλ�ڵ�������'
