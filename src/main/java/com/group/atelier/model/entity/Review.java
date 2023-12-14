package com.group.atelier.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "rating")
    private Short rating;

    @ManyToOne
    @JoinColumn(name = "coat_model_id")
    private CoatModel coatModel;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
