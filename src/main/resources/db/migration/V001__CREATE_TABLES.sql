CREATE TABLE products (
  id bigserial PRIMARY KEY,
  name varchar(255),
  price varchar(255)
);

CREATE TABLE cross_sales (
  id bigserial PRIMARY KEY,
  product_id bigserial,
  xsell_id bigserial
);

CREATE TABLE baskets (
    id bigserial PRIMARY KEY,
    user_id bigserial UNIQUE NOT NULL,
    items jsonb
);
