CREATE TABLE tb_user_alerts (
    id            VARCHAR(255) PRIMARY KEY,
    user_id       VARCHAR(255),
    instrument_id VARCHAR(255),
    percent       DOUBLE PRECISION,
    value         DOUBLE PRECISION,
    active        BOOLEAN,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    CONSTRAINT fk_user_alerts_instrument
        FOREIGN KEY (instrument_id) REFERENCES tb_instruments(id)
);
