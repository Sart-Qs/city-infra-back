
create table message(
    id serial PRIMARY KEY NOT NULL,
    chat_id bigint not null,
    user_id bigint not null,
    content varchar,
    media_data_id bigint,
    send_time TIMESTAMP WITH TIME ZONE NOT NULL,
    unique (id)
)