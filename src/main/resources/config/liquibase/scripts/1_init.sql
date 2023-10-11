--liquibase formatted sql
--changeset runInTransaction:false

create table clicker_round
(
    id      bigint not null auto_increment,
    date    datetime(6),
    score   integer,
    user_id varchar(255),
    constraint clicker_round_pk primary key (id)

);
create table cup
(
    id      bigint not null auto_increment,
    user_id varchar(255),
    constraint cup_pk primary key (id)
);
create table personal_qr
(
    id          integer not null auto_increment,
    code        bigint,
    create_date datetime(6),
    data        tinyblob,
    user_id     varchar(255),
    constraint personal_qr_pk primary key (id)
);
create table role
(
    id   integer not null auto_increment,
    name varchar(255),
    constraint role_pk primary key (id)
);
create table token
(
    id          bigint not null auto_increment,
    create_date datetime(6),
    dead_date   datetime(6),
    value       varchar(255),
    constraint token_pk primary key (id)
);
create table user
(
    id          varchar(255) not null,
    create_date datetime(6),
    email       varchar(255),
    gifts       integer,
    password    varchar(255),
    qr_url      varchar(255),
    role        varchar(255),
    token       varchar(255),
    username    varchar(255),
    constraint user_pk primary key (id)
);
create table user_cups
(
    user_id varchar(255) not null,
    cup_id    bigint       not null
);

alter table user_cups
    add constraint fk_user_cup unique (cup_id);
alter table clicker_round
    add constraint fk_clicker_user foreign key (user_id) references user (id);
alter table cup
    add constraint fk_cup_user foreign key (user_id) references user (id);
alter table personal_qr
    add constraint fk_qr_user foreign key (user_id) references user (id);
alter table user_cups
    add constraint fk_user_cup_user foreign key (cup_id) references cup (id);
alter table user_cups
    add constraint fk_user_cup_cup foreign key (user_id) references user (id)