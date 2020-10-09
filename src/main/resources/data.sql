INSERT INTO authenticationConfig(name, value) VALUES ('config.access-token.signing-key', '123');
INSERT INTO authenticationConfig(name, value) VALUES ('config.access-token.validity-seconds', '43200');
INSERT INTO authenticationConfig(name, value) VALUES ('config.grant.types', 'password,client_credentials,refresh_token');
INSERT INTO authenticationConfig(name, value) VALUES ('config.refresh-token.validity-seconds', '86400');
INSERT INTO authenticationConfig(name, value) VALUES ('config.scope', 'WEB');

INSERT INTO `role`(id, description, name) VALUES (1, 'Super user.', 'ROLE_ADMIN');
INSERT INTO `role`(id, description, name) VALUES (2, 'Standard user.', 'ROLE_BASIC');
INSERT INTO privilege(id, description, name) VALUES (1, 'User administrator privilege.', 'ADMIN_PRIVILEGE');
INSERT INTO privilege(id, description, name) VALUES (2, 'Standard user privilege.', 'BASIC_PRIVILEGE');
INSERT INTO role_privilege(role_id, privilege_id) VALUES (1, 1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2, 2);

-- Credential for testing -> clientId: clientIdTest, clientSecret: admin
INSERT INTO credential(id, client_id, client_secret, created_at, credentials_expired, description, grant_type, `scope`) VALUES (1, 'clientIdTest', '$2a$10$Pz5yIaKO/JwGQ0pio1XLs.xDPrPv95SO0F2A3BCKV9USuD1xqUlZ.', '2020-04-10', 0, "Credential for testing.", 'password,client_credentials,refresh_token', 'WEB');

-- User for testing
-- Username: admin
-- Password: admin
-- Client id: clientIdTest
-- Client secret: admin
INSERT INTO `user` (id, account_locked, created_at, email, email_verified, last_access_at, name, password, photo, username, role_id)
VALUES(1, 0, '2020-10-08', 'renderjavi1998@gmail.com', 1, NULL, 'Test', '$2a$10$urm6t9kLlyMM0KoybwMGe.7gweYL64rOMbDNyWa/TGIz6jMTTaEw2', 'https://u-society.s3.amazonaws.com/1602210192210-image', 'admin', 2);