package com.example.vickey.entity;

import com.example.vickey.CheckWatchedKey;
import com.example.vickey.entity.User;
import com.example.vickey.entity.Video;
import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "Check_watched")
public class CheckWatched {

    @EmbeddedId
    private CheckWatchedKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(nullable = false)
    private Integer progress;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp watchedDate;

    // Getters and setters
}
