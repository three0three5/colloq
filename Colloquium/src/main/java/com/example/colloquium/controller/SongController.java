package com.example.colloquium.controller;

import com.example.colloquium.api.SongApi;
import com.example.colloquium.dto.request.RateSongDto;
import com.example.colloquium.dto.response.SongInfoResponseDto;
import com.example.colloquium.dto.response.SongResponseDto;
import com.example.colloquium.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SongController implements SongApi {
    private final SongService songService;

    @Override
    public ResponseEntity<List<SongResponseDto>> getSongs() {
        return songService.getAllSongs();
    }

    @Override
    public ResponseEntity<SongInfoResponseDto> getSongById(Long id) {
        return songService.getSongById(id);
    }

    @Override
    public ResponseEntity<String> rateSong(RateSongDto rateSongDto) {
        return songService.rateSong(rateSongDto);
    }
}
