

USE db_2012
SELECT Sno ,Sname FROM Student
SELECT LEFT(Sname,1) AS '����', COUNT(LEFT(Sname,1)) AS '����' 
FROM Student Group BY LEFT(Sname,1)

 
