package com.back.city.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "password")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordEntity {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public PasswordEntity(String password){
        this.password = passwordEncoder.encode(password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    @JsonIgnore
    private void setPasswordEncoder(String password){
        this.password = passwordEncoder.encode(password);
    }

}
