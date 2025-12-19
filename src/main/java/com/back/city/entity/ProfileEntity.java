package com.back.city.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "profile")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userAvatar;
    private String aboutSelf;
    private String location;

    public ProfileEntity(){
        this.userAvatar = "aboba";
        this.aboutSelf = "Здесь пока что ничего нет";
        this.location = "Воронеж, Россия";
    }
}

//TODO поменять путь до стандартного аватара
