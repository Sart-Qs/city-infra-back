package com.back.city.services;

import com.back.city.dto.chat.OnlineNotify;
import com.back.city.dto.auth.JwtAuthResponse;
import com.back.city.dto.auth.SingInRequest;
import com.back.city.dto.auth.SingUpRequest;
import com.back.city.entity.PasswordEntity;
import com.back.city.entity.UserEntity;
import com.back.city.mapper.UserMapper;
import com.back.city.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;


    public JwtAuthResponse singIn(SingInRequest request) throws UsernameNotFoundException{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
        ));

        var user = userService.loadUserByUsername(request.getUserName());
        var jwt = jwtService.generateToken(user);


        return new JwtAuthResponse(jwt);
    }


    public JwtAuthResponse singUp(SingUpRequest request){
        UserEntity newUser = userMapper.toUserEntity(request);
        userRepository.save(newUser);
        var jwt = jwtService.generateToken(newUser);
        return new JwtAuthResponse(jwt);
    }


    public void notifyUserOnline(SingInRequest request){
        Optional<UserEntity> user = userRepository.findByUserName(request.getUserName());
        OnlineNotify onlineNotify = new OnlineNotify();
        onlineNotify.setUserId(user.get().getId());
        onlineNotify.setUserName(user.get().getUsername());
        messagingTemplate.convertAndSend("/topic/user.online", onlineNotify);
    }
}
