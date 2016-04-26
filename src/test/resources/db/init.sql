-- add testing data

INSERT INTO user(id, login, password, email, authorize) VALUES (-1, 'Admin', 'Admin', 'admin@example.com', TRUE );
INSERT INTO user(id, login, password, email, authorize) VALUES (-2, 'Aze', 'Aze', 'aze@example.com', TRUE );
INSERT INTO types(id, name) VALUES (1,'Book');
INSERT INTO types(id, name) VALUES (2,'CD');
INSERT INTO types(id, name) VALUES (3,'DVD');
INSERT INTO media(id, title, author, type) VALUES (10, 'La tour sombre, le pistolero', 'Stephen King', 1);
INSERT INTO media(id, title, author, type) VALUES (20, 'The Black Album', 'Metallica', 2);
INSERT INTO media(id, title, author, type) VALUES (30, 'Shining', 'Stephen King', 1);
INSERT INTO media(id, title, author, type) VALUES (40, 'Big Fish', 'Tim Burton', 3);

-- add testing Member
INSERT INTO member(id, firstname, lastname, dob, email, address, city) VALUES (-1, 'toto', 'le haricot', '2001-10-08', 'toto@gmail.com','route de Nantes', 'Nantes');



