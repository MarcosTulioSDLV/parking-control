CREATE TABLE IF NOT EXISTS TB_PARKING_SPOT (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parking_spot_number VARCHAR(255) NOT NULL UNIQUE,
    license_plate_car VARCHAR(255) NOT NULL UNIQUE,
    brand_car VARCHAR(255) NOT NULL,
    model_car VARCHAR(255) NOT NULL,
    color_car VARCHAR(255) NOT NULL,
    registration_date TIMESTAMP NOT NULL,
    responsible_name VARCHAR(255) NOT NULL,
    block VARCHAR(255) NOT NULL,
    apartment VARCHAR(255) NOT NULL
);