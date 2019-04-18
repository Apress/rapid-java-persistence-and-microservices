CREATE TABLE IF NOT EXISTS Customer (
  customerId int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
dateAdded datetime DEFAULT NULL,
email varchar(255) DEFAULT NULL,
name varchar(255) DEFAULT NULL,
password varchar(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `Order` ( -- escaping Order in `` characters
  orderId bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
customerId int(11) DEFAULT NULL,
price int(11) NOT NULL,
productId bigint(20) DEFAULT NULL,
quantity int(11) NOT NULL
);

CREATE TABLE IF NOT EXISTS Product (
  productId int(11) unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
name varchar(255) DEFAULT NULL,
quantity varchar(255) DEFAULT NULL,
price smallint DEFAULT NULL
);

Insert into Customer(name, email, password) VALUES('Raj', 'raj@example.com', 'password');
Insert into Product(name, quantity, price) VALUES('Laptop', 10, 1200);
Insert into `Order`(customerId, productId, price, quantity) VALUES(1, 1, 1200, 1);
