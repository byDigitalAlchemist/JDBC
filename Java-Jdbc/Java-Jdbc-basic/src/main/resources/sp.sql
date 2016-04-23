DROP PROCEDURE IF EXISTS `get_count_for_department`
/
CREATE  PROCEDURE `get_count_for_department`(IN the_department VARCHAR(64), OUT the_count INT)
BEGIN
	
	SELECT COUNT(*) INTO the_count FROM employees where department=the_department;

END
/
DROP PROCEDURE IF EXISTS `get_employees_for_department`
/
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_employees_for_department`(IN the_department VARCHAR(64))
BEGIN

	SELECT * from employees where department=the_department;

END
/ 
DROP PROCEDURE IF EXISTS `greet_the_department`
/
CREATE DEFINER=`root`@`localhost` PROCEDURE `greet_the_department`(INOUT department VARCHAR(64))
BEGIN

	SET department = concat('Hello to the awesome ', department, ' team!');

END
/
DROP PROCEDURE IF EXISTS `increase_salaries_for_department`
/
CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_salaries_for_department`(IN the_department VARCHAR(64), IN increase_amount DECIMAL(10,2))
BEGIN

	UPDATE employees SET salary= salary + increase_amount where department=the_department;

END
/