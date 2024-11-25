package com.example.vickey;

public class EpisodeTitleCountDto {
    private String title;
    private int episodeCount;

    public EpisodeTitleCountDto(String title, int episodeCount) {
        this.title = title;
        this.episodeCount = episodeCount;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}
