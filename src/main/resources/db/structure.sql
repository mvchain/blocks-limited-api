
drop database if exists invite;
create database invite;
use invite;

create table invite_user(
    id bigint auto_increment,
    name varchar(24),
    cellphone varchar(24),
    invite_code varchar(10),
    sub_product_count int,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

create table invite_record(
    id bigint auto_increment,
    inviter_id bigint,
    invitee_id bigint,
    product_count int,
    transaction_at timestamp,
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);
