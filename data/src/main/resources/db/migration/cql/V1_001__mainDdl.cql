
-- ************ Create Custom Types ********************

CREATE TYPE IF NOT EXISTS autocafe.address (
  street1 text,
  street2 text,
  city text,
  state text,
  zip_code int,
  phones set<text>
);

CREATE TYPE IF NOT EXISTS autocafe.location_detail(
    name text,
    address frozen<address>
);

CREATE TYPE IF NOT EXISTS autocafe.contact(
    first_name text,
    last_name text,
    address frozen<address>
);

-- **************** CREATE TABLE(S) SCRIPTS *****************************

-- clients table
CREATE TABLE IF NOT EXISTS autocafe.clients (
    id uuid,
    name text,
    location_detail frozen<location_detail>,
    home_page_text text,
    created timeuuid,
    created_user text,
    modified timeuuid,
    modified_user text,
    PRIMARY KEY (id,name)
);

-- Dealership Table
CREATE TABLE IF NOT EXISTS autocafe.dealerships (
    client_id uuid,
    id uuid,
    name text,
    contacts list<frozen<contact>>,
    header_title text,
    header_img_url text,
    home_welcome_message text,
    make_vehicle_totals map<text,int>,
    location_detail frozen<location_detail>,
    created_user text,
    created timeuuid,
    modified_user text,
    modified timeuuid,
    PRIMARY KEY (client_id,id,name)
);

CREATE INDEX dealership_id ON autocafe.dealerships (id);

-- dealership lots
CREATE TABLE IF NOT EXISTS autocafe.dealership_lots (
    dealership_id uuid,
    lot_id bigint,
    lot_location text,
    created timeuuid,
    PRIMARY KEY (dealership_id,lot_id)
);

CREATE INDEX dealership_lots_id ON autocafe.dealership_lots (lot_id);

-- Client Vehicles
CREATE TABLE IF NOT EXISTS autocafe.vehicles (
    dealership_id uuid,
    id uuid,
    stock_num text,
    vin text,
    short_description text,
    options text,
    price double,
    invoice_amount double,
    ext_color text,
    int_color text,
    trim text,
    body_style text,
    year int,
    make text,
    model text,
    mileage double,
    lot_id bigint,
    lot_location text,
    img_url text,
    created_user text,
    created timeuuid,
    modified_user text,
    modified timeuuid,
    PRIMARY KEY (dealership_id,id,stock_num)
);

CREATE INDEX vehicle_vin ON autocafe.vehicles (vin);

-- Vehicle Image Table
CREATE TABLE IF NOT EXISTS autocafe.vehicle_images (
    dealership_id uuid,
    stock_num text,
    img_url text,
    img_type text,
    img_order int,
    created timeuuid,
    PRIMARY KEY (dealership_id,stock_num)
);





