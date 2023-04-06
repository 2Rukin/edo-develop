CREATE TABLE IF NOT EXISTS notification (
    id SERIAL PRIMARY KEY,
    notification_type VARCHAR(100)
);

ALTER TABLE IF EXISTS employee
ADD COLUMN notification_id BIGINT,
ADD CONSTRAINT employee_notification_fk FOREIGN KEY (notification_id) REFERENCES notification (id);
