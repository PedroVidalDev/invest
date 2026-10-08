CREATE TYPE instrumentType AS ENUM ('CRYPTO', 'STOCK', 'FIXED_INCOME');
CREATE TYPE indexerType AS ENUM ('SELIC', 'IPCA');

CREATE TABLE tb_instruments (
    id            VARCHAR(255) PRIMARY KEY,
    type          instrumentType,
    symbol        VARCHAR(255),
    name          VARCHAR(255),
    indexer       indexerType,
    maturity_date TIMESTAMP,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP 
);