CREATE TABLE fruits (
    id UUID PRIMARY KEY default gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    stock BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP WITHOUT TIME ZONE
);

CREATE INDEX fruits_name ON fruits(name);
CREATE INDEX fruits_created_at ON fruits(created_at);
CREATE INDEX fruits_updated_at ON fruits(updated_at);

-- Create a function to update the updated_at column
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to call the function before any update
CREATE TRIGGER update_fruits_updated_at
    BEFORE UPDATE ON fruits
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();