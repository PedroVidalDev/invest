ALTER TABLE tb_operations
    ADD COLUMN user_id VARCHAR(255);

ALTER TABLE tb_operations
    ADD CONSTRAINT fk_operations_user
    FOREIGN KEY (user_id) REFERENCES tb_users(id);
