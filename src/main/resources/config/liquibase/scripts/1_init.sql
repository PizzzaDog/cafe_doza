--liquibase formatted sql
--changeset runInTransaction:false

create table clicker_round
(
    id      bigint not null auto_increment,
    date    datetime(6),
    score   integer,
    user_id bigint,
    constraint clicker_round_pk primary key (id)

);
create table cup
(
    id      bigint not null auto_increment,
    user_id bigint,
    constraint cup_pk primary key (id)
);
create table personal_qr
(
    id          integer not null auto_increment,
    code        bigint,
    create_date datetime(6),
    data        longblob,
    user_id     bigint,
    url         varchar(255),
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
    user_id     bigint,
    is_alive    boolean default true,
    constraint token_pk primary key (id)
);
create table user
(
    id          bigint auto_increment,
    create_date datetime(6),
    email       varchar(255) unique,
    gifts       integer,
    password    varchar(255),
    qr_url      varchar(255),
    qr_id       integer,
    role_id     integer,
    token       varchar(255),
    username    varchar(255) unique,
    constraint user_pk primary key (id)
);
create table user_cups
(
    user_id   bigint       not null,
    cup_id    bigint       not null
);

alter table user_cups
    add constraint fk_user_cup unique (cup_id);
alter table clicker_round
    add constraint fk_clicker_user foreign key (user_id) references user (id);
alter table cup
    add constraint fk_cup_user foreign key (user_id) references user (id);
alter table user
    add constraint fk_user_role foreign key (role_id) references role (id);
alter table personal_qr
    add constraint fk_qr_user foreign key (user_id) references user (id);
alter table token
    add constraint fk_token_user foreign key (user_id) references user (id);
alter table user_cups
    add constraint fk_user_cup_user foreign key (cup_id) references cup (id);
alter table user_cups
    add constraint fk_user_cup_cup foreign key (user_id) references user (id);