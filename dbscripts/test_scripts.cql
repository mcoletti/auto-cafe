CREATE KEYSPACE IF NOT EXISTS autocafe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};


select * from cassandra_migration_version;
select * from cassandra_migration_version_counts;

delete from client_vehicles where client_id = 65026b3e-a833-4263-81a5-dedc2a6efed5 and vehicle_id = 037b81b0-ea03-497c-a16e-c95a4655bbac;


select * from clients;
select * from client_vehicles;

select * from vehicle_details;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;

CREATE TABLE IF NOT EXISTS autocafe.clients (
    id uuid,
    client_name text,
    location frozen<location>,
    created_by text,
    created_dtm timestamp,
    modified_by text,
    modified_dtm timestamp,
    PRIMARY KEY (id,client_name)
)
WITH CLUSTERING ORDER BY (client_name ASC);

CREATE INDEX idx_client_name ON autocafe.clients (client_name);