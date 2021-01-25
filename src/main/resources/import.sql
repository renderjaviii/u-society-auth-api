-- Users for testing
-- Client id: clientIdTest
-- Client secret: admin

-- Username: admin
-- Password: admin
INSERT INTO `user` (id, account_locked, created_at, email, email_verified, last_access_at, name, password, photo, username, role_id)
VALUES(1, 0, '2020-10-08', 'renderjavi1998@gmail.com', 1, NULL, 'Test', '$2a$10$urm6t9kLlyMM0KoybwMGe.7gweYL64rOMbDNyWa/TGIz6jMTTaEw2', 'https://u-society.s3.amazonaws.com/1602210192210-image', 'admin', 2);
-- Username: renderjaviii
-- Password: test
INSERT INTO `user` (id, account_locked, created_at, email, email_verified, last_access_at, name, password, photo, username, role_id)
VALUES(2, 0, '2020-10-05', 'renderjavi@gmail.com', 1, '2020-10-10 17:46:48.0', 'Member', '$2a$10$9..Eh4Ek2uXWT7jgGl45ZuMntT/9WIoqsXN7NYaQlEq886PkZorFK', 'https://u-society.s3.amazonaws.com/1602212923533-image', 'renderjaviii', 2);
-- Username: guest
-- Password: T3$t123abc
INSERT INTO `user` (id, account_locked, created_at, email, email_verified, last_access_at, name, password, photo, username, role_id)
VALUES(3, 0, '2020-10-09', 'guest@gmail.com', 1, '2020-10-10 18:13:39.0', 'Guest', '$2a$10$OaK2vHYYgubNBal7E6fF0OuEJy09mSk9/m/kIW2s2c.40CNFANpm.', NULL, 'guest', 2);
