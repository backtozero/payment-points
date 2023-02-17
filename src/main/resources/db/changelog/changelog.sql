-- liquibase formatted sql

-- changeset mike:liquibase:1
CREATE TABLE payment_methods (payment_method_name varchar(255) PRIMARY KEY, price_modifier_start decimal, price_modifier_end decimal, points_coefficient decimal, description varchar(255));
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('CASH', 0.9, 1.05, 0.05, NULL);
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('CASH_ON_DELIVERY', 1, 1.02, 0.05, 'Should accept the following courier services only ● YAMATO ● SAGAWA');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('VISA', 0.95, 1, 0.03, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('MASTERCARD', 0.95, 1, 0.03, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('AMEX', 0.98, 1.01, 0.02, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('JCB', 0.95, 1, 0.05, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('LINE PAY', 1, 1, 0.01, NULL);
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('PAYPAY', 1, 1, 0.01, NULL);
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('POINTS', 1, 1, 0, NULL);
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('GRAB PAY', 1, 1, 0.01, NULL);
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('BANK TRANSFER', 1, 1, 0, 'Should accept and store bank and account number information');
INSERT INTO payment_methods (payment_method_name, price_modifier_start, price_modifier_end, points_coefficient, description)
VALUES ('CHEQUE', 0.9, 1, 0, 'Should accept and store bank and account number information');

-- changeset mike:liquibase:2
CREATE TABLE sales_history (event_timestamp timestamp(3) PRIMARY KEY, request_timestamp timestamp(3), customer_id varchar(255), payment_method_name varchar(255) REFERENCES payment_methods,  price decimal, points_earned decimal, additional_info jsonb);
CREATE TABLE couriers (courier_name varchar(255) PRIMARY KEY, description varchar(255));
INSERT INTO couriers (courier_name, description) VALUES ('YAMATO', NULL);
INSERT INTO couriers (courier_name, description) VALUES ('SAGAWA', NULL);
INSERT INTO couriers (courier_name, description) VALUES ('COMMON COURIER', NULL);
CREATE TABLE payment_method_couriers (payment_method_name varchar(255) REFERENCES payment_methods, courier_name varchar(255) REFERENCES couriers, PRIMARY KEY (payment_method_name, courier_name));
INSERT INTO payment_method_couriers (payment_method_name, courier_name) VALUES ('CASH_ON_DELIVERY', 'YAMATO');
INSERT INTO payment_method_couriers (payment_method_name, courier_name) VALUES ('CASH_ON_DELIVERY', 'SAGAWA');
CREATE TABLE additional_fields_to_save (field_name varchar(255) PRIMARY KEY, description varchar(255));
INSERT INTO additional_fields_to_save (field_name, description) VALUES ('last4', 'Last 4 digits of the card');
INSERT INTO additional_fields_to_save (field_name, description) VALUES ('bank', 'Bank name');
INSERT INTO additional_fields_to_save (field_name, description) VALUES ('account', 'Account number');
INSERT INTO additional_fields_to_save (field_name, description) VALUES ('cheque', 'Cheque number');
CREATE TABLE payment_method_additional_fields_to_save (payment_method_name varchar(255) REFERENCES payment_methods, field_name varchar(255) REFERENCES additional_fields_to_save, PRIMARY KEY (payment_method_name, field_name));
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('VISA', 'last4');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('MASTERCARD', 'last4');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('AMEX', 'last4');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('JCB', 'last4');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('BANK_TRANSFER', 'bank');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('BANK_TRANSFER', 'account');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('CHEQUE', 'bank');
INSERT INTO payment_method_additional_fields_to_save (payment_method_name, field_name) VALUES ('CHEQUE', 'cheque');

