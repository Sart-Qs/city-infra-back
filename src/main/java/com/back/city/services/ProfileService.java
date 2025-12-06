package com.back.city.services;

import com.back.city.dto.user.UserProfileDTO;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import com.back.city.repository.ProfileRepository;
import com.back.city.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public UserProfileDTO getUserProfile(Long id){
        UserEntity userData = userRepository.getById(id);
        ProfileEntity profileData = profileRepository.getById(userData.getProfileEntity().getId());
        return UserProfileDTO.builder()
                .id(profileData.getId())
                .email(userData.getEmail())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .aboutSelf(profileData.getAboutSelf())
                .location(profileData.getLocation())
                .avatar(profileData.getUserAvatar())
                .build();
    }

}
