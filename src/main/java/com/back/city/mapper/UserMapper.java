package com.back.city.mapper;

import com.back.city.dto.user.UserDTO;
import com.back.city.dto.user.UserForChatRoom;
import com.back.city.dto.user.UserProfileDTO;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

//TODO добавить маппер для FindUserResponse
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toUserDTO(UserEntity user);
    List<UserDTO> toUserDTO(List<UserEntity> users);
    default UserProfileDTO toUserProfileDTO(UserEntity user){
        ProfileEntity profile = user.getProfileEntity();
        return UserProfileDTO.builder()
                .id(profile.getId())
                .location(profile.getLocation())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(profile.getUserAvatar())
                .aboutSelf(profile.getAboutSelf())
                .build();
    }

    UserForChatRoom toUserForChatRoom(UserEntity user);
}
