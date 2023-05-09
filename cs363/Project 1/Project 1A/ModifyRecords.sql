USE Project1A;

UPDATE students SET name='Scott' WHERE ssn='746897816';
UPDATE major SET name='Computer Science',level='MS' WHERE snum=746897816;
DELETE FROM register WHERE regtime='Spring2021';