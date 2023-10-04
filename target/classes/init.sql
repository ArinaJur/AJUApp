CREATE SCHEMA IF NOT EXISTS university;
USE university;

CREATE TABLE IF NOT EXISTS tbl_person (
    person_id BIGINT PRIMARY KEY,
    firstName VARCHAR(80),
    lastName VARCHAR(100),
    userName VARCHAR(115),
    password VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS tbl_admin (
    admin_id BIGINT PRIMARY KEY,
    person_id BIGINT,
    id VARCHAR(8)
);