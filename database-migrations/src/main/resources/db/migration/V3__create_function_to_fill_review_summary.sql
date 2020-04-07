CREATE OR REPLACE FUNCTION compute_review_summary()
    RETURNS trigger AS
$BODY$
BEGIN
    INSERT INTO review_summary (restaurant_id, average, qtd_review)
    VALUES (NEW.restaurant_id, NEW.review, 1)
    ON CONFLICT (restaurant_id)
        DO UPDATE SET average = (((review_summary.average * review_summary.qtd_review) + NEW.review) / (review_summary.qtd_review + 1)),
                      qtd_review = (review_summary.qtd_review + 1);

    RETURN NEW;
end;
$BODY$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tg_compute_review_summary
    BEFORE INSERT
    ON review
    FOR EACH ROW
EXECUTE PROCEDURE compute_review_summary();