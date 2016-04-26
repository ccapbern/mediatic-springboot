-- add testing data
INSERT INTO user(id, login, password, email, authorize) VALUES (1, 'Admin', 'Admin', 'admin@example.com', TRUE );
INSERT INTO user(id, login, password, email, authorize) VALUES (2, 'Aze', 'Aze', 'aze@example.com', TRUE );

-- add testing Member
INSERT INTO member(id, firstname, lastname, dob, email, address, city) VALUES (1, 'toto', 'le haricot', '2001-10-08', 'toto@gmail.com','route de Nantes', 'Nantes');