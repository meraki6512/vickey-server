package com.example.vickey.service;

import com.example.vickey.entity.Video;
import com.example.vickey.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // 비디오 업로드 처리
    public String uploadVideo(MultipartFile file) throws IOException {
        // 업로드할 디렉토리 경로
        String uploadDir = "/data/user/0/com.example.vickey/cache/"; // 임시
        Path path = Paths.get(uploadDir, file.getOriginalFilename());

        // 파일을 서버에 저장
        Files.write(path, file.getBytes());

        // 저장된 파일의 URL (예: S3 URL 또는 로컬 파일 경로)
        return path.toString();
    }

    // 비디오 정보를 DB에 저장
    public void saveVideo(Long episodeId, String videoUrl, int episodeNum) {
        Video video = new Video();
        video.setEpisodeId(episodeId);
        video.setVideoUrl(videoUrl);
        video.setEpisodeNum(episodeNum);
        videoRepository.save(video);
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public List<Video> findVideosById(Long episodeId) {
        return videoRepository.findByEpisodeId(episodeId);
    }
}

