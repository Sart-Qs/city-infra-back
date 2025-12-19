
create table password(
    id serial primary key not null,
    password varchar not null,
    unique (id)
)