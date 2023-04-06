ALTER TABLE department DROP COLUMN address;
ALTER TABLE department ADD COLUMN address BIGINT REFERENCES address (id);
COMMENT ON COLUMN department.address IS 'адрес департамента, внутри сущность Address';