package com.back.city.mapper;

import com.back.city.dto.auth.SingUpRequest;
import com.back.city.dto.user.UserDTO;
import com.back.city.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-17T02:49:33+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 25.0.1 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> toUserDTO(List<UserEntity> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( UserEntity userEntity : users ) {
            list.add( toUserDTO( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity toUserEntity(SingUpRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        return userEntity;
    }
}
