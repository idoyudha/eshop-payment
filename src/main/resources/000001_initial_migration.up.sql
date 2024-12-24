CREATE TABLE IF NOT EXISTS `payments` (
  `id` uuid PRIMARY KEY,
  `order_id` uuid NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `status` ENUM ('PENDING', 'APPROVED', 'REJECTED') NOT NULL,
  `note` varchar(255),
  `created_at` timestamp NOT NULL,
  `updated_at` timestamp NOT NULL,
  `deleted_at` timestamp
);

CREATE INDEX `payments_index_0` ON `payments` (`order_id`);