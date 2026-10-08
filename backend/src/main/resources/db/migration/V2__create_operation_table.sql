CREATE TABLE tb_operations (
    id            VARCHAR(36)       NOT NULL,
    instrument_id VARCHAR(255),
    type          VARCHAR(32)       NOT NULL,
    asset_value   DOUBLE PRECISION,
    quantity      DOUBLE PRECISION,
    annual_rate   INTEGER,
    executed_at   TIMESTAMP,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    CONSTRAINT pk_operations PRIMARY KEY (id),
    CONSTRAINT chk_operations_type CHECK (type IN ('BUY', 'SELL', 'DEPOSIT', 'WITHDRAWAL'))
);