CREATE DATABASE BHT

GO

USE BHT

CREATE TABLE Client (
	id INT NOT NULL PRIMARY KEY IDENTITY,
	username NVARCHAR(20) NOT NULL UNIQUE,
	password NVARCHAR(20) NOT NULL,
	email NVARCHAR(50) NOT NULL UNIQUE,
	gender BIT,
	hasAvatar BIT,
);

INSERT INTO Client(username,password,email,gender,hasAvatar) VALUES
('bht','123456','bht@elca.vn',1,0),
('lav','123456','lav@elca.vn',1,0),
('kdq','123456','kdq@elca.vn',1,0),
('nar','123456','nar@elca.vn',0,0),
('xdt','123456','xdt@elca.vn',0,0);

SELECT * FROM Client;