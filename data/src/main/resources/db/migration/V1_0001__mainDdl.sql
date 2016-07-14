
CREATE SEQUENCE autocafedb.client_id_seq;

CREATE TABLE autocafedb.client (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.client_id_seq'),
                created_by VARCHAR(45) NOT NULL,
                created_dtm TIMESTAMP NOT NULL,
                modified_by VARCHAR(45) NOT NULL,
                modified_dtm TIMESTAMP NOT NULL,
                client_name VARCHAR(150) NOT NULL,
                CONSTRAINT client_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.client_id_seq OWNED BY autocafedb.client.id;

CREATE SEQUENCE autocafedb.location_id_seq;

CREATE TABLE autocafedb.location (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.location_id_seq'),
                name VARCHAR(75) NOT NULL,
                address_line1 VARCHAR(75) NOT NULL,
                address_line2 VARCHAR(75) NOT NULL,
                city VARCHAR(75) NOT NULL,
                state VARCHAR(10) NOT NULL,
                zip INTEGER NOT NULL,
                client_id INTEGER NOT NULL,
                CONSTRAINT location_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.location_id_seq OWNED BY autocafedb.location.id;

CREATE SEQUENCE autocafedb.vehicle_body_style_id_seq;

CREATE TABLE autocafedb.vehicle_body_style (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_body_style_id_seq'),
                style VARCHAR(75) NOT NULL,
                CONSTRAINT vehicle_body_style_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_body_style_id_seq OWNED BY autocafedb.vehicle_body_style.id;

CREATE SEQUENCE autocafedb.image_type_id_seq;

CREATE TABLE autocafedb.image_type (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.image_type_id_seq'),
                name VARCHAR(45) NOT NULL,
                CONSTRAINT image_type_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.image_type_id_seq OWNED BY autocafedb.image_type.id;

CREATE SEQUENCE autocafedb.vehicle_model_id_seq;

CREATE TABLE autocafedb.vehicle_model (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_model_id_seq'),
                make VARCHAR(45) NOT NULL,
                model VARCHAR(45) NOT NULL,
                CONSTRAINT vehicle_model_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_model_id_seq OWNED BY autocafedb.vehicle_model.id;

CREATE INDEX idxmake
 ON autocafedb.vehicle_model USING BTREE
 ( make ASC );

CREATE INDEX idxmodel
 ON autocafedb.vehicle_model USING BTREE
 ( model ASC );

CREATE SEQUENCE autocafedb.vehicle_category_id_seq;

CREATE TABLE autocafedb.vehicle_category (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_category_id_seq'),
                description VARCHAR(45),
                CONSTRAINT vehicle_category_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_category_id_seq OWNED BY autocafedb.vehicle_category.id;

CREATE SEQUENCE autocafedb.vehicle_year_id_seq;

CREATE TABLE autocafedb.vehicle_year (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_year_id_seq'),
                value VARCHAR(45) NOT NULL,
                CONSTRAINT vehicle_year_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_year_id_seq OWNED BY autocafedb.vehicle_year.id;

CREATE SEQUENCE autocafedb.vehicle_manufacture_id_seq;

CREATE TABLE autocafedb.vehicle_manufacture (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_manufacture_id_seq'),
                full_name VARCHAR(150) NOT NULL,
                code VARCHAR(45),
                CONSTRAINT vehicle_manufacture_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_manufacture_id_seq OWNED BY autocafedb.vehicle_manufacture.id;

CREATE UNIQUE INDEX code_unique
 ON autocafedb.vehicle_manufacture USING BTREE
 ( id ASC );

CREATE UNIQUE INDEX full_name_unique
 ON autocafedb.vehicle_manufacture USING BTREE
 ( full_name ASC );

CREATE SEQUENCE autocafedb.vehicle_inventory_id_seq;

CREATE TABLE autocafedb.vehicle_inventory (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.vehicle_inventory_id_seq'),
                created_by VARCHAR(45) NOT NULL,
                created_dtm TIMESTAMP NOT NULL,
                modified_by VARCHAR(45),
                modified_dtm TIMESTAMP,
                key_name VARCHAR(75) NOT NULL,
                stock_num VARCHAR(25) NOT NULL,
                ext_color VARCHAR(25) NOT NULL,
                int_color VARCHAR(45) NOT NULL,
                trim VARCHAR(25) NOT NULL,
                vehicle_price NUMERIC(10) DEFAULT 0 NOT NULL,
                vehicle_mileage VARCHAR(45) DEFAULT '0' NOT NULL,
                vehicle_category_id INTEGER NOT NULL,
                manufacture_id INTEGER NOT NULL,
                vehicle_model_id INTEGER NOT NULL,
                vehicle_year_id INTEGER NOT NULL,
                vehicle_body_style_id INTEGER NOT NULL,
                client_id INTEGER NOT NULL,
                location_id INTEGER NOT NULL,
                CONSTRAINT vehicle_inventory_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.vehicle_inventory_id_seq OWNED BY autocafedb.vehicle_inventory.id;

CREATE UNIQUE INDEX id_unique
 ON autocafedb.vehicle_inventory USING BTREE
 ( id ASC );

CREATE INDEX idx_price
 ON autocafedb.vehicle_inventory USING BTREE
 ( vehicle_price ASC );

CREATE INDEX fk_vehicle_inventory_vehicle_category1_idx
 ON autocafedb.vehicle_inventory USING BTREE
 ( vehicle_category_id ASC );

CREATE INDEX fk_vehicle_inventory_manufacture1_idx
 ON autocafedb.vehicle_inventory USING BTREE
 ( manufacture_id ASC );

CREATE INDEX fk_vehicle_inventory_vehicle_model1_idx
 ON autocafedb.vehicle_inventory USING BTREE
 ( vehicle_model_id ASC );

CREATE INDEX fk_vehicle_inventory_vehicle_year1_idx
 ON autocafedb.vehicle_inventory USING BTREE
 ( vehicle_year_id ASC );

CREATE SEQUENCE autocafedb.catalog_images_id_seq;

CREATE TABLE autocafedb.catalog_images (
                id INTEGER NOT NULL DEFAULT nextval('autocafedb.catalog_images_id_seq'),
                cdn_url_loc VARCHAR(150) NOT NULL,
                image_type_id INTEGER NOT NULL,
                vehicle_inventory_id INTEGER NOT NULL,
                CONSTRAINT catalog_images_pk PRIMARY KEY (id)
);


ALTER SEQUENCE autocafedb.catalog_images_id_seq OWNED BY autocafedb.catalog_images.id;

CREATE INDEX fk_catalog_images_image_type1_idx
 ON autocafedb.catalog_images USING BTREE
 ( image_type_id ASC );

CREATE INDEX fk_catalog_images_vehicle_inventory1_idx
 ON autocafedb.catalog_images USING BTREE
 ( vehicle_inventory_id ASC );

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT client_vehicle_inventory_fk
FOREIGN KEY (client_id)
REFERENCES autocafedb.client (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.location ADD CONSTRAINT client_location_fk
FOREIGN KEY (client_id)
REFERENCES autocafedb.client (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT location_vehicle_inventory_fk
FOREIGN KEY (location_id)
REFERENCES autocafedb.location (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT vehicle_body_style_vehicle_inventory_fk
FOREIGN KEY (vehicle_body_style_id)
REFERENCES autocafedb.vehicle_body_style (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.catalog_images ADD CONSTRAINT fk_catalog_images_image_type1
FOREIGN KEY (image_type_id)
REFERENCES autocafedb.image_type (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT fk_vehicle_inventory_vehicle_model1
FOREIGN KEY (vehicle_model_id)
REFERENCES autocafedb.vehicle_model (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT fk_vehicle_inventory_vehicle_category1
FOREIGN KEY (vehicle_category_id)
REFERENCES autocafedb.vehicle_category (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT fk_vehicle_inventory_vehicle_year1
FOREIGN KEY (vehicle_year_id)
REFERENCES autocafedb.vehicle_year (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.vehicle_inventory ADD CONSTRAINT fk_vehicle_inventory_manufacture1
FOREIGN KEY (manufacture_id)
REFERENCES autocafedb.vehicle_manufacture (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE autocafedb.catalog_images ADD CONSTRAINT fk_catalog_images_vehicle_inventory1
FOREIGN KEY (vehicle_inventory_id)
REFERENCES autocafedb.vehicle_inventory (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
