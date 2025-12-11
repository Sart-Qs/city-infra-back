package com.back.city.services;

import com.back.city.dto.user.UserProfileDTO;
import com.back.city.entity.EventEntity;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import com.back.city.mapper.UserMapper;
import com.back.city.repository.EventRepository;
import com.back.city.repository.ProfileRepository;
import com.back.city.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final EventRepository eventRepository;
    private final UserMapper userMapper;

    public UserProfileDTO getUserProfile(Long id){
        UserEntity userData = userRepository.getById(id);
        List<EventEntity> events = eventRepository.findAllEventsByUserId(id);
        return userMapper.toUserProfileDTO(userData, events);
    }

}
