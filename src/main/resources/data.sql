 --Insert cuisines
INSERT INTO cuisines (name) VALUES ('Asian'), ('Mexican'), ('Indian');

 --Insert restaurants
INSERT INTO restaurants (name, is_kosher, rating) VALUES ('Taizu', false, 4.83);

 --Map cuisines to the restaurant
INSERT INTO restaurant_cuisines (restaurant_id, cuisine_id) VALUES (1, 1), (1, 2), (1, 3);

 --Insert dishes for Taizu
iNSERT INTO dishes (restaurant_id, name, description, price) VALUES
(1, 'Noodles', 'Amazing one', 59),
(1, 'Shakshuka', 'Great one', 34),
(1, 'Humus', 'Good one', 48);

 --Insert a rating
INSERT INTO ratings (restaurant_id, rating) VALUES (1, 4.83);

 --Insert an order
INSERT INTO orders (restaurant_id) VALUES (1);

 --Insert order items
INSERT INTO order_items (order_id, dish_id, amount) VALUES (1, 1, 2), (1, 2, 1);
