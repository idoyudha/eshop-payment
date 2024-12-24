CREATE TABLE payments (
    id VARCHAR(36) NOT NULL,
    order_id VARCHAR(36) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    note VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_payments_order_id ON payments(order_id);