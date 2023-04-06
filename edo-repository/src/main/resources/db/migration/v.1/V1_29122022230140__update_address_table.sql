ALTER TABLE address ADD COLUMN longitude VARCHAR(100) NOT NULL;
ALTER TABLE address ADD COLUMN latitude VARCHAR(100) NOT NULL;
COMMENT ON COLUMN address.longitude IS 'долгота';
COMMENT ON COLUMN address.latitude IS 'широта';