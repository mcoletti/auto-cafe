
-- Create Custom Types
CREATE TYPE IF NOT EXISTS autocafe.address (
  street1 text,
  street2 text,
  city text,
  zip_code int,
  phones set<text>
);

CREATE TYPE IF NOT EXISTS autocafe.location (
  name text,
  address frozen<address>
);


-- **** Create Client Tables ****
-- Client Table
CREATE TABLE IF NOT EXISTS autocafe.clients (
    id uuid,
    client_name text,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (id, client_name)
)
WITH CLUSTERING ORDER BY (client_name ASC);

-- Client Locations
CREATE TABLE IF NOT EXISTS autocafe.client_locations (
    client_id uuid,
    location frozen<location>,
    PRIMARY KEY ((client_id),location)
);
-- Client Vehicles
CREATE TABLE IF NOT EXISTS autocafe.client_vehicles (
    client_id uuid,
    vehicle_id uuid,
    key_name text,
    PRIMARY KEY ((client_id), key_name)
)
WITH CLUSTERING ORDER BY (key_name ASC);

-- **** Create Vehicle Table ****
CREATE TABLE IF NOT EXISTS autocafe.vehicle_details (
    id uuid,
    client_id uuid,
    key_name text,
    stock_num int,
    ext_color text,
    int_color text,
    trim text,
    price decimal,
    mileage text,
    category text,
    model text,
    body_style text,
    year_value text,
    manufacture text,
    location frozen<location>,
    PRIMARY KEY ((id,client_id), key_name)
)
WITH CLUSTERING ORDER BY (key_name ASC);

-- Vehicle Categories
CREATE TABLE IF NOT EXISTS autocafe.vehicle_categories (
    vehicle_id uuid,
    category text,
    PRIMARY KEY ((vehicle_id,category))
);

-- Vehicle Locations
CREATE TABLE IF NOT EXISTS autocafe.vehicle_locations (
    vehicle_id uuid,
    location frozen<location>,
    PRIMARY KEY ((vehicle_id,location))
);

-- Vehicle Image Table
CREATE TABLE IF NOT EXISTS autocafe.vehicle_images (
    vehicle_id uuid,
    img_name text,
    img_cdn_loc text,
    img_size int,
    img_suffix text,
    img_type text,
    PRIMARY KEY ((vehicle_id,img_type),img_name)
)
WITH CLUSTERING ORDER BY (img_name ASC);

-- ******** Common LookUp table ***********
-- Categories Table
CREATE TABLE IF NOT EXISTS autocafe.categories(
    id uuid,
    category text,
    PRIMARY KEY ((id,category))
);

-- Models Table
CREATE TABLE IF NOT EXISTS autocafe.models(
    id uuid,
    model text,
    PRIMARY KEY ((id,model))
);

-- Body Styles
CREATE TABLE IF NOT EXISTS autocafe.body_styles(
    id uuid,
    body_style text,
    PRIMARY KEY ((id,body_style))
);

-- Year table
CREATE TABLE IF NOT EXISTS autocafe.years(
    id uuid,
    year_value int,
    PRIMARY KEY ((id,year_value))
);

-- Manufacture table
CREATE TABLE IF NOT EXISTS autocafe.manufactures(
    id uuid,
    manufacture text,
    PRIMARY KEY ((id,manufacture))
);

-- *** Create Indexes
CREATE INDEX idx_client_name ON autocafe.clients (client_name);
CREATE INDEX idx_key_name ON autocafe.client_vehicles (key_name);
CREATE INDEX idx_vhicle_dtls_key_name ON autocafe.vehicle_details (key_name);
CREATE INDEX idx_category ON autocafe.vehicle_categories (category);
CREATE INDEX idx_location ON autocafe.vehicle_locations (location);
