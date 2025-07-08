-- =================================================================
-- E-COMMERCE SAMPLE DATA (Fixed for auto-increment)
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
-- CATEGORIES (10 categories) - Let H2 auto-generate IDs
-- =================================================================
INSERT INTO categories (name, description, created_at, updated_at) VALUES
                                                                       ('Electronics', 'Electronic devices, gadgets, and tech accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Clothing & Fashion', 'Apparel, shoes, and fashion accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Books & Media', 'Books, eBooks, audiobooks, and educational materials', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Home & Kitchen', 'Home appliances, kitchenware, and home decor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Sports & Outdoors', 'Sports equipment, outdoor gear, and fitness accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Health & Beauty', 'Personal care, cosmetics, and health products', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Toys & Games', 'Toys, board games, and gaming accessories', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Automotive', 'Car accessories, tools, and automotive parts', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Garden & Tools', 'Gardening supplies, power tools, and hardware', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                       ('Office Supplies', 'Stationery, office equipment, and business supplies', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- USERS (10 users: 2 admins, 8 regular users) - Let H2 auto-generate IDs
-- =================================================================
INSERT INTO users (username, email, password, first_name, last_name, role, is_active, created_at, updated_at) VALUES
-- Admins (password: admin123)
('admin', 'admin@ecommerce.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'System', 'Administrator', 'ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('superadmin', 'superadmin@ecommerce.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Super', 'Admin', 'ADMIN', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Regular Users (password: user123)
('john_doe', 'john.doe@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'John', 'Doe', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('jane_smith', 'jane.smith@yahoo.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Jane', 'Smith', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('mike_johnson', 'mike.johnson@hotmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Michael', 'Johnson', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sarah_wilson', 'sarah.wilson@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Sarah', 'Wilson', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('david_brown', 'david.brown@outlook.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'David', 'Brown', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('emily_davis', 'emily.davis@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Emily', 'Davis', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('alex_garcia', 'alex.garcia@yahoo.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Alexander', 'Garcia', 'USER', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('lisa_martinez', 'lisa.martinez@gmail.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2uheWG/igi.', 'Lisa', 'Martinez', 'USER', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =================================================================
-- PRODUCTS (50+ products across all categories) - Use category references
-- =================================================================

-- Electronics (Category 1)
INSERT INTO products (name, description, price, stock_quantity, category_id, image_url, is_active, created_at, updated_at) VALUES
                                                                                                                               ('iPhone 15 Pro Max', 'Latest Apple smartphone with titanium design and advanced camera system', 1199.99, 45, 1, 'https://example.com/images/iphone-15-pro-max.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Samsung Galaxy S24 Ultra', 'Premium Android smartphone with S Pen and AI features', 1299.99, 38, 1, 'https://example.com/images/galaxy-s24-ultra.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('MacBook Pro 16" M3', 'Professional laptop with M3 chip for developers and creators', 2499.99, 22, 1, 'https://example.com/images/macbook-pro-16.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Dell XPS 13', 'Ultra-portable laptop with premium build quality', 999.99, 35, 1, 'https://example.com/images/dell-xps-13.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Sony WH-1000XM5', 'Industry-leading noise canceling wireless headphones', 399.99, 85, 1, 'https://example.com/images/sony-headphones.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('iPad Air 5th Gen', 'Powerful tablet with M1 chip for productivity and creativity', 599.99, 67, 1, 'https://example.com/images/ipad-air.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Gaming Mechanical Keyboard', 'RGB backlit mechanical keyboard for gaming', 129.99, 120, 1, 'https://example.com/images/gaming-keyboard.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('4K Webcam', 'Ultra HD webcam for streaming and video calls', 89.99, 95, 1, 'https://example.com/images/4k-webcam.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Clothing & Fashion (Category 2)
                                                                                                                               ('Nike Air Max 270', 'Comfortable running shoes with Max Air cushioning', 150.00, 180, 2, 'https://example.com/images/nike-air-max.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Adidas Ultraboost 22', 'High-performance running shoes with Boost technology', 180.00, 145, 2, 'https://example.com/images/adidas-ultraboost.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Levi''s 501 Original Jeans', 'Classic straight-fit jeans in authentic indigo', 69.99, 250, 2, 'https://example.com/images/levis-501.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Champion Reverse Weave Hoodie', 'Premium quality cotton hoodie with iconic logo', 65.00, 200, 2, 'https://example.com/images/champion-hoodie.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Ray-Ban Aviator Sunglasses', 'Classic aviator style sunglasses with UV protection', 154.99, 75, 2, 'https://example.com/images/rayban-aviator.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('North Face Puffer Jacket', 'Warm winter jacket with down insulation', 220.00, 88, 2, 'https://example.com/images/northface-jacket.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Calvin Klein Cotton T-Shirt', 'Premium cotton t-shirt with modern fit', 29.99, 300, 2, 'https://example.com/images/ck-tshirt.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Books & Media (Category 3)
                                                                                                                               ('Clean Code by Robert Martin', 'A handbook of agile software craftsmanship', 42.99, 125, 3, 'https://example.com/images/clean-code.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('The Pragmatic Programmer', 'Your journey to mastery, 20th Anniversary Edition', 39.99, 98, 3, 'https://example.com/images/pragmatic-programmer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Design Patterns: Gang of Four', 'Elements of reusable object-oriented software', 54.99, 67, 3, 'https://example.com/images/design-patterns.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Atomic Habits by James Clear', 'An easy and proven way to build good habits', 18.99, 200, 3, 'https://example.com/images/atomic-habits.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('The Lean Startup', 'How today''s entrepreneurs use continuous innovation', 24.99, 150, 3, 'https://example.com/images/lean-startup.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Home & Kitchen (Category 4)
                                                                                                                               ('KitchenAid Stand Mixer', 'Professional 5-quart stand mixer for baking', 299.99, 45, 4, 'https://example.com/images/kitchenaid-mixer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Instant Pot Duo 7-in-1', 'Multi-use pressure cooker with smart programs', 89.99, 120, 4, 'https://example.com/images/instant-pot.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Dyson V15 Detect Vacuum', 'Cordless vacuum with laser dust detection', 749.99, 35, 4, 'https://example.com/images/dyson-v15.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Ninja Professional Blender', 'High-speed blender for smoothies and food prep', 79.99, 95, 4, 'https://example.com/images/ninja-blender.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Cast Iron Skillet Set', 'Pre-seasoned cast iron cookware set', 69.99, 80, 4, 'https://example.com/images/cast-iron-set.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Sports & Outdoors (Category 5)
                                                                                                                               ('Wilson NBA Official Basketball', 'Official size and weight basketball', 29.99, 150, 5, 'https://example.com/images/wilson-basketball.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Spalding Portable Basketball Hoop', 'Adjustable height basketball system', 199.99, 25, 5, 'https://example.com/images/basketball-hoop.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Camping Tent for 4 People', 'Waterproof dome tent with easy setup', 89.99, 60, 5, 'https://example.com/images/camping-tent.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Yoga Mat Premium', 'Non-slip exercise mat with carrying strap', 34.99, 200, 5, 'https://example.com/images/yoga-mat.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Adjustable Dumbbells Set', 'Space-saving adjustable weight set', 299.99, 40, 5, 'https://example.com/images/dumbbells.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Health & Beauty (Category 6)
                                                                                                                               ('Electric Toothbrush', 'Sonic toothbrush with multiple cleaning modes', 79.99, 110, 6, 'https://example.com/images/electric-toothbrush.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Skincare Routine Set', 'Complete facial care kit with cleanser and moisturizer', 59.99, 85, 6, 'https://example.com/images/skincare-set.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Hair Dryer Professional', 'Ionic hair dryer with multiple heat settings', 129.99, 65, 6, 'https://example.com/images/hair-dryer.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Vitamin D3 Supplements', 'High-potency vitamin D3 for immune support', 19.99, 250, 6, 'https://example.com/images/vitamin-d3.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Toys & Games (Category 7)
                                                                                                                               ('LEGO Creator Expert Set', 'Advanced building set for adult collectors', 179.99, 55, 7, 'https://example.com/images/lego-creator.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Nintendo Switch OLED', 'Gaming console with vibrant OLED screen', 349.99, 75, 7, 'https://example.com/images/nintendo-switch.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Chess Set Wooden', 'Handcrafted wooden chess set with storage', 49.99, 90, 7, 'https://example.com/images/wooden-chess.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Remote Control Drone', 'HD camera drone with GPS and auto-return', 199.99, 45, 7, 'https://example.com/images/rc-drone.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Automotive (Category 8)
                                                                                                                               ('Car Phone Mount', 'Magnetic dashboard phone holder', 24.99, 180, 8, 'https://example.com/images/phone-mount.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Jump Starter Power Bank', 'Portable car battery jump starter', 89.99, 70, 8, 'https://example.com/images/jump-starter.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Car Dash Camera', 'HD dash cam with night vision', 79.99, 95, 8, 'https://example.com/images/dash-camera.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Car Vacuum Cleaner', 'Portable handheld car vacuum', 39.99, 120, 8, 'https://example.com/images/car-vacuum.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Garden & Tools (Category 9)
                                                                                                                               ('Garden Tool Set', 'Complete 10-piece gardening kit', 89.99, 85, 9, 'https://example.com/images/garden-tools.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Cordless Drill Set', 'Professional drill with bits and carrying case', 129.99, 60, 9, 'https://example.com/images/cordless-drill.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Garden Hose 50ft', 'Heavy-duty expandable garden hose', 34.99, 100, 9, 'https://example.com/images/garden-hose.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Lawn Mower Electric', 'Corded electric lawn mower for small yards', 199.99, 35, 9, 'https://example.com/images/lawn-mower.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Office Supplies (Category 10)
                                                                                                                               ('Ergonomic Office Chair', 'Adjustable office chair with lumbar support', 199.99, 50, 10, 'https://example.com/images/office-chair.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Standing Desk Converter', 'Adjustable standing desk for health', 149.99, 40, 10, 'https://example.com/images/standing-desk.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Wireless Mouse and Keyboard', 'Ergonomic wireless desktop set', 59.99, 150, 10, 'https://example.com/images/wireless-desktop.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                               ('Monitor 27" 4K', 'Ultra HD monitor for productivity', 299.99, 45, 10, 'https://example.com/images/4k-monitor.jpg', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Note: Carts, Cart Items, Orders, and Order Items will be created dynamically through the application
-- This ensures proper auto-increment behavior and avoids primary key conflicts