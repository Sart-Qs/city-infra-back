package com.back.city.services;

import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Component
@AllArgsConstructor
public class JwtHandshakeInterceptor extends DefaultHandshakeHandler {
    private final JwtService jwtService;
    private final UserService userService;
    public static final String HEADER_NAME = "Authorization";

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        var token = request.getHeaders().get(HEADER_NAME);
        if (token != null && !token.isEmpty()){
            String userName = jwtService.extractUserName(String.valueOf(token));
            if(userName != null){
                UserDetails userDetails = userService.loadUserByUsername(userName);
                if(userDetails != null && jwtService.isTokenValid(String.valueOf(token), userDetails)){
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            }
        }
        return super.determineUser(request, wsHandler, attributes);
    }
}
