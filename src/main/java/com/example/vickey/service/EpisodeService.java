package com.example.vickey.service;

import com.example.vickey.entity.Episode;
import com.example.vickey.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//EpisodeRepository에서 제공하는 메소드만 호출하고, 비즈니스 로직 처리
@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

//    public List<EpisodeTitleCountDto> getEpisodeTitlesAndCounts() {
//        return episodeRepository.findTitleAndEpisodeCount();
//    }

    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
    }

    public List<String> getEpisodeThumbnails() {
//        return episodeRepository.findAll().stream()
//                .map(Episode::getThumbnailUrl) // thumbnail_url 반환
//                .collect(Collectors.toList());
        return episodeRepository.findAllThumbnailUrls();
    }

    public Episode addEpisode(Episode episode) {

        // episodeId 자동 생성
        Long maxEpisodeId = (long) episodeRepository.findMaxEpisodeId();
        episode.setEpisodeId(maxEpisodeId + 1);

        return episodeRepository.save(episode);
    }

    public Optional<Episode> getEpisodeById(Long id) {
        return episodeRepository.findById(id);
    }
}
