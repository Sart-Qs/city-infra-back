package com.back.city.repository;

import com.back.city.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findByEmail(String email);
    UserEntity findUserById (Long id);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);

}
