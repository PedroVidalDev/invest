CREATE TYPE categoryType AS ENUM ('USER', 'ADMIN');

CREATE TABLE tb_users (
    id          VARCHAR(255) PRIMARY KEY,
    name        VARCHAR(255),
    email       VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255),
    photo       VARCHAR(255),
    active      BOOLEAN,
    category    categoryType,
    verified_at TIMESTAMP,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP
);
