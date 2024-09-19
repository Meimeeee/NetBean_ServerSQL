CREATE TABLE department(
	id VARCHAR(10) PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE employees(
	id INT PRIMARY KEY,
	name VARCHAR(50),
	id_department VARCHAR(10),
	salary DECIMAL(10,2)
	FOREIGN KEY (id_department) REFERENCES department(id)
);