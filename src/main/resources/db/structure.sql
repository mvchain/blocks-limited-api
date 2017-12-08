
drop database if exists invite;
create database invite;
use invite;

create table invite_user(
    id bigint auto_increment,
    name varchar(24) not null unique,
    cellphone varchar(24) not null unique,
    invite_code varchar(10) unique,
    invite_count int default 0,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table invite_record(
    id bigint auto_increment,
    inviter_id bigint,
    invitee_id bigint,
    product_count int,
    unique_code varchar(64),
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table auth(
    id bigint primary key auto_increment,
    type varchar(16) not null,
    password varchar(64) not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;