
create table events(
    id serial primary key not null,
    coordinates real[] not null,
    user_id bigint not null,
    description varchar not null,
    media_data bigint not null,
    unique (id)
)