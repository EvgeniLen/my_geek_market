DROP TABLE IF EXISTS orders_statuses;
CREATE TABLE orders_statuses (
                                 id INT(12) NOT NULL AUTO_INCREMENT,
                                 title  VARCHAR(50) DEFAULT NULL,
                                 PRIMARY KEY (id)
) DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS delivery_addresses;
CREATE TABLE delivery_addresses (
                                    id INT(12) NOT NULL AUTO_INCREMENT,
                                    user_id INT(12) NOT NULL,
                                    address VARCHAR(500) NOT NULL,
                                    PRIMARY KEY (id),
                                    CONSTRAINT FK_USER_ID_DEL_ADR FOREIGN KEY (user_id)
                                        REFERENCES users (id)
)DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
                        id	INT(12) NOT NULL AUTO_INCREMENT,
                        username VARCHAR(255) NOT NULL,
                        status_id INT(12) NOT NULL,
                        price DECIMAL(10,2) NOT NULL,
                        delivery_price DECIMAL(10,2) NOT NULL,
                        delivery_address_id INT(12) NOT NULL,
                        phone_number VARCHAR(20) NOT NULL,
                        delivery_date TIMESTAMP NOT NULL,
                        create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (id),
                        CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)
                            REFERENCES orders_statuses (id),
                        CONSTRAINT FK_DELIVERY_ADDRESS_ID FOREIGN KEY (delivery_address_id)
                            REFERENCES delivery_addresses (id)
)DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS orders_item;
CREATE TABLE orders_item (
                             id INT(12) NOT NULL AUTO_INCREMENT,
                             product_id INT(12) NOT NULL,
                             order_id INT(12) NOT NULL,
                             quantity INT NOT NULL,
                             item_price DECIMAL(10,2) NOT NULL,
                             total_price DECIMAL(10,2) NOT NULL,
                             title VARCHAR(255) NOT NULL ,
                             PRIMARY KEY (id),
                             CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
                                 REFERENCES orders (id),
                             CONSTRAINT FK_PRODUCT_ID_ORD_IT FOREIGN KEY (product_id)
                                 REFERENCES products (id)
)DEFAULT CHARSET = utf8;