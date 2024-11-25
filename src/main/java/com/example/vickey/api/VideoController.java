package com.example.vickey.api;

import com.example.vickey.service.VideoService;
import com.example.vickey.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // 비디오 업로드 : 바로 S3에 동영상을 업로드
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file,
                                              @RequestParam("episodeId") Long episodeId,
                                              @RequestParam("episodeNum") int episodeNum) throws IOException {
        // S3에 업로드
        String videoUrl = videoService.uploadVideo(file);

        // URL을 DB에 저장
        videoService.saveVideo(episodeId, videoUrl, episodeNum);

        return ResponseEntity.ok("Video uploaded successfully: " + videoUrl);
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();  // 모든 비디오 데이터 반환
    }


    @GetMapping
    public ResponseEntity<List<Video>> getVideosByEpisodeId(@RequestParam Long episodeId) {
        List<Video> videos = videoService.findVideosByEpisodeId(episodeId);
        if (videos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 비어 있으면 204 반환
        }
        return ResponseEntity.ok(videos);
    }
}
