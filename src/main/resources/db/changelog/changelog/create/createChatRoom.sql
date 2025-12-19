
CREATE TABLE chat_room(
    id serial not null primary key,
    chat_name varchar,
    last_message varchar,
    chat_avatar varchar,
    unique (id)
)