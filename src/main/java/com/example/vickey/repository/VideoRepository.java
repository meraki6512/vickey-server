package com.example.vickey.repository;

import com.example.vickey.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findAll();

    List<Video> findByEpisodeId(Long episodeId);

    // 추가적인 쿼리 메서드 정의
}
