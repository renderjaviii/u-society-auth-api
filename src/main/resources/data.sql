INSERT INTO authenticationConfig(name, value) VALUES ('config.access-token.signing-key', '123');
INSERT INTO authenticationConfig(name, value) VALUES ('config.access-token.validity-seconds', '43200');
INSERT INTO authenticationConfig(name, value) VALUES ('config.grant.types', 'password,client_credentials,refresh_token');
INSERT INTO authenticationConfig(name, value) VALUES ('config.refresh-token.validity-seconds', '86400');
INSERT INTO authenticationConfig(name, value) VALUES ('config.scope', 'WEB');

-- INSERT INTO `role`(id, description, name) VALUES (1, 'Super user.', 'ROLE_ADMIN');
-- INSERT INTO `role`(id, description, name) VALUES (2, 'Standard user.', 'ROLE_BASIC');
--
-- INSERT INTO privilege(id, description, name) VALUES (1, 'User administrator privilege.', 'ADMIN_PRIVILEGE');
-- INSERT INTO privilege(id, description, name) VALUES (2, 'Standard user privilege.', 'BASIC_PRIVILEGE');
--
-- INSERT INTO role_privilege(role_id, privilege_id) VALUES (1, 1);
-- INSERT INTO role_privilege(role_id, privilege_id) VALUES (2, 2);

-- Credential for testing -> clientId: clientIdTest, clientSecret: admin
-- INSERT INTO credential(id, client_id, client_secret, created_at, credentials_expired, description, grant_type, `scope`) VALUES (1, 'clientIdTest', '$2a$10$Pz5yIaKO/JwGQ0pio1XLs.xDPrPv95SO0F2A3BCKV9USuD1xqUlZ.', '2020-04-10', 0, "Credential for testing.", 'password,client_credentials,refresh_token', 'WEB');

-- Users for testing -> username: admin, password: ADM1n3$t123 & username: test, password: T3$t123abc
-- INSERT INTO `user` (account_locked, birth_date, created_at, document_number, email, email_verified, first_name, gender, last_access_at, last_name, password, phone_number, username, role_id) VALUES(0, '2020-05-23', '2020-05-23', '0000000000', 'admin@domain.co', 1, 'super', 'M', '2020-05-31 19:46:49.0', 'admin', '$2a$10$C4TpyN8Z2ea7znpYiWuSuu5rEUPAhfOcY9vCAJTLYH3u6ieuXTVG2', '00000000000', 'admin', 1);
-- INSERT INTO `user` (account_locked, birth_date, created_at, document_number, email, email_verified, first_name, gender, last_access_at, last_name, password, phone_number, username, role_id) VALUES(0, '2020-05-29', '2020-05-30', '0000000001', 'test@domain.co', 1, 'user', 'F', NULL, 'test', '$2a$10$1qcRVn5teIZ1IvtAvsfgsOyiv1xd08FbJxb2.WLlQrRgEbRpLfWka', '00000000001', 'test', 2);
