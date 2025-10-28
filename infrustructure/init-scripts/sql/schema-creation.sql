
-- ============================================
-- CREATE SCRIPT (Complete & Fixed)
-- ============================================

-- Create user and database (only if needed)
-- CREATE USER kdg WITH PASSWORD 'kdg';
-- CREATE DATABASE prog6;
-- GRANT ALL PRIVILEGES ON DATABASE prog6 TO kdg;
-- \c prog6 kdg;

-- ==========================================
-- ORDERS SCHEMA
-- ==========================================

CREATE SCHEMA IF NOT EXISTS orders AUTHORIZATION kdg;

ALTER DEFAULT PRIVILEGES IN SCHEMA orders
    GRANT ALL PRIVILEGES ON TABLES TO kdg;

-- Restaurant projections (read model)
CREATE TABLE orders.restaurant_projection (
                                              restaurant_id UUID PRIMARY KEY,
                                              owner_id UUID NOT NULL,
                                              name VARCHAR(255) NOT NULL,
                                              street VARCHAR(255) NOT NULL,
                                              number INT NOT NULL,
                                              city VARCHAR(255) NOT NULL,
                                              postal_code VARCHAR(100) NOT NULL,
                                              country VARCHAR(255) NOT NULL,
                                              picture_urls TEXT,
                                              opening_hours_json TEXT,
                                              type_of_cuisine VARCHAR(100),
                                              contact_email VARCHAR(255),
                                              default_preparation_time_in_minutes INT,
                                              price_range VARCHAR(50)
);

-- Menu projections
CREATE TABLE orders.menu_projections (
                                         menu_id UUID PRIMARY KEY,
                                         restaurant_id UUID NOT NULL,
                                         avg_price NUMERIC
);

-- Dish projections
CREATE TABLE orders.dish_projections (
                                         menu_id UUID NOT NULL,
                                         dish_id UUID NOT NULL,
                                         restaurant_id UUID NOT NULL,
                                         name VARCHAR(255),
                                         type VARCHAR(100),
                                         description TEXT,
                                         price NUMERIC,
                                         picture_url VARCHAR(500),
                                         status VARCHAR(50),
                                         PRIMARY KEY (menu_id, dish_id),
                                         CONSTRAINT fk_menu
                                             FOREIGN KEY (menu_id)
                                                 REFERENCES orders.menu_projections(menu_id)
                                                 ON DELETE CASCADE
);

-- Food tags for dishes
CREATE TABLE orders.food_tags (
                                  id UUID PRIMARY KEY,
                                  name VARCHAR(100) NOT NULL,
                                  menu_id UUID NOT NULL,
                                  dish_id UUID NOT NULL,
                                  CONSTRAINT fk_dish
                                      FOREIGN KEY (menu_id, dish_id)
                                          REFERENCES orders.dish_projections(menu_id, dish_id)
                                          ON DELETE CASCADE
);

-- Baskets (shopping carts)
CREATE TABLE orders.baskets (
                                basket_id UUID PRIMARY KEY,
                                restaurant_id UUID NOT NULL,
                                total_price NUMERIC(12,2),
                                status VARCHAR(50),
                                message TEXT
);

-- Basket items
CREATE TABLE orders.basket_items (
                                     basket_id UUID NOT NULL,
                                     dish_id UUID NOT NULL,
                                     menu_id UUID NOT NULL,
                                     price_at_add NUMERIC(12,2) NOT NULL,
                                     quantity INTEGER NOT NULL,
                                     PRIMARY KEY (basket_id, dish_id),
                                     CONSTRAINT fk_basket
                                         FOREIGN KEY (basket_id)
                                             REFERENCES orders.baskets(basket_id)
                                             ON DELETE CASCADE
);

-- Orders (placed orders with customer snapshot)
CREATE TABLE orders.orders (
                               order_id UUID PRIMARY KEY,
                               basket_id UUID NOT NULL,
                               restaurant_id UUID NOT NULL,
                               message TEXT,
                               total_price NUMERIC(12,2),
                               status VARCHAR(50) DEFAULT 'PENDING',
                               created_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
    -- Customer information (snapshot)
                               customer_first_name VARCHAR(200),
                               customer_last_name VARCHAR(200),
                               customer_email VARCHAR(255),
                               delivery_street VARCHAR(255),
                               delivery_number INTEGER,
                               delivery_postal_code VARCHAR(50),
                               delivery_city VARCHAR(200),
                               delivery_country VARCHAR(200),
                               rejection_reason TEXT
);

