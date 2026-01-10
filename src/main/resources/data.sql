DELETE FROM user_role;
DELETE FROM vote;
DELETE FROM dish;
DELETE FROM users;
DELETE FROM restaurant;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100),
       ('ADMIN', 101),
       ('USER', 101);

INSERT INTO RESTAURANT (name)
VALUES ('Ukrainochka'),
       ('Macdonald''s'),
       ('Amedeo'),
       ('New Era'),
       ('Kapadokya');

INSERT INTO DISH (name, price, dish_date, restaurant_id)
VALUES ('Varenyky with Cherry', '950', DATEADD('day', -1, CURRENT_DATE), 10),
       ('Borscht', '1400', CURRENT_DATE, 10),
       ('Meat Dumplings', '1550', CURRENT_DATE, 10),
       ('Vinegret', '1250', CURRENT_DATE, 10),
       ('Blini with Meat 3 pcs', '1550', DATEADD('day', 1, CURRENT_DATE), 10),

       ('French Fries', '465', DATEADD('day', -1, CURRENT_DATE), 11),
       ('Hamburger', '630', DATEADD('day', -1, CURRENT_DATE), 11),
       ('Cheeseburger', '655', DATEADD('day', -1, CURRENT_DATE), 11),
       ('Chicken McNuggets 4 pcs', '600', DATEADD('day', -1, CURRENT_DATE), 11),
       ('French Fries', '465', CURRENT_DATE, 11),
       ('Grand Big Mac', '2095', CURRENT_DATE, 11),
       ('Big Tasty', '1795', DATEADD('day', 1, CURRENT_DATE), 11),
       ('French Fries', '465', DATEADD('day', 1, CURRENT_DATE), 11),

       ('Capricciossa Pizza', '2500', CURRENT_DATE, 12),
       ('Caesar Salad', '2300', CURRENT_DATE, 12),
       ('Cream of Mushroom soup', '1400', DATEADD('day', 1, CURRENT_DATE), 12),
       ('Pasta Boloneze', '2000', DATEADD('day', 1, CURRENT_DATE), 12),

       ('Kharcho', '2550', DATEADD('day', -1, CURRENT_DATE), 13),
       ('Khinkali Kalakuri 5 pcs', '1550', DATEADD('day', -1, CURRENT_DATE), 13),
       ('Lobiani', '1800', DATEADD('day', -1, CURRENT_DATE), 13),
       ('Adjarian Khachapuri', '2300', DATEADD('day', 1, CURRENT_DATE), 13),
       ('Chikhirtma', '2200', DATEADD('day', 1, CURRENT_DATE), 13),
       ('Cucumber-tomato Salad', '1950', DATEADD('day', 1, CURRENT_DATE), 13),

       ('Mercimek', '1000', DATEADD('day', -1, CURRENT_DATE), 14),
       ('Chicken with Rice', '3000', DATEADD('day', -1, CURRENT_DATE), 14),
       ('Sutlac', '1200', DATEADD('day', -1, CURRENT_DATE), 14),
       ('Mercimek', '1000', CURRENT_DATE, 14),
       ('Lamb chops', '4500', CURRENT_DATE, 14),
       ('Lahmacun 3 pcs', '3600', CURRENT_DATE, 14);
