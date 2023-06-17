package com.example.colloquium.api;

import com.example.colloquium.dto.request.RateSongDto;
import com.example.colloquium.dto.response.SongInfoResponseDto;
import com.example.colloquium.dto.response.SongResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SongApi {
    @GetMapping("/songs")
    ResponseEntity<List<SongResponseDto>> getSongs();

    @GetMapping("/songs/{id}")
    ResponseEntity<SongInfoResponseDto> getSongById(@PathVariable Long id);

    @PostMapping("/ratings")
    ResponseEntity<String> rateSong(@RequestBody RateSongDto rateSongDto);
}
