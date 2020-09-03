
CREATE DATABASE IF NOT EXISTS scala;

USE scala;


CREATE TABLE IF NOT EXISTS km ( 
	id INT AUTO_INCREMENT,
	datum DATE, 
	tachometr INT,
	PRIMARY KEY (id)
	);
	
CREATE USER IF NOT EXISTS 'scala'@'localhost' IDENTIFIED BY 'scala';

GRANT ALL PRIVILEGES ON scala.* TO 'scala'@'localhost';