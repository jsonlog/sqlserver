USE db_2012
SELECT Age,count(Age)���� FROM Employee
GROUP BY Age
HAVING count(Age) >= 2

