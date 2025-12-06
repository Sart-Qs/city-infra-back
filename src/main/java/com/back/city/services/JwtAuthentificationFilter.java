package com.back.city.services;


import com.back.city.entity.PasswordEntity;
import com.back.city.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Security;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private boolean isPublicEndpoint(String requestURI) {
        return requestURI.startsWith("/api/login") ||
                requestURI.startsWith("/api/register");
    }

    public static final String BEARER_PREFIX = "Bearer";
    public static final String HEADER_NAME = "Authorization";
    public final JwtService jwtService;
    public final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String requestURI = request.getRequestURI();
            logger.info("=== JWT FILTER START ===");
            logger.info("Request URI: " + request.getRequestURI());
            logger.info("Request Method: " + request.getMethod());
            logger.info("Query String: " + request.getQueryString());
            logger.info("Content Type: " + request.getContentType());
            logger.info("Content Length: " + request.getContentLength());

            var authHeader = request.getHeader(HEADER_NAME);
            logger.info("Authorization Header: " + authHeader);

            if (isPublicEndpoint(requestURI)) {
                logger.info("Public endpoint, skipping JWT check: " + requestURI);
                filterChain.doFilter(request, response);
                return;
            }


            if ((StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX))) {
                logger.info("No valid Authorization header, continuing...");
                filterChain.doFilter(request, response);
                return;
            }

            var jwt = authHeader.substring(BEARER_PREFIX.length());
            var userName = jwtService.extractUserName(jwt);
            logger.info("userName: " + userName);


            if (StringUtils.isNotEmpty(userName) && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.info("User not authenticated, validating token...");

                UserDetails userDetails = userService.loadUserByUsername(userName);

                if (userDetails != null && jwtService.isTokenValid(jwt, userDetails)) {
                    var authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    logger.info("User authenticated via JWT: " + userName);
                } else {
                    logger.warn("Invalid token or user not found: " + userName);
                }
            }


        } catch (Exception e) {
            logger.error("JWT filter error: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
        logger.info("=== JWT FILTER END ===");
    }

}


