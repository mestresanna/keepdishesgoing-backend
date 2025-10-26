-- ============================================
-- DROP SCRIPT (Fixed Order)
-- ============================================

-- Drop orders schema tables (order matters due to foreign keys)
DROP TABLE IF EXISTS orders.order_items CASCADE;
DROP TABLE IF EXISTS orders.orders CASCADE;
DROP TABLE IF EXISTS orders.basket_items CASCADE;
DROP TABLE IF EXISTS orders.baskets CASCADE;
DROP TABLE IF EXISTS orders.food_tags CASCADE;
DROP TABLE IF EXISTS orders.dish_projections CASCADE;
DROP TABLE IF EXISTS orders.menu_projections CASCADE;
DROP TABLE IF EXISTS orders.restaurant_projection CASCADE;

-- Drop restaurant schema tables
DROP TABLE IF EXISTS restaurant.restaurant_order_items CASCADE;
DROP TABLE IF EXISTS restaurant.restaurant_orders CASCADE;
DROP TABLE IF EXISTS restaurant.scheduled_dish_change_dishes CASCADE;
DROP TABLE IF EXISTS restaurant.scheduled_dish_changes CASCADE;
DROP TABLE IF EXISTS restaurant.food_tags CASCADE;
DROP TABLE IF EXISTS restaurant.menu_dishes CASCADE;
DROP TABLE IF EXISTS restaurant.dishes CASCADE;
DROP TABLE IF EXISTS restaurant.menus CASCADE;
DROP TABLE IF EXISTS restaurant.restaurants CASCADE;

-- Drop schemas
DROP SCHEMA IF EXISTS orders CASCADE;
DROP SCHEMA IF EXISTS restaurant CASCADE;

