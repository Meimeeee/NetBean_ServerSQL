CREATE TABLE rooms(
	id INT PRIMARY KEY,
	type VARCHAR(50),
	price DECIMAL(5,2),
	status INT
	);

CREATE TABLE guests(
	id INT PRIMARY KEY,
	name VARCHAR(50),
	email VARCHAR(50),
	phone VARCHAR(50)
);

CREATE TABLE bookings(
	id INT PRIMARY KEY,
	room_id INT,
	guest_id INT,
	check_in DATE,
	check_out DATE,
	FOREIGN KEY (room_id) REFERENCES rooms(id),
	FOREIGN KEY (guest_id) REFERENCES guests(id)
);