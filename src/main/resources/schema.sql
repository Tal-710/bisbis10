BEGIN;

-- Create Restaurants Table
CREATE TABLE IF NOT EXISTS restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_kosher BOOLEAN NOT NULL,
    total_Ratings DECIMAL(10,2) DEFAULT 0.00,
    number_Of_Ratings INT DEFAULT 0,
    average_Rating DECIMAL(4,2) DEFAULT 0.00
);

-- Create Cuisines Table
CREATE TABLE IF NOT EXISTS cuisines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Create Restaurant Cuisines Junction Table
CREATE TABLE IF NOT EXISTS restaurant_cuisines (
    restaurant_id INT,
    cuisine_id INT,
    PRIMARY KEY (restaurant_id, cuisine_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE,
    FOREIGN KEY (cuisine_id) REFERENCES cuisines(id) ON DELETE CASCADE
);

-- Create Dishes Table
CREATE TABLE IF NOT EXISTS dishes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2),
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);

-- Create Orders Table
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    restaurant_id INT NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id) ON DELETE CASCADE
);


-- Create Order Items Table
CREATE TABLE IF NOT EXISTS order_items (
    order_id UUID,
    dish_id INT,
    amount INT,
    PRIMARY KEY (order_id, dish_id),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dishes(id) ON DELETE CASCADE
);

COMMIT;
