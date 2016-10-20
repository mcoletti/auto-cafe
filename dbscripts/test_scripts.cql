CREATE KEYSPACE IF NOT EXISTS autocafe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};


select * from cassandra_migration_version;
select * from cassandra_migration_version_counts;
select * from clients where id = 0a944635-f6dc-47e6-b570-8b59af2917b4;
delete from client_vehicles where client_id = 65026b3e-a833-4263-81a5-dedc2a6efed5 and vehicle_id = 037b81b0-ea03-497c-a16e-c95a4655bbac;
select * from vehicles where stock_num = '3908C' ALLOW FILTERING;
select * from dealership_lots;
select * from dealerships where id = e9c6dfea-cc89-41ea-89f0-5ba5e2a0b816;
select * from dealer_vehicles;

select * from vehicles where stock_num = 'E630A' ALLOW FILTERING;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;

CREATE TABLE IF NOT EXISTS autocafe.dealer_vehicles (
    dealer_id uuid,
    stock_num text,
    short_desc text,
    description text,
    price double,
    lotLocation frozen<lotLocation>,
    details frozen<vehicle_detail>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (dealer_id,stock_num)
);

INSERT INTO dealerships (client_id,id,created,created_user,lotLocation,modified,modified_user,name)
VALUES (590ead73-1c84-44af-9a36-3cafdd8ce700,b898c2a0-0639-4237-9a4f-05a228b465ca,50554d6e-29bb-11e5-b345-feff819cdc9f,'testUser','cool location23',50554d6e-29bb-11e5-b345-feff819cdc9f,'testUser','client1');

CREATE TABLE IF NOT EXISTS autocafe.dealerships (
    client_id uuid,
    id uuid,
    name text,
    contacts list<frozen<contact>>,
    created_user text,
    created timeuuid,
    modified_user text,
    modified timeuuid,
    PRIMARY KEY (client_id,created)
);


CREATE INDEX dealership_id ON autocafe.dealerships (id);

