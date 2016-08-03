select * from cassandra_migration_version;

select * from clients;
select * from vehicle_details;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;



CREATE TABLE IF NOT EXISTS autocafe.client_vehicles (
    client_id uuid,
    vehicle_id uuid,
    key_name text,
    created_uuid timeuuid,
    PRIMARY KEY (client_id,created_uuid)
)
WITH CLUSTERING ORDER BY (created_uuid DESC);

INSERT INTO client_vehicles(client_id,vehicle_id,key_name) VALUES (395b2f0c-8008-410c-8402-fb64a3a7a295,85bf6c0e-b5fd-4e27-8ee0-06adf891990b,'micah');