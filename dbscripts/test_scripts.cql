select * from cassandra_migration_version;

select * from clients;
select * from vehicle_details;
select * from vehicle_images;

select * from client_vehicles;

CREATE TABLE IF NOT EXISTS autocafe.client_locations (
    client_id uuid,
    location_id uuid,
    location frozen<location>,
    PRIMARY KEY (client_id,location_id)
);

CREATE TABLE IF NOT EXISTS autocafe.client_vehicles (
    client_id text,
    vehicle_id text,
    key_name text,
    PRIMARY KEY ((client_id),vehicle_id)
)
WITH CLUSTERING ORDER BY (vehicle_id ASC);

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