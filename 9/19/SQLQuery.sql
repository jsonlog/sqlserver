USE db_2012
UPDATE Student
SET Sage=
CASE WHEN Sex= '��' THEN Sage - 1
WHEN Sex = 'Ů' THEN Sage + 1
END 