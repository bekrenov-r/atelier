package com.group.atelier.model.entity;

import com.group.atelier.model.enums.CoatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coat_model")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "img_path")
    private String imgPath;

    @Column(name = "coat_type")
    @Enumerated(value = EnumType.STRING)
    private CoatType coatType;

    @Column(name = "video_url")
    private String videoUrl;
}
