package com.group.atelier.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReviewReply {
    @Id
    @Column(name = "review_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "review_id")
    @MapsId
    private Review review;
}
