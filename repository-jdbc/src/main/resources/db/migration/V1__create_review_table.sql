CREATE TABLE IF NOT EXISTS review(
    restaurant_id uuid not null,
    user_id uuid not null,
    order_id uuid not null,
    review int4 not null
);