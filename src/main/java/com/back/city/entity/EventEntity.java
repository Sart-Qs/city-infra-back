package com.back.city.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo почему нельзя использовать float в точных вычислениях
    private Float[] coordinates;
    private Long userId;
    private String description;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> filesUrl;
    private Date timestomp;
//    @OneToMany
//    @JoinColumn(name = "likes")
//    private List<LikeEntity> likes;
}

//TODO подумать над вынесения медиадаты в отдельную сущность
//TODO подумать как сделать лайки
