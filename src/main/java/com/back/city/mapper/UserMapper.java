package com.back.city.mapper;

import com.back.city.dto.auth.SingUpRequest;
import com.back.city.dto.user.UserDTO;
import com.back.city.dto.user.UserForChatRoom;
import com.back.city.dto.user.UserProfileDTO;
import com.back.city.entity.EventEntity;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    default UserDTO toUserDTO(UserEntity user){
        return UserDTO.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .build();
    }
    List<UserDTO> toUserDTO(List<UserEntity> users);
    default UserProfileDTO toUserProfileDTO(UserEntity user , List<EventEntity> events){
        ProfileEntity profile = user.getProfileEntity();
        return UserProfileDTO.builder()
                .id(profile.getId())
                .location(profile.getLocation())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatar(profile.getUserAvatar())
                .aboutSelf(profile.getAboutSelf())
                .events(events)
                .build();
    }

    default UserForChatRoom toUserForChatRoom(UserEntity user){
        return UserForChatRoom.builder()
                .id(user.getId())
                .avatar(user.getProfileEntity().getUserAvatar())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }


    UserEntity toUserEntity(SingUpRequest request);


}
