package com.back.city.services;

import com.back.city.dto.user.UserForChatRoom;
import com.back.city.entity.UserEntity;
import com.back.city.repository.UserRepository;
import com.back.city.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    public UserEntity register (UserEntity userEntity){
        if(userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        if (userRepository.existsByUserName(userEntity.getUsername())){
            throw new RuntimeException("Пользователь с таким иминем уже существует");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


    public UserEntity getCurrentUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(userName);
    }

    public Optional<UserEntity> getUser(String userName){
        return userRepository.findByUserName(userName);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getByUsername(username);
        userEntity.setStatus(UserStatus.ONLINE);
        userRepository.save(userEntity);

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPasswordEntity().getPassword())
                .authorities(authorities)
                .build();
    }

    public void disconnectUser(UserEntity userEntity){
        userEntity.setStatus(UserStatus.ONLINE);
        userRepository.save(userEntity);
    }
    //TODO поменять на поиск по ассоциации чтобы выводились похожие варианты и возвращать список а не 1 элемент
    public UserForChatRoom findUserByUserName(String userName){
        Optional<UserEntity> user = userRepository.findByUserName(userName);
        return UserForChatRoom.builder()
                .id(user.get().getId())
                .lastName(user.get().getLastName())
                .firstName(user.get().getFirstName())
                .avatar(user.get().getProfileEntity().getUserAvatar())
                .build();
    }
}


