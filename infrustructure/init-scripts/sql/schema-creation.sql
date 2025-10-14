CREATE USER kdg WITH PASSWORD 'kdg';
CREATE DATABASE prog6;
GRANT ALL PRIVILEGES ON DATABASE prog6 TO kdg;

\c prog6 kdg;
CREATE SCHEMA restaurant AUTHORIZATION kdg;

ALTER DEFAULT PRIVILEGES IN SCHEMA restaurant
    GRANT ALL PRIVILEGES ON TABLES TO kdg;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA restaurant TO kdg;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA restaurant TO kdg;
DROP TABLE restaurant.restaurants;
CREATE TABLE restaurant.owners (
                        owner_id UUID PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        surname VARCHAR(100) NOT NULL
);

CREATE TABLE restaurant.restaurants (
                             restaurant_id UUID PRIMARY KEY,
                             owner UUID NOT NULL,
                             name VARCHAR(200) NOT NULL,
                             contact_email VARCHAR(150) NOT NULL,
                             type_of_cuisine VARCHAR(50) NOT NULL,
                             street VARCHAR(200) NOT NULL,
                             number INT NOT NULL,
                             city VARCHAR(100) NOT NULL,
                             country VARCHAR(100) NOT NULL,
                             picture_urls TEXT,
                             default_preparation_time_in_minutes INT NOT NULL,
                             opening_hours_json TEXT,
                             price_range VARCHAR
--                              CONSTRAINT fk_owner FOREIGN KEY (owner_id) REFERENCES restaurant.owners(owner_id)
);
DROP TABLE restaurant.menus;
CREATE TABLE restaurant.menus (
                                menu_id UUID PRIMARY KEY,
                                restaurant_id UUID NOT NULL,
                                avg_price float
);
DROP TABLE restaurant.menu_dishes;
CREATE TABLE restaurant.menu_dishes (
                                        menu_id UUID REFERENCES restaurant.menus(menu_id) ON DELETE CASCADE,
                                        dish_id UUID REFERENCES restaurant.dishes(id),
                                        PRIMARY KEY (menu_id, dish_id),
                                            FOREIGN KEY (menu_id) REFERENCES restaurant.menus(menu_id),
                                            FOREIGN KEY (dish_id) REFERENCES restaurant.dishes(id)
);
DROP TABLE restaurant.dishes;
CREATE TABLE restaurant.dishes (
                                   id UUID PRIMARY KEY,
                                   restaurant_id UUID NOT NULL,
                                   name VARCHAR(255) NOT NULL,
                                   description TEXT,
                                   type VARCHAR(50) NOT NULL, -- starter, dessert, etc.
                                   price DECIMAL(10,2) NOT NULL,
                                   picture_url TEXT,
                                   state VARCHAR(50) NOT NULL, -- DRAFT, PUBLISHED, OUT_OF_STOCK, UNPUBLISHED
                                   scheduled_at TIMESTAMP NULL, -- when publish/unpublish is planned
                                   has_pending_changes BOOLEAN NOT NULL,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
DROP TABLE restaurant.food_tags;
CREATE TABLE restaurant.food_tags (
                                      id UUID PRIMARY KEY,
                                      dish_id UUID NOT NULL,
                                      name VARCHAR(100) NOT NULL,
                                      CONSTRAINT fk_foodtag_dish
                                          FOREIGN KEY (dish_id)
                                              REFERENCES restaurant.dishes (id)
                                              ON DELETE CASCADE
);
