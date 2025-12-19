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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;

    @Transactional
    public UserEntity register (UserEntity userEntity){
        if(userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        if (userRepository.existsByUserName(userEntity.getUsername())){
            throw new RuntimeException("Пользователь с таким иминем уже существует");
        }

        return userRepository.save(userEntity);
    }

    @Transactional
    public UserEntity getByUsername(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }


    public UserEntity getCurrentUser() {
        var userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(userName);
    }

    @Transactional
    public Optional<UserEntity> getUser(String userName){
        return userRepository.findByUserName(userName);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getByUsername(username);
        userEntity.setStatus(UserStatus.ONLINE);
        userRepository.save(userEntity);
        //TODO добавить нормальные роли
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPasswordEntity().getPassword())
                .authorities(authorities)
                .build();
    }

    @Transactional
    public void disconnectUser(UserEntity userEntity){
        userEntity.setStatus(UserStatus.ONLINE);
        userRepository.save(userEntity);
    }


    //TODO поменять на поиск по ассоциации чтобы выводились похожие варианты и возвращать список а не 1 элемент
    //TODO Убрать это дто и сделать маппер под обычный UserDTO
    @Transactional
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


