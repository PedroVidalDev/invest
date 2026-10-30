CREATE TYPE displayCurrencyType AS ENUM ('BRL', 'USD', 'EUR', 'GBP', 'CNY');
CREATE TYPE languageType AS ENUM ('PT_BR', 'EN_US', 'DE_DE', 'EN_GB', 'ZH_CN');
CREATE TYPE themeType AS ENUM ('LIGHT', 'DARK');

CREATE TABLE tb_userpreference (
    id                 VARCHAR(255) PRIMARY KEY,
    user_id            VARCHAR(255) UNIQUE NOT NULL REFERENCES tb_users(id),
    display_currency   displayCurrencyType,
    language           languageType,
    theme              themeType,
    created_at         TIMESTAMP,
    updated_at         TIMESTAMP
);
