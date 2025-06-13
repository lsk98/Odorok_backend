--- 테스트 용

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(100),
    name VARCHAR(100),
    refresh VARCHAR(500)
);
