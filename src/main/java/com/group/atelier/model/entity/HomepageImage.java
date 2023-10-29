package com.group.atelier.model.entity;

import com.group.atelier.model.CoatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "homepage_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomepageImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "coat_type")
    @Enumerated(value = EnumType.STRING)
    private CoatType coatType;

    @Column(name = "video_url")
    private String videoUrl;
}
