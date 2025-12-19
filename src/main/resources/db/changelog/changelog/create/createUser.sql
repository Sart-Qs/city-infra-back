
create table users(
    id serial primary key not null,
    user_name varchar not null,
    first_name varchar not null,
    last_name varchar not null,
    email varchar not null,
    password_id bigint not null,
    profile_id bigint not null,
    status varchar not null,
    unique (id,password_id, profile_id)
)