create sequence user_id_seq start with 100 increment by 50;

create table users
(
    id         bigint       not null default nextval('user_id_seq'),
    email      varchar(255) not null,
    password   varchar(255) not null,
    name       varchar(255) not null,
    role       varchar(20)  not null,
    created_at timestamp    not null,
    updated_at timestamp,
    primary key (id),
    constraint user_email_unique unique (email)
);

create sequence post_id_seq start with 100 increment by 50;

create table posts
(
    id         bigint       not null default nextval('post_id_seq'),
    uid        varchar(300) not null,
    content    text         not null,
    created_by bigint       not null references users (id),
    created_at timestamp    not null,
    updated_at timestamp,
    primary key (id),
    constraint posts_uid_unique unique (uid)
);
