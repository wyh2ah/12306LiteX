-- 生成者Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   时间:        2024-12-13 01:59:42 EST
--   站点:      Oracle Database 11g
--   类型:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE wxy_invoice (
    invoice_id    NUMBER(8) NOT NULL,
    invoice_state CHAR(1) NOT NULL,
    trip_order_id NUMBER(8) NOT NULL
);

CREATE UNIQUE INDEX wxy_invoice__idx ON
    wxy_invoice (
        trip_order_id
    ASC );

ALTER TABLE wxy_invoice ADD CONSTRAINT wxy_invoice_pk PRIMARY KEY ( invoice_id );

CREATE TABLE wxy_station (
    station_id   NUMBER(8) NOT NULL,
    station_name VARCHAR2(50) NOT NULL
);

ALTER TABLE wxy_station ADD CONSTRAINT wxy_station_pk PRIMARY KEY ( station_id );

CREATE TABLE wxy_train (
    train_id   NUMBER(8) NOT NULL,
    train_name VARCHAR2(50) NOT NULL,
    seats_num  NUMBER(8)
);

ALTER TABLE wxy_train ADD CONSTRAINT wxy_train_pk PRIMARY KEY ( train_id );

CREATE TABLE wxy_train_trip (
    seats_left NUMBER(8) NOT NULL,
    train_id   NUMBER(8) NOT NULL,
    trip_id    NUMBER(8) NOT NULL
);

CREATE TABLE wxy_trip (
    trip_id   NUMBER(8) NOT NULL,
    trip_date DATE NOT NULL
);

ALTER TABLE wxy_trip ADD CONSTRAINT wxy_trip_pk PRIMARY KEY ( trip_id );

CREATE TABLE wxy_trip_station (
    station_type VARCHAR2(50) NOT NULL,
    start_date   DATE NOT NULL,
    end_date     DATE NOT NULL,
    trip_id      NUMBER(8) NOT NULL,
    station_id   NUMBER(8) NOT NULL
);

ALTER TABLE wxy_trip_station
    ADD CHECK ( station_type IN ( 'END', 'START', 'STOP' ) );

CREATE TABLE wxy_user (
    user_id     NUMBER(8) NOT NULL,
    user_name   VARCHAR2(50) NOT NULL,
    password    VARCHAR2(50) NOT NULL,
    fname       VARCHAR2(50) NOT NULL,
    lname       VARCHAR2(50) NOT NULL,
    birth_date  DATE NOT NULL,
    gender      VARCHAR2(50) NOT NULL,
    nationality VARCHAR2(50) NOT NULL,
    email       VARCHAR2(50) NOT NULL,
    phone       VARCHAR2(50) NOT NULL
);

ALTER TABLE wxy_user ADD CONSTRAINT wxy_user_pk PRIMARY KEY ( user_id );

CREATE TABLE wxy_user_trip (
    user_id  NUMBER(8) NOT NULL,
    trip_id  NUMBER(8) NOT NULL,
    order_id NUMBER(8) NOT NULL
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



-- Oracle SQL Developer Data Modeler 概要报告: 
-- 
-- CREATE TABLE                             8
-- CREATE INDEX                             1
-- ALTER TABLE                             14
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
