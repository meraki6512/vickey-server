package com.example.vickey.repository;

import com.example.vickey.EpisodeTitleCountDto;
import com.example.vickey.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//DB 상호작용 관련 부분
public interface EpisodeRepository extends JpaRepository<Episode, Long> {

    List<Episode> findAll();

    @Query("SELECT new com.example.vickey.EpisodeTitleCountDto(e.title, e.episodeCount) FROM Episode e")
    List<EpisodeTitleCountDto> findTitleAndEpisodeCount();

    @Query("SELECT e.thumbnailUrl FROM Episode e")
    List<String> findAllThumbnailUrls();

    @Query("SELECT COALESCE(MAX(e.episodeId), 0) FROM Episode e")
    int findMaxEpisodeId();

    // 추가적인 쿼리 메서드
}
