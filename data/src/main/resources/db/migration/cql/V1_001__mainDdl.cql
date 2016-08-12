
-- ************ Create Custom Types ********************

CREATE TYPE IF NOT EXISTS autocafe.address (
  street1 text,
  street2 text,
  city text,
  state text,
  zip_code int,
  phones set<text>
);

CREATE TYPE IF NOT EXISTS autocafe.location (
  name text,
  address frozen<address>
);

CREATE TYPE IF NOT EXISTS autocafe.vehicle_detail (
    ext_color text,
    int_color text,
    trim text,
    body_style text,
    year int,
    make text,
    model text,
    mileage double
);
-- ***********************************************************

-- **************** CREATE TABLE(S) SCRIPTS *****************************

-- Client Table
CREATE TABLE IF NOT EXISTS autocafe.clients (
    id uuid,
    client_name text,
    location Map<text, frozen<location>>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (id,client_name)
)
WITH CLUSTERING ORDER BY (client_name ASC);

CREATE INDEX idx_client_name ON autocafe.clients (client_name);


-- Client Vehicles
CREATE TABLE IF NOT EXISTS autocafe.client_vehicles (
    client_id uuid,
    vehicle_id uuid,
    stock_num int,
    short_desc text,
    price double,
    location frozen<location>,
    details frozen<vehicle_detail>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (client_id,vehicle_id)
);

-- Vehicle Image Table
CREATE TABLE IF NOT EXISTS autocafe.vehicle_images (
    vehicle_id uuid,
    img_name text,
    img_cdn_loc text,
    img_size int,
    img_suffix text,
    img_type text,
    img_order int,
    PRIMARY KEY ((vehicle_id),img_type)
)
WITH CLUSTERING ORDER BY (img_type ASC);



