
create table profile(
    id serial primary key not null,
    user_avatar varchar not null,
    about_self varchar not null,
    location varchar not null,
    unique (id)
)