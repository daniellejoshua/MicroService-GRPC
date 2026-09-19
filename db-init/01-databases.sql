CREATE USER customer_user WITH PASSWORD 'customer_pass';
CREATE DATABASE customer_db OWNER customer_user;

CREATE USER billing_user WITH PASSWORD 'billing_pass';
CREATE DATABASE billing_db OWNER billing_user;