CREATE TYPE serviceType AS ENUM ('VERIFY', 'RECOVERY');

CREATE TABLE tb_tokens (
    id          VARCHAR(255) PRIMARY KEY,
    user_id     VARCHAR(255) NOT NULL UNIQUE,
    service     serviceType  NOT NULL,
    code        VARCHAR(6)   NOT NULL,
    attempts    INTEGER      NOT NULL DEFAULT 0,
    expires_at  TIMESTAMP    NOT NULL,
    created_at  TIMESTAMP,
    CONSTRAINT fk_tokens_user
        FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);
