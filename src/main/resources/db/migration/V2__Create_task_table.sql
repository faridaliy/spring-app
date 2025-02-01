CREATE TABLE tasks (
                       id BIGINT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       title VARCHAR(100) NOT NULL,
                       description VARCHAR(500),
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);