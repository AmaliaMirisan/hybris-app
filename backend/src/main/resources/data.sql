-- =================================================================
-- E-COMMERCE SAMPLE DATA
-- =================================================================

-- Clear existing data (if any)
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM cart_items;
DELETE FROM carts;
DELETE FROM products;
DELETE FROM categories;
DELETE FROM users;

-- =================================================================
-- CATEGORIES (10 categories)
-- =================================================================
INSERT INTO categories (id, name, description, created_at, updated_at) VALUES
                                                                           (1, 'Electronics', 'Electronic devices, gadgets, and tech accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (2, 'Clothing & Fashion', 'Apparel, shoes, and fashion accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (3, 'Books & Media', 'Books, eBooks, audiobooks, and educational materials', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (4, 'Home & Kitchen', 'Home appliances, kitchenware, and home decor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (5, 'Sports & Outdoors', 'Sports equipment, outdoor gear, and fitness accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (6, 'Health & Beauty', 'Personal care, cosmetics, and health products', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (7, 'Toys & Games', 'Toys, board games, and gaming accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (8, 'Automotive', 'Car accessories, tools, and automotive parts', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (9, 'Garden & Tools', 'Gardening supplies, power tools, and hardware', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           (10, 'Office Supplies', 'Stationery, office equipment, and business supplies', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- USERS (10 users: 2 admins, 8 regular users)
-- =================================================================
INSERT INTO users (id, username, email, password, first_name, last_name, role, is_active, created_at, updated_at) VALUES
-- Admins (password: admin123)
(1, 'admin', 'admin@ecommerce.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'System', 'Administrator', 'ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'superadmin', 'superadmin@ecommerce.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Super', 'Admin', 'ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Regular Users (password: user123)
(3, 'john_doe', 'john.doe@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'John', 'Doe', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'jane_smith', 'jane.smith@yahoo.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Jane', 'Smith', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'mike_johnson', 'mike.johnson@hotmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Michael', 'Johnson', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'sarah_wilson', 'sarah.wilson@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Sarah', 'Wilson', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'david_brown', 'david.brown@outlook.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'David', 'Brown', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'emily_davis', 'emily.davis@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Emily', 'Davis', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'alex_garcia', 'alex.garcia@yahoo.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Alexander', 'Garcia', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'lisa_martinez', 'lisa.martinez@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Lisa', 'Martinez', 'USER', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- PRODUCTS (50+ products across all categories)
-- =================================================================

-- Electronics (Category 1)
INSERT INTO products (id, name, description, price, stock_quantity, category_id, image_url, is_active, created_at, updated_at) VALUES
                                                                                                                                   (1, 'iPhone 15 Pro Max', 'Latest Apple smartphone with titanium design and advanced camera system', 1199.99, 45, 1, 'https://example.com/images/iphone-15-pro-max.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (2, 'Samsung Galaxy S24 Ultra', 'Premium Android smartphone with S Pen and AI features', 1299.99, 38, 1, 'https://example.com/images/galaxy-s24-ultra.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (3, 'MacBook Pro 16" M3', 'Professional laptop with M3 chip for developers and creators', 2499.99, 22, 1, 'https://example.com/images/macbook-pro-16.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (4, 'Dell XPS 13', 'Ultra-portable laptop with premium build quality', 999.99, 35, 1, 'https://example.com/images/dell-xps-13.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (5, 'Sony WH-1000XM5', 'Industry-leading noise canceling wireless headphones', 399.99, 85, 1, 'https://example.com/images/sony-headphones.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (6, 'iPad Air 5th Gen', 'Powerful tablet with M1 chip for productivity and creativity', 599.99, 67, 1, 'https://example.com/images/ipad-air.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (7, 'Gaming Mechanical Keyboard', 'RGB backlit mechanical keyboard for gaming', 129.99, 120, 1, 'https://example.com/images/gaming-keyboard.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (8, '4K Webcam', 'Ultra HD webcam for streaming and video calls', 89.99, 95, 1, 'https://example.com/images/4k-webcam.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Clothing & Fashion (Category 2)
                                                                                                                                   (9, 'Nike Air Max 270', 'Comfortable running shoes with Max Air cushioning', 150.00, 180, 2, 'https://example.com/images/nike-air-max.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (10, 'Adidas Ultraboost 22', 'High-performance running shoes with Boost technology', 180.00, 145, 2, 'https://example.com/images/adidas-ultraboost.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (11, 'Levi''s 501 Original Jeans', 'Classic straight-fit jeans in authentic indigo', 69.99, 250, 2, 'https://example.com/images/levis-501.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (12, 'Champion Reverse Weave Hoodie', 'Premium quality cotton hoodie with iconic logo', 65.00, 200, 2, 'https://example.com/images/champion-hoodie.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (13, 'Ray-Ban Aviator Sunglasses', 'Classic aviator style sunglasses with UV protection', 154.99, 75, 2, 'https://example.com/images/rayban-aviator.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (14, 'North Face Puffer Jacket', 'Warm winter jacket with down insulation', 220.00, 88, 2, 'https://example.com/images/northface-jacket.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (15, 'Calvin Klein Cotton T-Shirt', 'Premium cotton t-shirt with modern fit', 29.99, 300, 2, 'https://example.com/images/ck-tshirt.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Books & Media (Category 3)
                                                                                                                                   (16, 'Clean Code by Robert Martin', 'A handbook of agile software craftsmanship', 42.99, 125, 3, 'https://example.com/images/clean-code.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (17, 'The Pragmatic Programmer', 'Your journey to mastery, 20th Anniversary Edition', 39.99, 98, 3, 'https://example.com/images/pragmatic-programmer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (18, 'Design Patterns: Gang of Four', 'Elements of reusable object-oriented software', 54.99, 67, 3, 'https://example.com/images/design-patterns.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (19, 'Atomic Habits by James Clear', 'An easy and proven way to build good habits', 18.99, 200, 3, 'https://example.com/images/atomic-habits.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (20, 'The Lean Startup', 'How today''s entrepreneurs use continuous innovation', 24.99, 150, 3, 'https://example.com/images/lean-startup.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Home & Kitchen (Category 4)
                                                                                                                                   (21, 'KitchenAid Stand Mixer', 'Professional 5-quart stand mixer for baking', 299.99, 45, 4, 'https://example.com/images/kitchenaid-mixer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (22, 'Instant Pot Duo 7-in-1', 'Multi-use pressure cooker with smart programs', 89.99, 120, 4, 'https://example.com/images/instant-pot.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (23, 'Dyson V15 Detect Vacuum', 'Cordless vacuum with laser dust detection', 749.99, 35, 4, 'https://example.com/images/dyson-v15.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (24, 'Ninja Professional Blender', 'High-speed blender for smoothies and food prep', 79.99, 95, 4, 'https://example.com/images/ninja-blender.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (25, 'Cast Iron Skillet Set', 'Pre-seasoned cast iron cookware set', 69.99, 80, 4, 'https://example.com/images/cast-iron-set.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Sports & Outdoors (Category 5)
                                                                                                                                   (26, 'Wilson NBA Official Basketball', 'Official size and weight basketball', 29.99, 150, 5, 'https://example.com/images/wilson-basketball.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (27, 'Spalding Portable Basketball Hoop', 'Adjustable height basketball system', 199.99, 25, 5, 'https://example.com/images/basketball-hoop.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (28, 'Camping Tent for 4 People', 'Waterproof dome tent with easy setup', 89.99, 60, 5, 'https://example.com/images/camping-tent.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (29, 'Yoga Mat Premium', 'Non-slip exercise mat with carrying strap', 34.99, 200, 5, 'https://example.com/images/yoga-mat.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (30, 'Adjustable Dumbbells Set', 'Space-saving adjustable weight set', 299.99, 40, 5, 'https://example.com/images/dumbbells.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Health & Beauty (Category 6)
                                                                                                                                   (31, 'Electric Toothbrush', 'Sonic toothbrush with multiple cleaning modes', 79.99, 110, 6, 'https://example.com/images/electric-toothbrush.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (32, 'Skincare Routine Set', 'Complete facial care kit with cleanser and moisturizer', 59.99, 85, 6, 'https://example.com/images/skincare-set.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (33, 'Hair Dryer Professional', 'Ionic hair dryer with multiple heat settings', 129.99, 65, 6, 'https://example.com/images/hair-dryer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (34, 'Vitamin D3 Supplements', 'High-potency vitamin D3 for immune support', 19.99, 250, 6, 'https://example.com/images/vitamin-d3.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Toys & Games (Category 7)
                                                                                                                                   (35, 'LEGO Creator Expert Set', 'Advanced building set for adult collectors', 179.99, 55, 7, 'https://example.com/images/lego-creator.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (36, 'Nintendo Switch OLED', 'Gaming console with vibrant OLED screen', 349.99, 75, 7, 'https://example.com/images/nintendo-switch.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (37, 'Chess Set Wooden', 'Handcrafted wooden chess set with storage', 49.99, 90, 7, 'https://example.com/images/wooden-chess.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (38, 'Remote Control Drone', 'HD camera drone with GPS and auto-return', 199.99, 45, 7, 'https://example.com/images/rc-drone.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Automotive (Category 8)
                                                                                                                                   (39, 'Car Phone Mount', 'Magnetic dashboard phone holder', 24.99, 180, 8, 'https://example.com/images/phone-mount.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (40, 'Jump Starter Power Bank', 'Portable car battery jump starter', 89.99, 70, 8, 'https://example.com/images/jump-starter.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (41, 'Car Dash Camera', 'HD dash cam with night vision', 79.99, 95, 8, 'https://example.com/images/dash-camera.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (42, 'Car Vacuum Cleaner', 'Portable handheld car vacuum', 39.99, 120, 8, 'https://example.com/images/car-vacuum.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Garden & Tools (Category 9)
                                                                                                                                   (43, 'Garden Tool Set', 'Complete 10-piece gardening kit', 89.99, 85, 9, 'https://example.com/images/garden-tools.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (44, 'Cordless Drill Set', 'Professional drill with bits and carrying case', 129.99, 60, 9, 'https://example.com/images/cordless-drill.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (45, 'Garden Hose 50ft', 'Heavy-duty expandable garden hose', 34.99, 100, 9, 'https://example.com/images/garden-hose.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (46, 'Lawn Mower Electric', 'Corded electric lawn mower for small yards', 199.99, 35, 9, 'https://example.com/images/lawn-mower.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Office Supplies (Category 10)
                                                                                                                                   (47, 'Ergonomic Office Chair', 'Adjustable office chair with lumbar support', 199.99, 50, 10, 'https://example.com/images/office-chair.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (48, 'Standing Desk Converter', 'Adjustable standing desk for health', 149.99, 40, 10, 'https://example.com/images/standing-desk.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (49, 'Wireless Mouse and Keyboard', 'Ergonomic wireless desktop set', 59.99, 150, 10, 'https://example.com/images/wireless-desktop.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                   (50, 'Monitor 27" 4K', 'Ultra HD monitor for productivity', 299.99, 45, 10, 'https://example.com/images/4k-monitor.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- CARTS (5 sample carts for different users)
-- =================================================================
INSERT INTO carts (id, session_id, user_id, created_at, updated_at) VALUES
                                                                        (1, 'cart-session-001', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                        (2, 'cart-session-002', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                        (3, 'cart-session-003', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                        (4, 'cart-session-004', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Guest cart
                                                                        (5, 'cart-session-005', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- CART ITEMS (20+ items across different carts)
-- =================================================================
INSERT INTO cart_items (id, cart_id, product_id, quantity, unit_price, created_at) VALUES
-- John Doe's cart (user_id: 3)
(1, 1, 1, 1, 1199.99, CURRENT_TIMESTAMP), -- iPhone 15 Pro Max
(2, 1, 5, 1, 399.99, CURRENT_TIMESTAMP),  -- Sony Headphones
(3, 1, 16, 2, 42.99, CURRENT_TIMESTAMP),  -- Clean Code book

-- Jane Smith's cart (user_id: 4)
(4, 2, 9, 1, 150.00, CURRENT_TIMESTAMP),  -- Nike Air Max
(5, 2, 11, 2, 69.99, CURRENT_TIMESTAMP),  -- Levi's Jeans
(6, 2, 13, 1, 154.99, CURRENT_TIMESTAMP), -- Ray-Ban Sunglasses
(7, 2, 32, 1, 59.99, CURRENT_TIMESTAMP),  -- Skincare Set

-- Mike Johnson's cart (user_id: 5)
(8, 3, 21, 1, 299.99, CURRENT_TIMESTAMP), -- KitchenAid Mixer
(9, 3, 22, 1, 89.99, CURRENT_TIMESTAMP),  -- Instant Pot
(10, 3, 24, 1, 79.99, CURRENT_TIMESTAMP), -- Ninja Blender

-- Guest cart
(11, 4, 26, 2, 29.99, CURRENT_TIMESTAMP), -- Basketball
(12, 4, 29, 1, 34.99, CURRENT_TIMESTAMP), -- Yoga Mat
(13, 4, 19, 1, 18.99, CURRENT_TIMESTAMP), -- Atomic Habits

-- Sarah Wilson's cart (user_id: 6)
(14, 5, 35, 1, 179.99, CURRENT_TIMESTAMP), -- LEGO Set
(15, 5, 36, 1, 349.99, CURRENT_TIMESTAMP), -- Nintendo Switch
(16, 5, 37, 1, 49.99, CURRENT_TIMESTAMP),  -- Chess Set

-- Additional items for variety
(17, 1, 31, 1, 79.99, CURRENT_TIMESTAMP),  -- Electric Toothbrush
(18, 2, 39, 2, 24.99, CURRENT_TIMESTAMP),  -- Car Phone Mount
(19, 3, 43, 1, 89.99, CURRENT_TIMESTAMP),  -- Garden Tool Set
(20, 5, 47, 1, 199.99, CURRENT_TIMESTAMP); -- Office Chair

-- =================================================================
-- ORDERS (8 sample orders with different statuses)
-- =================================================================
INSERT INTO orders (id, user_id, order_number, total_amount, status, shipping_address, payment_method, created_at, updated_at) VALUES
                                                                                                                                   (1, 3, 'ORD-2024-001', 1642.97, 'DELIVERED', '123 Main St, Anytown, USA 12345', 'Credit Card', DATEADD('DAY', -15, CURRENT_TIMESTAMP), DATEADD('DAY', -10, CURRENT_TIMESTAMP)),
                                                                                                                                   (2, 4, 'ORD-2024-002', 434.97, 'SHIPPED', '456 Oak Ave, Another City, USA 67890', 'PayPal', DATEADD('DAY', -8, CURRENT_TIMESTAMP), DATEADD('DAY', -7, CURRENT_TIMESTAMP)),
                                                                                                                                   (3, 5, 'ORD-2024-003', 469.97, 'CONFIRMED', '789 Pine Rd, Somewhere, USA 54321', 'Credit Card', DATEADD('DAY', -5, CURRENT_TIMESTAMP), DATEADD('DAY', -4, CURRENT_TIMESTAMP)),
                                                                                                                                   (4, 6, 'ORD-2024-004', 579.97, 'PENDING', '321 Elm St, Hometown, USA 98765', 'Debit Card', DATEADD('DAY', -3, CURRENT_TIMESTAMP), DATEADD('DAY', -3, CURRENT_TIMESTAMP)),
                                                                                                                                   (5, 7, 'ORD-2024-005', 299.99, 'DELIVERED', '654 Maple Dr, Big City, USA 11111', 'Credit Card', DATEADD('DAY', -20, CURRENT_TIMESTAMP), DATEADD('DAY', -15, CURRENT_TIMESTAMP)),
                                                                                                                                   (6, 8, 'ORD-2024-006', 89.98, 'CANCELLED', '987 Cedar Ln, Small Town, USA 22222', 'PayPal', DATEADD('DAY', -12, CURRENT_TIMESTAMP), DATEADD('DAY', -11, CURRENT_TIMESTAMP)),
                                                                                                                                   (7, 9, 'ORD-2024-007', 149.99, 'SHIPPED', '147 Birch Way, Metro Area, USA 33333', 'Credit Card', DATEADD('DAY', -6, CURRENT_TIMESTAMP), DATEADD('DAY', -5, CURRENT_TIMESTAMP)),
                                                                                                                                   (8, 3, 'ORD-2024-008', 79.99, 'DELIVERED', '123 Main St, Anytown, USA 12345', 'Credit Card', DATEADD('DAY', -30, CURRENT_TIMESTAMP), DATEADD('DAY', -25, CURRENT_TIMESTAMP));

-- =================================================================
-- ORDER ITEMS (30+ items across all orders)
-- =================================================================
INSERT INTO order_items (id, order_id, product_id, quantity, unit_price, subtotal) VALUES
-- Order 1 (John Doe - DELIVERED)
(1, 1, 1, 1, 1199.99, 1199.99), -- iPhone 15 Pro Max
(2, 1, 5, 1, 399.99, 399.99),   -- Sony Headphones
(3, 1, 16, 1, 42.99, 42.99),    -- Clean Code book

-- Order 2 (Jane Smith - SHIPPED)
(4, 2, 9, 1, 150.00, 150.00),   -- Nike Air Max
(5, 2, 11, 2, 69.99, 139.98),   -- Levi's Jeans (qty: 2)
(6, 2, 13, 1, 154.99, 154.99),  -- Ray-Ban Sunglasses

-- Order 3 (Mike Johnson - CONFIRMED)
(7, 3, 21, 1, 299.99, 299.99),  -- KitchenAid Mixer
(8, 3, 22, 1, 89.99, 89.99),    -- Instant Pot
(9, 3, 24, 1, 79.99, 79.99),    -- Ninja Blender

-- Order 4 (Sarah Wilson - PENDING)
(10, 4, 35, 1, 179.99, 179.99), -- LEGO Set
(11, 4, 36, 1, 349.99, 349.99), -- Nintendo Switch
(12, 4, 37, 1, 49.99, 49.99),   -- Chess Set

-- Order 5 (David Brown - DELIVERED)
(13, 5, 47, 1, 199.99, 199.99), -- Office Chair
(14, 5, 49, 1, 59.99, 59.99),   -- Wireless Desktop
(15, 5, 31, 1, 79.99, 79.99),   -- Electric Toothbrush

-- Order 6 (Emily Davis - CANCELLED)
(16, 6, 26, 2, 29.99, 59.98),   -- Basketball (qty: 2)
(17, 6, 29, 1, 34.99, 34.99),   -- Yoga Mat

-- Order 7 (Alex Garcia - SHIPPED)
(18, 7, 40, 1, 89.99, 89.99),   -- Jump Starter
(19, 7, 41, 1, 79.99, 79.99),   -- Dash Camera

-- Order 8 (John Doe - DELIVERED) - Previous order
(20, 8, 19, 1, 18.99, 18.99),   -- Atomic Habits
(21, 8, 20, 1, 24.99, 24.99),   -- Lean Startup
(22, 8, 32, 1, 59.99, 59.99),   -- Skincare Set

-- Additional order items for variety
(23, 1, 39, 1, 24.99, 24.99),   -- Car Phone Mount (added to order 1)
(24, 2, 34, 2, 19.99, 39.98),   -- Vitamins (added to order 2)
(25, 3, 43, 1, 89.99, 89.99),   -- Garden Tools (added to order 3)
(26, 5, 17, 1, 39.99, 39.99),   -- Pragmatic Programmer (added to order 5)
(27, 7, 42, 1, 39.99, 39.99),   -- Car Vacuum (added to order 7)
(28, 4, 15, 1, 29.99, 29.99),   -- CK T-Shirt (added to order 4)
(29, 6, 30, 1, 299.99, 299.99), -- Dumbbells (added to cancelled order)
(30, 8, 33, 1, 129.99, 129.99); -- Hair Dryer (added to order 8)

