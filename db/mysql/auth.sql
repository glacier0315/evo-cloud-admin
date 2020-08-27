CREATE DATABASE eboot_auth CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';

CREATE USER 'eboot_auth'@'%'  IDENTIFIED BY 'eboot_auth';

grant all privileges on eboot_auth.* to 'eboot_auth'@'%';

flush privileges;

use eboot_auth;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details  (
  client_id varchar(256) NOT NULL,
  resource_ids varchar(256),
  client_secret varchar(256),
  scope varchar(256),
  authorized_grant_types varchar(256),
  web_server_redirect_uri varchar(256),
  authorities varchar(256),
  access_token_validity int(11),
  refresh_token_validity int(11),
  additional_information varchar(4096),
  autoapprove varchar(256),
  PRIMARY KEY (client_id) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权客户端' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('app', 'rid', '$2a$10$GOzKSM9MR0OQMl32hiYp9uNUxnfk4u1dSnHwKrcWnNiJyn.5apB1G', 'all', 'authorization_code,refresh_token,password', 'https://www.getpostman.com/oauth2/callback', null, 3600, 3600, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('auth-client', null, '$2a$10$oK1FopDCVsaG76.p8YFRcuuSbqsKbayv9v7NlViAjJaK3HlU1LT16', 'all', 'authorization_code,client_credentials,refresh_token,password', 'http://192.168.10.1:8530/login/oauth2/code/uas', null, 3600, 3600, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('eboot-sys', null, '$2a$10$pq1Jjw6cM.Rj3ZZhBOhvMOTEiTxWOuizkXn7RDbb9r2ik1isBK20S', 'all', 'authorization_code,client_credentials,refresh_token,password', 'http://localhost:8081/login/oauth2/code/uas', null, 3600, 36000, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('order-client', null, '$2a$10$q7R4ap1/Qi3sXp9VKOKpzORD4DWAA2ol9lgg1XsO37cI5TIONTPhK', 'all', 'authorization_code,refresh_token,password', 'http://192.168.10.1:8530', null, 3600, 36000, null, '1');
INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('user-client', null, '$2a$10$v6.UycDNQSzZtvV9jjWQo.JTepd4d4j2fVxSK8Qyj7QVQqMrp8wWu', 'all', 'authorization_code,refresh_token,password', 'http://192.168.10.1:8530', null, 3600, 36000, null, '1');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code  (
  code varchar(255),
  authentication blob NULL,
  INDEX code_index(code) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '授权码' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id varchar(255) DEFAULT NULL,
  token blob,
  authentication_id varchar(255) DEFAULT NULL,
  user_name varchar(255) DEFAULT NULL,
  client_id varchar(255) DEFAULT NULL,
  authentication blob,
  refresh_token varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE = utf8_general_ci COMMENT = '令牌' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id varchar(255) DEFAULT NULL,
  token blob,
  authentication blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE = utf8_general_ci COMMENT = '刷新令牌' ROW_FORMAT = Dynamic;
