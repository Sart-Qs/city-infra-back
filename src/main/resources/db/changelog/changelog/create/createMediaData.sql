
create table media_data(
    id serial primary key not null,
    media varchar not null,
    unique (id)
)