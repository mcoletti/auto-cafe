CREATE KEYSPACE IF NOT EXISTS autocafe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};


select * from cassandra_migration_version;
select * from cassandra_migration_version_counts;
select * from clients;
delete from client_vehicles where client_id = 65026b3e-a833-4263-81a5-dedc2a6efed5 and vehicle_id = 037b81b0-ea03-497c-a16e-c95a4655bbac;
update vehicles 
set short_description = 'cool car' 
where dealership_id = ef3395f0-4bfe-4fe6-953f-866735b60474 and stock_num = 'P1000' and created = d7ac83e0-8982-11e6-9ccf-599b436bc329;

select * from vehicles;
select * from dealerships;
select * from dealership_lots;
update dealerships set header_title = 'ACC Ogden' where client_id = 1daa3920-c111-43bc-9dc3-a412e133688f and created = b9d37410-a105-11e6-8a86-2d11b24b31a7;
delete from dealerships where client_id = 0a944635-f6dc-47e6-b570-8b59af2917b4;

select * from dealerships where id =ef3395f0-4bfe-4fe6-953f-866735b60474;
delete from dealerships where client_id = 0a944635-f6dc-47e6-b570-8b59af2917b4 and created = 390d2f10-96d3-11e6-b3ae-45e004073552;
select * from dealer_vehicles;

select * from vehicles;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;

CREATE TYPE IF NOT EXISTS autocafe.make_vehicle_total (
    seq int,
    make text,
    vehicle_total int
);


CREATE TABLE IF NOT EXISTS autocafe.dealerships (
    client_id uuid,
    id uuid,
    name text,
    contacts list<frozen<contact>>,
    page_title_header text,
    img_header_logos list<text>,
    location_detail frozen<location_detail>,
    created_user text,
    created timeuuid,
    modified_user text,
    modified timeuuid,
    PRIMARY KEY (client_id,created)
);

CREATE INDEX dealership_id ON autocafe.dealerships (id);