CREATE TABLE IF NOT EXISTS pet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    species VARCHAR(50) NOT NULL,
    age INT,
    other_name VARCHAR(255)
);

INSERT INTO pet (name, species, age, other_name) VALUES
('Lucky', 'Dog', 13, 'Luckysinho');