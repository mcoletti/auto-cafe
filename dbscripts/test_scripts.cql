CREATE KEYSPACE IF NOT EXISTS autocafe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 2};


select * from cassandra_migration_version;
select * from cassandra_migration_version_counts;

delete from client_vehicles where client_id = 65026b3e-a833-4263-81a5-dedc2a6efed5 and vehicle_id = 037b81b0-ea03-497c-a16e-c95a4655bbac;

delete from dealerships where id =795ab464-2bbe-441c-aaba-aff7aab14672;
select * from dealerships where id = ad44d405-8240-4035-98c9-2b9b528b2e86;
select * from dealer_vehicles;

select * from vehicle_details;
select * from client_vehicles where client_id = 395b2f0c-8008-410c-8402-fb64a3a7a295;
select * from vehicle_images;

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