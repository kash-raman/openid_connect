--------------- POSTGRES ---------------
--
-- drop table if exists oauth_client_details;
-- create table oauth_client_details (
--   client_id VARCHAR(255) PRIMARY KEY,
--   resource_ids VARCHAR(255),
--   client_secret VARCHAR(255),
--   scope VARCHAR(255),
--   authorized_grant_types VARCHAR(255),
--   web_server_redirect_uri VARCHAR(255),
--   authorities VARCHAR(255),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additional_information VARCHAR(4096),
--   autoapprove VARCHAR(255)
-- );

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication BYTEA
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
--
-- create table if not exists ClientDetails (
--   appId VARCHAR(255) PRIMARY KEY,
--   resourceIds VARCHAR(255),
--   appSecret VARCHAR(255),
--   scope VARCHAR(255),
--   grantTypes VARCHAR(255),
--   redirectUrl VARCHAR(255),
--   authorities VARCHAR(255),
--   access_token_validity INTEGER,
--   refresh_token_validity INTEGER,
--   additionalInformation VARCHAR(4096),
--   autoApproveScopes VARCHAR(255)
-- );


--------------- MySQL ---------------
--drop table if exists oauth_client_details;
--create table oauth_client_details (
--  client_id VARCHAR(255) PRIMARY KEY,
--  resource_ids VARCHAR(255),
--  client_secret VARCHAR(255),
--  scope VARCHAR(255),
--  authorized_grant_types VARCHAR(255),
--  web_server_redirect_uri VARCHAR(255),
--  authorities VARCHAR(255),
--  access_token_validity INTEGER,
--  refresh_token_validity INTEGER,
--  additional_information VARCHAR(4096),
--  autoapprove VARCHAR(255)
--);
--
--create table if not exists oauth_client_token (
--  token_id VARCHAR(255),
--  token LONG VARBINARY,
--  authentication_id VARCHAR(255) PRIMARY KEY,
--  user_name VARCHAR(255),
--  client_id VARCHAR(255)
--);
--
--create table if not exists oauth_access_token (
--  token_id VARCHAR(255),
--  token LONG VARBINARY,
--  authentication_id VARCHAR(255) PRIMARY KEY,
--  user_name VARCHAR(255),
--  client_id VARCHAR(255),
--  authentication LONG VARBINARY,
--  refresh_token VARCHAR(255)
--);
--
--create table if not exists oauth_refresh_token (
--  token_id VARCHAR(255),
--  token LONG VARBINARY,
--  authentication LONG VARBINARY
--);
--
--create table if not exists oauth_code (
--  code VARCHAR(255), authentication LONG VARBINARY
--);
--
--create table if not exists oauth_approvals (
--	userId VARCHAR(255),
--	clientId VARCHAR(255),
--	scope VARCHAR(255),
--	status VARCHAR(10),
--	expiresAt TIMESTAMP,
--	lastModifiedAt TIMESTAMP
--);
--
--create table if not exists ClientDetails (
--  appId VARCHAR(255) PRIMARY KEY,
--  resourceIds VARCHAR(255),
--  appSecret VARCHAR(255),
--  scope VARCHAR(255),
--  grantTypes VARCHAR(255),
--  redirectUrl VARCHAR(255),
--  authorities VARCHAR(255),
--  access_token_validity INTEGER,
--  refresh_token_validity INTEGER,
--  additionalInformation VARCHAR(4096),
--  autoApproveScopes VARCHAR(255)
--);

create table if not exists user_login
(
    user_id      integer not null
        constraint user_login_pkey
            primary key,
    address      varchar(255),
    email        varchar(255),
    first_name   varchar(255),
    last_name    varchar(255),
    locked       boolean not null,
    pass_phrase  varchar(255),
    password     varchar(255),
    phone_number varchar(255),
    user_name    varchar(255)
        constraint uk_c9gphki9awsu7q9761s47312s
            unique
);

alter table user_login
    owner to postgres;

