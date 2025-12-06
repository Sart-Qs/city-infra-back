package com.back.city.initializator;

import com.back.city.entity.PasswordEntity;
import com.back.city.entity.ProfileEntity;
import com.back.city.entity.UserEntity;
import com.back.city.repository.UserRepository;
import com.back.city.list.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private UserRepository userRepository;

    public void initial(){
        userRepository.save(new UserEntity(null,"dsa", "ewq", "ewq", "@w1", new PasswordEntity("123"), new ProfileEntity(), UserStatus.OFFLINE));
        userRepository.save(new UserEntity(null,"qweq", "dsagvb", "eqwds", "@asdcxx", new PasswordEntity("123"), new ProfileEntity(), UserStatus.OFFLINE));
    }
}
