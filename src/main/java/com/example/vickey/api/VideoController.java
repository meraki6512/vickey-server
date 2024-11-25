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

    // 비디오 업로드 API
    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file,
                              @RequestParam("episodeId") Long episodeId,
                              @RequestParam("episodeNum") int episodeNum) throws IOException {
        // 파일을 서버에 업로드 후 URL을 반환받음
        String videoUrl = videoService.uploadVideo(file);
        // 비디오 URL을 DB에 저장
        videoService.saveVideo(episodeId, videoUrl, episodeNum);
        return "Video uploaded successfully!";
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();  // 모든 비디오 데이터 반환
    }


    @GetMapping
    public ResponseEntity<List<Video>> getVideosById(@RequestParam Long id) {
        List<Video> videos = videoService.findVideosById(id);
        if (videos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 비어 있으면 204 반환
        }
        return ResponseEntity.ok(videos);
    }
}
