ALTER TABLE tb_operations
    ADD CONSTRAINT fk_operations_instrument
    FOREIGN KEY (instrument_id) REFERENCES tb_instruments(id);