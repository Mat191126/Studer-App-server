INSERT INTO location (id, active, latitude, longitude)
VALUES ('235524ea-76a6-11eb-9439-0242ac130002', true, 50.071522, 19.943859),
       ('2355272e-76a6-11eb-9439-0242ac130002', true, 50.043740, 19.951120),
       ('2355281e-76a6-11eb-9439-0242ac130002', true, 50.0670, 19.9294),
       ('23552b02-76a6-11eb-9439-0242ac130002', true, 50.0647, 19.9381),
       ('23552bca-76a6-11eb-9439-0242ac130002', true, 50.0584, 50.0584),
       ('23552c88-76a6-11eb-9439-0242ac130002', true, 50.0599, 19.9107),
       ('23552d46-76a6-11eb-9439-0242ac130002', true, 50.0418, 19.9495),
       ('23552e04-76a6-11eb-9439-0242ac130002', true, 50.0495, 19.9169),
       ('23552c54-76a6-11eb-9439-0242ac130002', true, 50.0528, 19.9439),
       ('23552c27-76a6-11eb-9439-0242ac130002', true, 50.0603, 19.9378),
       ('23552c28-76a6-11eb-9439-0242ac130002', true, 50.0633, 19.9370),
       ('4734c0e0-825f-11eb-8dcd-0242ac130003', true, 50.0395, 19.9412),
       ('4734c310-825f-11eb-8dcd-0242ac130003', true, 50.0203, 19.9737);

INSERT INTO users (id, active, email, first_name, last_name, password, user_role, location_id)
VALUES ('b388fa0a-76a6-11eb-9439-0242ac130002', true, 'tom@gmail.com', 'Tom', 'Clark', 'asd', 'USER', '4734c0e0-825f-11eb-8dcd-0242ac130003'),
       ('b388fcbc-76a6-11eb-9439-0242ac130002', true, 'ann@onet.pl', 'Ann', 'Smith','asd', 'USER', '4734c310-825f-11eb-8dcd-0242ac130003');

INSERT INTO place_type (id, active, type, name)
VALUES (1001, true, 'LIBRARIES', 'Libraries'),
       (1002, true, 'QUITE_SPACES', 'Quite spaces'),
       (1003, true, 'COPY_PLACES', 'Copy places'),
       (1004, true, 'INEXPENSIVE_RESTAURANTS', 'Inexpensive restaurants'),
       (1005, true, 'COFFEE_SHOPS', 'Coffee shops'),
       (1006, true, 'STUDENT_PUBS', 'Student pubs'),
       (1007, true, 'MOVIE_THEATERS', 'Movie theaters'),
       (1008, true, 'BOOKSTORES', 'Bookstores'),
       (1009, true, 'DRUGSTORES', 'Drugstores'),
       (1010, true, 'PARKS_GREEN_SPACES', 'Parks and green spaces'),
       (1011, true, 'PSYCHOTHERAPISTS', 'Psychotherapists');

INSERT INTO address (id, active, street, street_number, town, zip_code, location_id)
VALUES ('ed14e978-76a6-11eb-9439-0242ac130002', true, 'Warszawska', '24', 'Kraków', '31-155', '235524ea-76a6-11eb-9439-0242ac130002'),
       ('ed14ebe4-76a6-11eb-9439-0242ac130002', true, 'Węgierska', '1', 'Kraków', '30-535', '2355272e-76a6-11eb-9439-0242ac130002'),
       ('ed14ecca-76a6-11eb-9439-0242ac130002', true, 'Stefana Batorego', '25', 'Kraków', '31-135', '2355281e-76a6-11eb-9439-0242ac130002'),
       ('ed14ed88-76a6-11eb-9439-0242ac130002', true, 'Sławkowska', '24A', 'Kraków', '31-014', '23552b02-76a6-11eb-9439-0242ac130002'),
       ('ed14ee64-76a6-11eb-9439-0242ac130002', true, 'Felicjanek', '4', 'Kraków', '31-104', '23552bca-76a6-11eb-9439-0242ac130002'),
       ('ed14ef22-76a6-11eb-9439-0242ac130002', true, '-', '-', 'Kraków', '33-332', '23552c88-76a6-11eb-9439-0242ac130002'),
       ('ed14ef14-76a6-11eb-9439-0242ac130002', true, 'Jana Zamoyskiego', '-', 'Kraków', '33-332', '23552d46-76a6-11eb-9439-0242ac130002'),
       ('ed14ef34-76a6-11eb-9439-0242ac130002', true, 'Praska', '-', 'Kraków', '33-332', '23552e04-76a6-11eb-9439-0242ac130002'),
       ('5becce28-8260-11eb-8dcd-0242ac130003', true, 'Miodowa', '20/3', 'Kraków', '31-055', '23552c54-76a6-11eb-9439-0242ac130002'),
       ('5becd080-8260-11eb-8dcd-0242ac130003', true, 'Rynek Główny', '13', 'Kraków', '31-042', '23552c27-76a6-11eb-9439-0242ac130002'),
       ('5becd418-8260-11eb-8dcd-0242ac130003', true, 'Sławkowska', '4', 'Kraków', '31-014','23552c28-76a6-11eb-9439-0242ac130002');

