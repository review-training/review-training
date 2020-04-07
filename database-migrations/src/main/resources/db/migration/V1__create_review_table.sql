CREATE TABLE IF NOT EXISTS review(
    restaurant_id uuid not null,
    user_id uuid not null,
    order_id uuid not null,
    created_at timestamp without time zone,
    review int not null
);