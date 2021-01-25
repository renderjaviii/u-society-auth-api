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