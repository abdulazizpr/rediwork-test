1. SELECT * FROM employees
2. SELECT COUNT(*) FROM employees WHERE job_title = "Manager"
3. SELECT name, salary FROM employees WHERE job_title IN ("Sales", "Marketing")
4. SELECT AVG(salary) FROM employees WHERE YEAR(joined_date) >= YEAR(currdate()) - 5;
5. SELECT name, SUM(sales) as total_sales FROM employees JOIN sales_data ON employees.id = sales_data.employee_id GROUP BY employees.id ORDER BY total_sales DESC LIMIT 5;
6. SELECT name, salary FROM employees WHERE salary > (SELECT AVG(salary) FROM employees);
7. SELECT name, SUM(sales) as total_sales FROM employees JOIN sales_data ON employees.id = sales_data.employee_id GROUP BY employees.id ORDER BY total_sales DESC;