INSERT INTO place (id, active, description, name, address_id)
VALUES ('f166745e-76a8-11eb-9439-0242ac130002', true, 'You can copy everything you want!', 'KSERO Kraków 6gr - KAMPUS PK', 'ed14e978-76a6-11eb-9439-0242ac130002'),
       ('f1667760-76a8-11eb-9439-0242ac130002', true, 'We have English speaking staff', 'Ksero Serwis Kraków Drukarnia Druk Skan Wielkoformatowy', 'ed14ebe4-76a6-11eb-9439-0242ac130002'),
       ('f1667878-76a8-11eb-9439-0242ac130002', true, 'Copy and not only', 'Ksero i druk ATLANTYDA', 'ed14ecca-76a6-11eb-9439-0242ac130002'),
       ('f166795e-76a8-11eb-9439-0242ac130002', true, 'Have problem with buying English books? Come to our store!', 'American Bookstore Księgarnia Amerykańska', 'ed14ed88-76a6-11eb-9439-0242ac130002'),
       ('f1667a30-76a8-11eb-9439-0242ac130002', true, 'the best place to buy English books', 'Massolit Books', 'ed14ee64-76a6-11eb-9439-0242ac130002'),
       ('f166afaa-76a8-11eb-9439-0242ac130002', true, 'Big area with grass', 'Błonia', 'ed14ef22-76a6-11eb-9439-0242ac130002'),
       ('f166afab-76a8-11eb-9439-0242ac130002', true, 'Nice park with playground', 'Park Bednarskiego', 'ed14ef14-76a6-11eb-9439-0242ac130002'),
       ('f166afdb-76a8-11eb-9439-0242ac130002', true, 'Green park in city centre', 'Park Dębnicki', 'ed14ef34-76a6-11eb-9439-0242ac130002'),
       ('9498c812-8260-11eb-8dcd-0242ac130003', true, 'You can listen to ska, reggae, punku i rockabilly', 'PUB Propaganda', '5becce28-8260-11eb-8dcd-0242ac130003'),
       ('f16677ef-76a8-11eb-9439-0242ac130002', true, 'Music club', 'Louis Music Club & Pub', '5becd080-8260-11eb-8dcd-0242ac130003'),
       ('b45c41ce-8260-11eb-8dcd-0242ac130003', true, 'Pub with great atmosphere', 'Free Pub', '5becd418-8260-11eb-8dcd-0242ac130003');

INSERT INTO place_types_list (place_id, place_type_id)
VALUES ('f166745e-76a8-11eb-9439-0242ac130002', 1003),
       ('f1667760-76a8-11eb-9439-0242ac130002', 1003),
       ('f1667878-76a8-11eb-9439-0242ac130002', 1003),
       ('f166795e-76a8-11eb-9439-0242ac130002', 1008),
       ('f1667a30-76a8-11eb-9439-0242ac130002', 1008),
       ('f1667a30-76a8-11eb-9439-0242ac130002', 1005),
       ('f166afaa-76a8-11eb-9439-0242ac130002', 1010),
       ('f166afab-76a8-11eb-9439-0242ac130002', 1010),
       ('f166afdb-76a8-11eb-9439-0242ac130002', 1010),
       ('9498c812-8260-11eb-8dcd-0242ac130003', 1006),
       ('f16677ef-76a8-11eb-9439-0242ac130002', 1006),
       ('b45c41ce-8260-11eb-8dcd-0242ac130003', 1006);

INSERT INTO favourite_place (id, active, place_id, user_id)
VALUES ('a1327e94-76ab-11eb-9439-0242ac130002', true, 'f166745e-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a1328326-76ab-11eb-9439-0242ac130002', true, 'f166745e-76a8-11eb-9439-0242ac130002', 'b388fcbc-76a6-11eb-9439-0242ac130002'),
       ('a1328420-76ab-11eb-9439-0242ac130002', true, 'f1667878-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a13284f2-76ab-11eb-9439-0242ac130002', true, 'f1667a30-76a8-11eb-9439-0242ac130002', 'b388fa0a-76a6-11eb-9439-0242ac130002'),
       ('a13285b0-76ab-11eb-9439-0242ac130002', true, 'f166afaa-76a8-11eb-9439-0242ac130002', 'b388fcbc-76a6-11eb-9439-0242ac130002');
