-- Create the database
CREATE DATABASE `e-commerce-core`;

-- Use the database
USE `e-commerce-core`;

-- Table for users
CREATE TABLE `user` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(255) NOT NULL,
                        `email` VARCHAR(255) NOT NULL UNIQUE,
                        `password` VARCHAR(255) NOT NULL,
                        `roles` VARCHAR(255) NOT NULL,
                        PRIMARY KEY (`id`)
);

-- Table for products
CREATE TABLE `product` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(255) NOT NULL,
                           `description` VARCHAR(255),
                           `price` DECIMAL(10, 2) NOT NULL,
                           `stock` INT NOT NULL,
                           PRIMARY KEY (`id`)
);

-- Table for orders
CREATE TABLE `order` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `date` DATETIME NOT NULL,
                         `status` VARCHAR(50) NOT NULL,
                         `total` DECIMAL(10, 2) NOT NULL,
                         `user_id` BIGINT,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
);

-- Table for order details (order items)
CREATE TABLE `order_detail` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT,
                                `quantity` INT NOT NULL,
                                `unit_price` DECIMAL(10, 2) NOT NULL,
                                `product_id` BIGINT,
                                `order_id` BIGINT,
                                PRIMARY KEY (`id`),
                                FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
                                FOREIGN KEY (`order_id`) REFERENCES `order`(`id`)
);


INSERT INTO `user` (`name`, `email`, `password`, `roles`) VALUES
                                                              ('Ana García', 'ana.g@example.com', 'hashed_pass_1', 'ROLE_USER'),
                                                              ('Carlos Rodríguez', 'carlos.r@example.com', 'hashed_pass_2', 'ROLE_USER'),
                                                              ('Sofía López', 'sofia.l@example.com', 'hashed_pass_3', 'ROLE_USER'),
                                                              ('Juan Pérez', 'juan.p@example.com', 'hashed_pass_4', 'ROLE_USER'),
                                                              ('Valeria Torres', 'valeria.t@example.com', 'hashed_pass_5', 'ROLE_USER'),
                                                              ('Martín Fernández', 'martin.f@example.com', 'hashed_pass_6', 'ROLE_USER'),
                                                              ('Florencia Díaz', 'florencia.d@example.com', 'hashed_pass_7', 'ROLE_USER'),
                                                              ('Gonzalo Gómez', 'gonzalo.g@example.com', 'hashed_pass_8', 'ROLE_USER'),
                                                              ('Marina Ruiz', 'marina.r@example.com', 'hashed_pass_9', 'ROLE_USER'),
                                                              ('Pablo Acosta', 'pablo.a@example.com', 'hashed_pass_10', 'ROLE_USER');

Select * from `user`;

INSERT INTO `product` (`name`, `description`, `price`, `stock`) VALUES
                                                                    ('Smartphone Galaxy S23', 'Un teléfono de alta gama con una excelente cámara.', 1500000.00, 50),
                                                                    ('Laptop MacBook Air', 'Portátil ligero y potente para uso diario y profesional.', 1800000.00, 30),
                                                                    ('Auriculares Inalámbricos', 'Auriculares con cancelación de ruido y batería de larga duración.', 250000.00, 200),
                                                                    ('Smart Watch Serie 8', 'Reloj inteligente para monitorear salud y estado físico.', 500000.00, 150),
                                                                    ('Teclado Mecánico RGB', 'Teclado para juegos con iluminación personalizable.', 100000.00, 75),
                                                                    ('Parlante Bluetooth Portátil', 'Parlante compacto y resistente al agua para llevar a cualquier lugar.', 75000.00, 100),
                                                                    ('Smart TV 4K UHD 55"', 'Televisor de alta definición con funciones inteligentes.', 900000.00, 20),
                                                                    ('Disco Duro Externo 2TB', 'Almacenamiento rápido y seguro para tus archivos.', 95000.00, 90),
                                                                    ('Mouse Inalámbrico', 'Diseño ergonómico para un uso cómodo y prolongado.', 40000.00, 300),
                                                                    ('Cafetera Programable', 'Cafetera con temporizador para preparar tu café por la mañana.', 50000.00, 80);
