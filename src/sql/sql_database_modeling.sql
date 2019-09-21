create database banking_application;

drop table customer cascade; 
drop table account cascade;
drop table transaction cascade;
drop table customer_account cascade;
drop table account_type cascade;
drop table transaction_type cascade;


create table customer(
id_customer SERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
address VARCHAR(200) NOT NULL
);

create table account_type(
id_account_type SERIAL PRIMARY KEY,
type VARCHAR(50) NOT NULL
);

create table account(
id_account SERIAL PRIMARY KEY,
id_account_type INT NOT NULL,
account_balance INT NOT NULL,
interest_rate INT NOT NULL,
last_access TIMESTAMP NOT NULL,
FOREIGN KEY (id_account_type) REFERENCES account_type (id_account_type)
);

create table customer_account(
id_customer INT NOT NULL,
id_account INT NOT NULL,
FOREIGN KEY (id_customer) REFERENCES customer (id_customer),
FOREIGN KEY (id_account) REFERENCES account (id_account),
PRIMARY KEY (id_customer,id_account)
);

create table transaction_type(
id_transaction_type SERIAL PRIMARY KEY,
type VARCHAR(50) NOT NULL
);

create table transaction(
id_transaction SERIAL PRIMARY KEY,
id_transaction_type INT NOT NULL,
id_account INT NOT NULL,
value DECIMAL NOT NULL,
transaction_date TIMESTAMP NOT NULL,
FOREIGN KEY (id_transaction_type) REFERENCES transaction_type (id_transaction_type)
);

--Populate initial values

insert into account_type (type) values('Joint account');
insert into account_type (type) values('Single account');

insert into transaction_type (type) values('Checking');
insert into transaction_type (type) values('Saving');
insert into transaction_type (type) values('Transfer');
insert into transaction_type (type) values('Withdraw');