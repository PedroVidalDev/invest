CREATE TABLE tb_user_instruments (
    id            VARCHAR(255) PRIMARY KEY,
    user_id       VARCHAR(255),
    instrument_id VARCHAR(255),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    CONSTRAINT fk_user_instruments_instrument
        FOREIGN KEY (instrument_id) REFERENCES tb_instruments(id)
);
