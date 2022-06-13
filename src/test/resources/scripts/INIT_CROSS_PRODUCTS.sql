INSERT INTO products (id, name, price)
VALUES
(1, 'Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3', '999,00 €'),
(2, 'Samsonite Airglow Laptop Sleeve 13.3', '41,34 €'),
(3, 'Logitech Wireless Mouse M185', '10,78 €'),
(4, 'Fellowes Mouse Pad Black', '1,34 €');

INSERT INTO cross_sales (product_id, xsell_id)
VALUES
(1, 2),
(1, 3),
(3, 4);