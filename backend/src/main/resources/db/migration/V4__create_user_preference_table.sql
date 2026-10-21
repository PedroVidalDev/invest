CREATE TYPE languageType AS ENUM ('PTBR', 'ENUS');
CREATE TYPE themeType AS ENUM ('LIGHT', 'DARK');

CREATE TABLE tb_userpreference (
    id                 VARCHAR(255) PRIMARY KEY,
    user_id            VARCHAR(255),
    cash_instrument_id VARCHAR(255),
    display_currency   VARCHAR(255),
    language           languageType,
    theme              themeType,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP
);
