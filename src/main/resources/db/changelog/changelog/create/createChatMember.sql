
create table chat_member(
    id serial primary key not null,
    chat_id bigint NOT NULL,
    user_id bigint NOT NULL,
    unique (chat_id, user_id)
)