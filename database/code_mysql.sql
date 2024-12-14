-- SQLINES FOR EVALUATION USE ONLY (14 DAYS)
CREATE TABLE wxy_invoice (
    invoice_id    INT AUTO_INCREMENT PRIMARY KEY,
    invoice_state CHAR(1) NOT NULL,
    trip_order_id INT NOT NULL
);

CREATE UNIQUE INDEX wxy_invoice__idx ON
    wxy_invoice (
        trip_order_id
    ASC );

-- ALTER TABLE wxy_invoice ADD CONSTRAINT wxy_invoice_pk PRIMARY KEY ( invoice_id );

CREATE TABLE wxy_station (
    station_id   INT AUTO_INCREMENT PRIMARY KEY,
    station_name VARCHAR(50) NOT NULL
);

-- ALTER TABLE wxy_station ADD CONSTRAINT wxy_station_pk PRIMARY KEY ( station_id );

CREATE TABLE wxy_train (
    train_id   INT AUTO_INCREMENT PRIMARY KEY,
    train_name VARCHAR(50) NOT NULL,
    seats_num  INT
);

-- ALTER TABLE wxy_train ADD CONSTRAINT wxy_train_pk PRIMARY KEY ( train_id );

CREATE TABLE wxy_train_trip (
    seats_left INT NOT NULL,
    train_id   INT NOT NULL,
    trip_id    INT NOT NULL
);

CREATE TABLE wxy_trip (
    trip_id   INT AUTO_INCREMENT PRIMARY KEY,
    trip_date DATETIME NOT NULL
);

-- ALTER TABLE wxy_trip ADD CONSTRAINT wxy_trip_pk PRIMARY KEY ( trip_id );

CREATE TABLE wxy_trip_station (
    station_type VARCHAR(50) NOT NULL,
    start_date   DATETIME NOT NULL,
    end_date     DATETIME NOT NULL,
    trip_id      INT NOT NULL,
    station_id   INT NOT NULL
);

ALTER TABLE wxy_trip_station
    ADD CHECK ( station_type IN ( 'END', 'START', 'STOP' ) );

CREATE TABLE wxy_user (
    user_id     INT AUTO_INCREMENT PRIMARY KEY,
    user_name   VARCHAR(50) ,
    password    VARCHAR(50) NOT NULL,
    fname       VARCHAR(50) ,
    lname       VARCHAR(50) ,
    birth_date  DATETIME ,
    gender      VARCHAR(50) ,
    nationality VARCHAR(50) ,
    email       VARCHAR(50) ,
    phone       VARCHAR(50) 
);

-- ALTER TABLE wxy_user ADD CONSTRAINT wxy_user_pk PRIMARY KEY ( user_id );

CREATE TABLE wxy_user_trip (
    user_id  INT NOT NULL,
    trip_id  INT NOT NULL,
    order_id INT NOT NULL
);

ALTER TABLE wxy_user_trip ADD CONSTRAINT wxy_user_trip_pk PRIMARY KEY ( order_id );

ALTER TABLE wxy_invoice
    ADD CONSTRAINT wxy_invoice_wxy_user_trip_fk FOREIGN KEY ( trip_order_id )
        REFERENCES wxy_user_trip ( order_id );

ALTER TABLE wxy_train_trip
    ADD CONSTRAINT wxy_train_trip_wxy_train_fk FOREIGN KEY ( train_id )
        REFERENCES wxy_train ( train_id );

ALTER TABLE wxy_train_trip
    ADD CONSTRAINT wxy_train_trip_wxy_trip_fk FOREIGN KEY ( trip_id )
        REFERENCES wxy_trip ( trip_id );

ALTER TABLE wxy_trip_station
    ADD CONSTRAINT wxy_trip_station_fk FOREIGN KEY ( station_id )
        REFERENCES wxy_station ( station_id );

ALTER TABLE wxy_trip_station
    ADD CONSTRAINT wxy_trip_station_wxy_trip_fk FOREIGN KEY ( trip_id )
        REFERENCES wxy_trip ( trip_id );

ALTER TABLE wxy_user_trip
    ADD CONSTRAINT wxy_user_trip_wxy_trip_fk FOREIGN KEY ( trip_id )
        REFERENCES wxy_trip ( trip_id );

ALTER TABLE wxy_user_trip
    ADD CONSTRAINT wxy_user_trip_wxy_user_fk FOREIGN KEY ( user_id )
        REFERENCES wxy_user ( user_id );