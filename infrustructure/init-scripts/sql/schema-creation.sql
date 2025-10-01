CREATE USER kdg WITH PASSWORD 'kdg';
CREATE DATABASE prog6;
GRANT ALL PRIVILEGES ON DATABASE prog6 TO kdg;

\c prog6 kdg;
CREATE SCHEMA restaurant AUTHORIZATION kdg;

ALTER DEFAULT PRIVILEGES IN SCHEMA restaurant
    GRANT ALL PRIVILEGES ON TABLES TO kdg;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA restaurant TO kdg;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA restaurant TO kdg;

CREATE TABLE restaurant.owners (
                        owner_id UUID PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        surname VARCHAR(100) NOT NULL
);

CREATE TABLE restaurant.restaurants (
                             restaurant_id UUID PRIMARY KEY,
                             owner_id UUID NOT NULL,
                             name VARCHAR(200) NOT NULL,
                             contact_email VARCHAR(150) NOT NULL,
                             type_of_cuisine VARCHAR(50) NOT NULL,
                             street VARCHAR(200) NOT NULL,
                             number INT NOT NULL,
                             city VARCHAR(100) NOT NULL,
                             country VARCHAR(100) NOT NULL,
                             picture_urls TEXT,
                             default_preparation_time_in_minutes INT NOT NULL,
                             CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES restaurant.owners(owner_id)
);