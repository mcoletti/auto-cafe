CREATE KEYSPACE IF NOT EXISTS autocafe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};


select * from cassandra_migration_version;
select * from cassandra_migration_version_counts;
select * from clients;
delete from client_vehicles where client_id = 65026b3e-a833-4263-81a5-dedc2a6efed5 and vehicle_id = 037b81b0-ea03-497c-a16e-c95a4655bbac;
update vehicles 
set short_description = 'cool car' 
where dealership_id = ef3395f0-4bfe-4fe6-953f-866735b60474 and stock_num = 'P1000' and created = d7ac83e0-8982-11e6-9ccf-599b436bc329;

select * from vehicles;
update vehicles set img_url = '/Users/micahcoletti/sandbox/auto-cafe/file_store/acc1.jpg' where dealership_id = 0cc60b64-01d7-4efa-a0c4-88737e4ef301 and id = 03f2e228-44b7-4aee-ae3f-e4a4654276bb;

delete from dealerships where id = afba859d-d1c6-463e-bc7e-4b211c813da7 and client_id = 1daa3920-c111-43bc-9dc3-a412e133688f;
select * from dealerships;
select * from dealership_lots;
update dealerships set make_vehicle_totals = {'Acura': 0, 'BMW': 0, 'Honda': 0} where client_id = f9ec7552-6380-4d0c-ae5f-9492a7d9ffb4 and created = 7e0dc620-a5be-11e6-b584-0999e3fa498b and id = 69a2ba78-a3fd-4919-b098-fbfe59cc4c07;
delete from dealerships where client_id = 0a944635-f6dc-47e6-b570-8b59af2917b4;

select * from dealerships where id =ef3395f0-4bfe-4fe6-953f-866735b60474;
delete from dealerships where client_id = 0a944635-f6dc-47e6-b570-8b59af2917b4 and created = 390d2f10-96d3-11e6-b3ae-45e004073552;
select * from dealer_vehicles;

delete  from vehicles where dealership_id = 1dbcec8a-4dcd-49ce-af21-b4ed904ee416;
select * from vehicles where id =806e71da-d79b-4d11-b428-0a2e90c3e8d1 and dealership_id =0cc60b64-01d7-4efa-a0c4-88737e4ef301;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;

CREATE TYPE IF NOT EXISTS autocafe.make_vehicle_total (
    seq int,
    make text,
    vehicle_total int
);


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
    PRIMARY KEY (dealership_id,id)
);

CREATE INDEX vehicle_vin ON autocafe.vehicles (vin);
CREATE INDEX vehicle_stockNum ON autocafe.vehicles (stock_num);




