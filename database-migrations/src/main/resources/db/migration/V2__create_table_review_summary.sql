CREATE TABLE IF NOT EXISTS review_summary (
    restaurant_id uuid not null unique,
    average numeric(2, 1) not null,
    qtd_review int not null
);