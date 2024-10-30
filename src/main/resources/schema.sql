CREATE TABLE IF NOT EXISTS bank_account (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY ,
                              balance DECIMAL(19, 4) DEFAULT 0
);

CREATE TABLE IF NOT EXISTS user (
                      dtype ENUM('OWNER', 'SUPPLIER') NOT NULL,
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      last_name VARCHAR(100) NOT NULL,
                      name VARCHAR(100) NOT NULL,
                      middle_name VARCHAR(100) NOT NULL,
                      phone VARCHAR(100) UNIQUE NOT NULL,
                      email VARCHAR(100) UNIQUE NOT NULL,
                      telegram VARCHAR(100) UNIQUE NOT NULL,
                      password TEXT NOT NULL,
                      shop_name VARCHAR(100),
                      bank_account_id BIGINT UNIQUE,
                      company_name VARCHAR(100),
                      FOREIGN KEY (bank_account_id) REFERENCES bank_account(id)

);

CREATE TABLE IF NOT EXISTS product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         number BIGINT NOT NULL,
                         price_per_piece DECIMAL(19, 2) NOT NULL,
                         user_id BIGINT,
                         FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        product_id BIGINT,
                        number BIGINT NOT NULL,
                        owner_id BIGINT,
                        supplier_id BIGINT,
                        FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE SET NULL,
                        FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE SET NULL,
                        FOREIGN KEY (supplier_id) REFERENCES user(id) ON DELETE SET NULL
);
CREATE TABLE IF NOT EXISTS history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('ERROR', 'SUCCESS') NOT NULL,
    product_id BIGINT,
    number_of_product BIGINT,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE SET NULL
    );
