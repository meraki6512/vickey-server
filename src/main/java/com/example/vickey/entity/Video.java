package com.example.vickey.entity;

import com.example.vickey.repository.EpisodeRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "Videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long videoId;

    @ManyToOne
    @JoinColumn(name = "episode_id", nullable = false) //외래키 설정
    private Episode episode;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private int episodeNum;

    // Getters 및 Setters

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public void setEpisodeId(Long episodeId, EpisodeRepository episodeRepository) {
        this.episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Episode ID: " + episodeId));
    }

    public Long getEpisodeId() {
        return this.episode != null ? this.episode.getEpisodeId() : null;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public void setEpisodeNum(int episodeNum) {
        this.episodeNum = episodeNum;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
