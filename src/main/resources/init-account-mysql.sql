create database IF NOT EXISTS tinybank_account;

USE tinybank_account;

CREATE TABLE if not exists account (
    account_no INT auto_increment PRIMARY KEY,
    customer_id INT NOT NULL,
    alias VARCHAR(50),
    balance decimal(20,2), 
    active BOOLEAN NOT NULL
 );
 
insert into account (customer_id, alias, balance, active) VALUES ( 1, 'Arthur', 40.00, TRUE);
insert into account (customer_id, alias, balance, active) VALUES ( 2, 'Tom', 40.00, TRUE);
 
-- CREATE USER 'tester'@'localhost' IDENTIFIED BY 'tester';

GRANT ALL PRIVILEGES ON tinybank_account.* TO 'tester'@'%';

FLUSH PRIVILEGES;
 
 
 