CREATE TABLE student5
(
����֤�� char(8)  NOT NULL,
ISBN char(16) NOT NULL,
������ char(10) NOT NULL,
����ʱ�� date NOT NULL,
����ʱ�� date NOT NULL,
CONSTRAINT FK_point FOREIGN KEY (������,����֤��,����ʱ��) 
REFERENCES student4 (������,����֤��,����ʱ��)
ON DELETE NO ACTION
)