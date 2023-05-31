USE Project1A;

SELECT snum,ssn FROM students WHERE name='Becky';
SELECT m.name FROM major m JOIN students s on s.snum = m.snum WHERE s.ssn='123097834';
SELECT c.name FROM courses c JOIN departments d on c.department_code = d.code WHERE d.name='Computer Science';
SELECT d.name,d.level FROM degrees d JOIN departments p on d.department_code = p.code WHERE p.name='Computer Science';
SELECT s.name FROM students s JOIN minor m on m.snum = s.snum;
SELECT COUNT(*) FROM minor;
SELECT s.name,s.snum FROM students s JOIN register r on r.snum = s.snum JOIN courses c on c.number = r.course_number WHERE c.name='Algorithm';
SELECT name, snum FROM students WHERE dob=(SELECT MIN(dob) FROM students);
SELECT name, snum FROM students WHERE dob=(SELECT MAX(dob) FROM students);
SELECT name, snum, ssn FROM students WHERE name LIKE '%n%' or name LIKE '%N%';
SELECT name, snum, ssn FROM students WHERE name NOT LIKE '%n%' or name NOT LIKE '%N%';
SELECT c.number,c.name,COUNT(*) FROM courses c JOIN register r on c.number = r.course_number GROUP BY number ORDER BY COUNT(*) DESC;
SELECT s.name FROM students s JOIN register r on r.snum = s.snum WHERE r.regtime='Fall2020';
SELECT c.number,c.name FROM courses c JOIN departments d on d.code = c.department_code WHERE d.name='Computer Science';
SELECT c.number,c.name FROM courses c JOIN departments d on d.code = c.department_code WHERE d.name='Computer Science' OR d.name='Landscape Architect';