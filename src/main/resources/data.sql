-- Inserting into 'restaurants' if not already exists
INSERT INTO restaurants (id, name, is_kosher, average_Rating)
SELECT 1, 'Taizu', false, 4.83
WHERE NOT EXISTS (
    SELECT 1 FROM restaurants WHERE id = 1
);

-- Inserting into 'cuisines' if not already exists
INSERT INTO cuisines (id, name)
SELECT 1, 'Asian' WHERE NOT EXISTS (SELECT 1 FROM cuisines WHERE name = 'Asian');
INSERT INTO cuisines (id, name)
SELECT 2, 'Mexican' WHERE NOT EXISTS (SELECT 1 FROM cuisines WHERE name = 'Mexican');
INSERT INTO cuisines (id, name)
SELECT 3, 'Indian' WHERE NOT EXISTS (SELECT 1 FROM cuisines WHERE name = 'Indian');

-- Inserting into 'restaurant_cuisines' if not already exists
INSERT INTO restaurant_cuisines (restaurant_id, cuisine_id)
SELECT 1, 1 WHERE NOT EXISTS (SELECT 1 FROM restaurant_cuisines WHERE restaurant_id = 1 AND cuisine_id = 1);
INSERT INTO restaurant_cuisines (restaurant_id, cuisine_id)
SELECT 1, 2 WHERE NOT EXISTS (SELECT 1 FROM restaurant_cuisines WHERE restaurant_id = 1 AND cuisine_id = 2);
INSERT INTO restaurant_cuisines (restaurant_id, cuisine_id)
SELECT 1, 3 WHERE NOT EXISTS (SELECT 1 FROM restaurant_cuisines WHERE restaurant_id = 1 AND cuisine_id = 3);

-- Inserting into 'dishes' if not already exists
-- Insert 'Noodles' if it does not exist
INSERT INTO dishes (id, restaurant_id, name, description, price)
SELECT 1, 1, 'Noodles', 'Amazing one', 59 WHERE NOT EXISTS (
    SELECT 1 FROM dishes WHERE restaurant_id = 1 AND name = 'Noodles'
);

-- Insert 'Humus' if it does not exist
INSERT INTO dishes (id, restaurant_id, name, description, price)
SELECT 2, 1, 'Humus', 'Good one', 48 WHERE NOT EXISTS (
    SELECT 1 FROM dishes WHERE restaurant_id = 1 AND name = 'Humus'
);

