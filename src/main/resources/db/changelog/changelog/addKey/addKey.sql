
alter table chat_member
add constraint fk_chat_room_id
foreign key (chat_id)
references chat_room(id);

alter table chat_member
add constraint fk_user_id
foreign key (user_id)
references users(id);

alter table users
add constraint fk_password_id
foreign key (password_id)
references password(id);

alter table users
add constraint fk_profile_id
foreign key (profile_id)
references profile(id);

alter table message
add constraint fk_media_data_id
foreign key (media_data_id)
references media_data(id)