
drop database if exists invite;
create database invite;
use invite;

drop table is exists invite_user;
create table invite_user(
    id bigint auto_increment,
    name varchar(24) not null,
    cellphone varchar(24) not null unique,
    invite_code varchar(10) unique,
    invite_count int default 0,
    address varchar(300) default '',
    ether_address varchar(256),
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists invite_record;
create table invite_record(
    id bigint auto_increment,
    invite_code varchar(10),
    quantity int,
    unique_code varchar(64),
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists auth;
create table auth(
    id bigint primary key auto_increment,
    type varchar(16) not null,
    password varchar(64) not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists ks_order;
create table ks_order(
    id bigint primary key auto_increment,
    name varchar(24) not null,
    cellphone varchar(24) not null,
    invite_code varchar(10),
    address varchar(300) default '',
    quantity int,
    sum int,
    pay_channel int,
    pay_account varchar(256),
    comment varchar(1000),
    status tinyint default 0,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

