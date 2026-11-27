ALTER TABLE tb_user_alerts
    ADD COLUMN auto_convert_from_value DOUBLE PRECISION DEFAULT NULL,
    ADD COLUMN auto_convert_to VARCHAR(255) DEFAULT NULL;
