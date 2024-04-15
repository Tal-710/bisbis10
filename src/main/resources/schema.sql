begin;
-- Create Restaurants Table
CREATE TABLE restaurants (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    rating DECIMAL(4,2),
    is_kosher BOOLEAN NOT NULL
);
--
--Create Cuisines Table
CREATE TABLE cuisines (
   id SERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL
);

-- Create Restaurant Cuisines Junction Table
CREATE TABLE restaurant_cuisines (
    restaurant_id INT,
    cuisine_id INT,
    PRIMARY KEY (restaurant_id, cuisine_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
    FOREIGN KEY (cuisine_id) REFERENCES cuisines(id)
);

--Create Dishes Table
CREATE TABLE dishes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
   description TEXT,
   price DECIMAL(10,2),
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Create Ratings Table
CREATE TABLE ratings (
    id SERIAL PRIMARY KEY,
    restaurant_id INT,
    rating DECIMAL(3,2),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Create Orders Table
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- Create Order Items Table
CREATE TABLE order_items (
    order_id INT,
    dish_id INT,
   amount INT,
   PRIMARY KEY (order_id, dish_id),
   FOREIGN KEY (order_id) REFERENCES orders(order_id),
   FOREIGN KEY (dish_id) REFERENCES dishes(id)
);
commit;
