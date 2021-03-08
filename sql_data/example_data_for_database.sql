INSERT INTO location (id, latitude, longitude)
VALUES ('235524ea-76a6-11eb-9439-0242ac130002', 23.45609, 34.8288),
       ('2355272e-76a6-11eb-9439-0242ac130002', 102.72563, 234.814),
       ('2355281e-76a6-11eb-9439-0242ac130002', 12.125, 5.172654),
       ('23552b02-76a6-11eb-9439-0242ac130002', 274.28, 1736.1873),
       ('23552bca-76a6-11eb-9439-0242ac130002', 18.7, 127.4),
       ('23552c88-76a6-11eb-9439-0242ac130002', 8273.7, 1263.1763),
       ('23552d46-76a6-11eb-9439-0242ac130002', 173264.13478, 27.4),
       ('23552e04-76a6-11eb-9439-0242ac130002', 7643.98754, 4.3764);

INSERT INTO users (id, email, first_name, last_name, password, user_role, location_id)
VALUES ('b388fa0a-76a6-11eb-9439-0242ac130002', 'tom@gmail.com', 'Tom', 'Clark', 'asd', 'USER', '235524ea-76a6-11eb-9439-0242ac130002'),
       ('b388fcbc-76a6-11eb-9439-0242ac130002', 'ann@onet.pl', 'Ann', 'Smith','asd', 'USER', '2355272e-76a6-11eb-9439-0242ac130002');

INSERT INTO place_type (id, type)
VALUES (1001, 'LIBRARY'),
       (1002, 'BOOKSTORE'),
       (1003, 'MILK BAR');

INSERT INTO address (id, street, street_number, town, zip_code, location_id)
VALUES ('ed14e978-76a6-11eb-9439-0242ac130002', 'Narrow', '5/4', 'Cracow', '32-045', '2355281e-76a6-11eb-9439-0242ac130002'),
       ('ed14ebe4-76a6-11eb-9439-0242ac130002', 'Short', '23', 'Cracow', '32-052', '23552b02-76a6-11eb-9439-0242ac130002'),
       ('ed14ecca-76a6-11eb-9439-0242ac130002', 'East', '125', 'Cracow', '32-654', '23552bca-76a6-11eb-9439-0242ac130002'),
       ('ed14ed88-76a6-11eb-9439-0242ac130002', 'Cold', '5A', 'Cracow', '31-548', '23552c88-76a6-11eb-9439-0242ac130002'),
       ('ed14ee64-76a6-11eb-9439-0242ac130002', 'St. Paul', '67B/3', 'Cracow', '32-432', '23552d46-76a6-11eb-9439-0242ac130002'),
       ('ed14ef22-76a6-11eb-9439-0242ac130002', 'Sunny Square', '5', 'Cracow', '32-004', '23552e04-76a6-11eb-9439-0242ac130002');

INSERT INTO place (id, description, name, address_id)
VALUES ('f166745e-76a8-11eb-9439-0242ac130002', 'You can eat delicious dumplings with your friends', 'DumplingWorld', 'ed14e978-76a6-11eb-9439-0242ac130002'),
       ('f1667760-76a8-11eb-9439-0242ac130002', 'We have English speaking staff and delicious pancakes', 'Pancaksy', 'ed14ebe4-76a6-11eb-9439-0242ac130002'),
       ('f1667878-76a8-11eb-9439-0242ac130002', 'Read your book in silence', 'East Library', 'ed14ecca-76a6-11eb-9439-0242ac130002'),
       ('f166795e-76a8-11eb-9439-0242ac130002', 'Have problem with buying books? Come to our store!', 'BookStore', 'ed14ed88-76a6-11eb-9439-0242ac130002'),
       ('f1667a30-76a8-11eb-9439-0242ac130002', 'Cosy place for reading? Its here!', 'Cosy Books', 'ed14ee64-76a6-11eb-9439-0242ac130002'),
       ('f166afaa-76a8-11eb-9439-0242ac130002', 'Our staff will help you to buy needed books', 'BookShelf', 'ed14ef22-76a6-11eb-9439-0242ac130002');

INSERT INTO place_types_list (place_id, place_type_id)
VALUES ('f166745e-76a8-11eb-9439-0242ac130002', 1003),
       ('f1667760-76a8-11eb-9439-0242ac130002', 1003),
       ('f1667878-76a8-11eb-9439-0242ac130002', 1001),
       ('f166795e-76a8-11eb-9439-0242ac130002', 1002),
       ('f1667a30-76a8-11eb-9439-0242ac130002', 1001),
       ('f166afaa-76a8-11eb-9439-0242ac130002', 1002);

INSERT INTO favourite_place (id, place_id, user_id)
VALUES ('a1327e94-76ab-11eb-9439-0242ac130002', 'f166745e-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a1328326-76ab-11eb-9439-0242ac130002', 'f166745e-76a8-11eb-9439-0242ac130002', 'b388fcbc-76a6-11eb-9439-0242ac130002'),
       ('a1328420-76ab-11eb-9439-0242ac130002', 'f1667878-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a13284f2-76ab-11eb-9439-0242ac130002', 'f1667a30-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a13285b0-76ab-11eb-9439-0242ac130002', 'f166afaa-76a8-11eb-9439-0242ac130002', 'b388fcbc-76a6-11eb-9439-0242ac130002');
