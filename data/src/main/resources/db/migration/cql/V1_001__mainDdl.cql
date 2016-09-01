
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
CREATE TABLE IF NOT EXISTS autocafe.dealerships (
    id uuid,
    name text,
    locations Map<text,frozen<location>>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY ((id,name),created_dtm)
)
WITH CLUSTERING ORDER BY (created_dtm ASC);

-- Client Vehicles
CREATE TABLE IF NOT EXISTS autocafe.dealer_vehicles (
    dealer_id uuid,
    stock_num text,
    short_desc text,
    description text,
    price double,
    location frozen<location>,
    details frozen<vehicle_detail>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (dealer_id,stock_num)
);

-- Vehicle Image Table
CREATE TABLE IF NOT EXISTS autocafe.vehicle_images (
    dealer_id uuid,
    vehicle_id uuid,
    img_name text,
    main_img boolean,
    img_cdn_loc text,
    img_size int,
    img_suffix text,
    img_type text,
    img_order int,
    PRIMARY KEY (dealer_id,vehicle_id)
);



