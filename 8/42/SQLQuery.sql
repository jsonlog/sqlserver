USE db_2012
SELECT Sname,Sage FROM Student 
WHERE Sex = '��'
UNION ALL
SELECT Cname,Credit FROM Course 
ORDER BY Sage ASC 