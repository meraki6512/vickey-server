package com.example.vickey.entity;

import com.example.vickey.LikeKey;
import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Likes")
public class Like {

    @EmbeddedId
    private LikeKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    // Getters and setters
}
