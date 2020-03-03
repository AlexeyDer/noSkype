delete from user_role;
delete from user;

INSERT INTO user(id, username, password, active) VALUES
(1, 'mas', '$2a$10$4gX0Q1QlxwPTJXmmthfNsuNRNWpbgF3pHDq3pxLGrtWi7iHJqDATe', true),
(2, 'carl', '$2a$10$4gX0Q1QlxwPTJXmmthfNsuNRNWpbgF3pHDq3pxLGrtWi7iHJqDATe', true);

INSERT INTO user_role(user_id, roles) VALUES
(1, 'USER'), (1, 'ADMIN'),
(2, 'USER');