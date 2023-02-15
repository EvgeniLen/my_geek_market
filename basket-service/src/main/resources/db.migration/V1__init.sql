DROP TABLE IF EXISTS baskets;
CREATE TABLE baskets (
                         id	INT(12) NOT NULL AUTO_INCREMENT,
                         username VARCHAR(255) NOT NULL,
                         total_price DECIMAL(10,2) NOT NULL,
                         create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (id)
)DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS basket_items;
CREATE TABLE basket_items (
                              id INT(12) NOT NULL AUTO_INCREMENT,
                              product_id INT(12) NOT NULL,
                              basket_id INT(12) NOT NULL,
                              quantity INT NOT NULL,
                              item_price DECIMAL(10,2) NOT NULL,
                              total_price DECIMAL(10,2) NOT NULL,
                              title VARCHAR(255) NOT NULL ,
                              PRIMARY KEY (id),
                              CONSTRAINT FK_BASKET_ID FOREIGN KEY (basket_id)
                                  REFERENCES baskets (id),
                              CONSTRAINT FK_PRODUCT_ID_BASK_ID FOREIGN KEY (product_id)
                                  REFERENCES products (id)
)DEFAULT CHARSET = utf8;


INSERT INTO delivery_addresses (user_id, address)
VALUES
    (1, "18a Diagon Alley"),
    (1, "4 Privet Drive");

INSERT INTO orders_statuses (title)
VALUES
    ("Сформирован"), ("Отправлен");