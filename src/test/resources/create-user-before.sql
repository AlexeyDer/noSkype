DELETE FROM user_role;
DELETE FROM user;

INSERT INTO user(id, active, password, username) VALUES
(1, true, '$2a$10$5hmRYxldck13npULWxZnSuggNCAxeQ0oJgxQjramHQiHhpUh/Gu4a', 'u'),
(2, true, '$2a$10$5hmRYxldck13npULWxZnSuggNCAxeQ0oJgxQjramHQiHhpUh/Gu4a', 'fred');

INSERT INTO user_role(user_id, roles) VALUES
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');