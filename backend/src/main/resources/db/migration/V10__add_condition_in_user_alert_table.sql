CREATE TYPE alertCondition AS ENUM ('ABOVE', 'BELOW');

ALTER TABLE tb_user_alerts
    ADD COLUMN condition alertCondition DEFAULT NULL;