-- Order items (snapshot of basket items at order time)
CREATE TABLE orders.order_items (
                                    order_item_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                    order_id UUID NOT NULL,
                                    menu_id UUID NOT NULL,
                                    dish_id UUID NOT NULL,
                                    price_at_order NUMERIC(12,2) NOT NULL,
                                    quantity INTEGER NOT NULL,
                                    CONSTRAINT fk_order
                                        FOREIGN KEY (order_id)
                                            REFERENCES orders.orders(order_id)
                                            ON DELETE CASCADE
);

CREATE TABLE orders.payment_states (
                                       payment_id UUID PRIMARY KEY,
                                       order_id UUID NOT NULL,
                                       stripe_session_id VARCHAR(255) NOT NULL UNIQUE,
                                       amount DECIMAL(12, 2) NOT NULL,
                                       status VARCHAR(50) NOT NULL,
                                       created_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_payment_order_id ON orders.payment_states(order_id);
CREATE INDEX idx_payment_session_id ON orders.payment_states(stripe_session_id);

-- Indexes for performance
CREATE INDEX idx_baskets_restaurant ON orders.baskets(restaurant_id);
CREATE INDEX idx_basket_items_basket ON orders.basket_items(basket_id);
CREATE INDEX idx_orders_restaurant ON orders.orders(restaurant_id);
CREATE INDEX idx_orders_status ON orders.orders(status);
CREATE INDEX idx_order_items_order ON orders.order_items(order_id);


-- ==========================================
-- RESTAURANT SCHEMA
-- ==========================================

CREATE SCHEMA IF NOT EXISTS restaurant AUTHORIZATION kdg;

ALTER DEFAULT PRIVILEGES IN SCHEMA restaurant
    GRANT ALL PRIVILEGES ON TABLES TO kdg;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA restaurant TO kdg;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA restaurant TO kdg;

-- Restaurants (aggregate root)
CREATE TABLE restaurant.restaurants (
                                        restaurant_id UUID PRIMARY KEY,
                                        owner UUID NOT NULL,
                                        name VARCHAR(200) NOT NULL,
                                        contact_email VARCHAR(150) NOT NULL,
                                        type_of_cuisine VARCHAR(50) NOT NULL,
                                        street VARCHAR(200) NOT NULL,
                                        number INT NOT NULL,
                                        city VARCHAR(100) NOT NULL,
                                        postal_code VARCHAR(100) NOT NULL,
                                        country VARCHAR(100) NOT NULL,
                                        picture_urls TEXT,
                                        default_preparation_time_in_minutes INT NOT NULL,
                                        opening_hours_json TEXT,
                                        price_range VARCHAR(50)
);

-- Menus
CREATE TABLE restaurant.menus (
                                  menu_id UUID PRIMARY KEY,
                                  restaurant_id UUID NOT NULL,
                                  avg_price NUMERIC,
                                  CONSTRAINT fk_restaurant
                                      FOREIGN KEY (restaurant_id)
                                          REFERENCES restaurant.restaurants(restaurant_id)
                                          ON DELETE CASCADE
);

-- Dishes
CREATE TABLE restaurant.dishes (
                                   id UUID PRIMARY KEY,
                                   restaurant_id UUID NOT NULL,
                                   name VARCHAR(255) NOT NULL,
                                   description TEXT,
                                   type VARCHAR(50) NOT NULL,
                                   price DECIMAL(10,2) NOT NULL,
                                   picture_url TEXT,
                                   state VARCHAR(50) NOT NULL,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   CONSTRAINT fk_restaurant_dish
                                       FOREIGN KEY (restaurant_id)
                                           REFERENCES restaurant.restaurants(restaurant_id)
                                           ON DELETE CASCADE
);

-- Menu-Dish association (many-to-many)
CREATE TABLE restaurant.menu_dishes (
                                        menu_id UUID NOT NULL,
                                        dish_id UUID NOT NULL,
                                        PRIMARY KEY (menu_id, dish_id),
                                        CONSTRAINT fk_menu
                                            FOREIGN KEY (menu_id)
                                                REFERENCES restaurant.menus(menu_id)
                                                ON DELETE CASCADE,
                                        CONSTRAINT fk_dish
                                            FOREIGN KEY (dish_id)
                                                REFERENCES restaurant.dishes(id)
                                                ON DELETE CASCADE
);

-- Food tags for dishes
CREATE TABLE restaurant.food_tags (
                                      id UUID PRIMARY KEY,
                                      dish_id UUID NOT NULL,
                                      name VARCHAR(100) NOT NULL,
                                      CONSTRAINT fk_foodtag_dish
                                          FOREIGN KEY (dish_id)
                                              REFERENCES restaurant.dishes(id)
                                              ON DELETE CASCADE
);

-- Scheduled dish changes
CREATE TABLE restaurant.scheduled_dish_changes (
                                                   id UUID PRIMARY KEY,
                                                   action VARCHAR(50) NOT NULL,
                                                   scheduled_at TIMESTAMP NOT NULL,
                                                   executed BOOLEAN NOT NULL DEFAULT FALSE,
                                                   executed_at TIMESTAMP NULL
);

-- Dishes linked to scheduled changes
CREATE TABLE restaurant.scheduled_dish_change_dishes (
                                                         schedule_id UUID NOT NULL,
                                                         dish_id UUID NOT NULL,
                                                         PRIMARY KEY (schedule_id, dish_id),
                                                         CONSTRAINT fk_schedule
                                                             FOREIGN KEY (schedule_id)
                                                                 REFERENCES restaurant.scheduled_dish_changes(id)
                                                                 ON DELETE CASCADE,
                                                         CONSTRAINT fk_dish_schedule
                                                             FOREIGN KEY (dish_id)
                                                                 REFERENCES restaurant.dishes(id)
                                                                 ON DELETE CASCADE
);

-- Restaurant orders (restaurant's view of orders)
CREATE TABLE restaurant.restaurant_orders (
                                              order_id UUID PRIMARY KEY,
                                              restaurant_id UUID NOT NULL,
                                              customer_name VARCHAR(255) NOT NULL,
                                              delivery_street VARCHAR(200) NOT NULL,
                                              delivery_number INT NOT NULL,
                                              delivery_city VARCHAR(100) NOT NULL,
                                              delivery_postal_code VARCHAR(100) NOT NULL,
                                              delivery_country VARCHAR(100) NOT NULL,
                                              total_price NUMERIC(12,2) NOT NULL,
                                              status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
                                              rejection_reason TEXT,
                                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                              CONSTRAINT fk_restaurant_order
                                                  FOREIGN KEY (restaurant_id)
                                                      REFERENCES restaurant.restaurants(restaurant_id)
                                                      ON DELETE CASCADE
);

-- Restaurant order items
CREATE TABLE restaurant.restaurant_order_items (
                                                   order_item_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                                                   order_id UUID NOT NULL,
                                                   dish_id UUID NOT NULL,
                                                   dish_name VARCHAR(255) NOT NULL,
                                                   quantity INTEGER NOT NULL,
                                                   price_at_order NUMERIC(12,2) NOT NULL,
                                                   CONSTRAINT fk_restaurant_order_item
                                                       FOREIGN KEY (order_id)
                                                           REFERENCES restaurant.restaurant_orders(order_id)
                                                           ON DELETE CASCADE,
                                                   CONSTRAINT fk_dish_order_item
                                                       FOREIGN KEY (dish_id)
                                                           REFERENCES restaurant.dishes(id)
                                                           ON DELETE RESTRICT
);

-- Indexes for performance
CREATE INDEX idx_restaurant_orders_restaurant ON restaurant.restaurant_orders(restaurant_id);
CREATE INDEX idx_restaurant_orders_status ON restaurant.restaurant_orders(status);
CREATE INDEX idx_restaurant_order_items_order ON restaurant.restaurant_order_items(order_id);
CREATE INDEX idx_menu_dishes_menu ON restaurant.menu_dishes(menu_id);
CREATE INDEX idx_menu_dishes_dish ON restaurant.menu_dishes(dish_id);


-- ==========================================
-- GRANT PERMISSIONS
-- ==========================================

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA orders TO kdg;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA orders TO kdg;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA restaurant TO kdg;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA restaurant TO kdg;