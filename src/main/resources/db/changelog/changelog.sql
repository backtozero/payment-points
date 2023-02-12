-- liquibase formatted sql

-- changeset mike:liquibase:1
CREATE TABLE payment_method_points (payment_method varchar(255), price_modifier varchar(255), points decimal, additional_items varchar(255));
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('CASH', 'price * 0.9 ~ price * 1.05', 0.05, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('CASH_ON_DELIVERY', 'price ~ price * 1.02', 0.05, 'Should accept the following courier services only ● YAMATO ● SAGAWA');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('VISA', 'price * 0.95 ~ price * 1', 0.03, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('MASTERCARD', 'price * 0.95 ~ price * 1', 0.03, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('AMEX', 'price * 0.98 ~ price * 1.01', 0.02, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('JCB', 'price * 0.95 ~ price * 1', 0.05, 'Should accept and store last 4 digits of the card');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('LINE PAY', 'price', 0.01, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('PAYPAY', 'price', 0.01, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('PAYPAY', 'price', 0.01, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('POINTS', 'price', 0, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('GRAB PAY', 'price', 0.01, NULL);
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('BANK TRANSFER', 'price', 0, 'Should accept and store bank and account number information');
INSERT INTO payment_method_points (payment_method, price_modifier, points, additional_items)
VALUES ('CHEQUE', 'price * 0.9 ~ price', 0, 'Should accept and store bank and cheque number information');
