package com.example.colloquium.controller;

import com.example.colloquium.api.PlaylistApi;
import com.example.colloquium.dto.request.PlaylistRequestDto;
import com.example.colloquium.dto.request.SongRequestDto;
import com.example.colloquium.dto.response.PlaylistResponseDto;
import com.example.colloquium.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlaylistController implements PlaylistApi {
    private final PlaylistService playlistService;

    @Override
    public ResponseEntity<PlaylistResponseDto> createPlaylist(PlaylistRequestDto playlistRequestDto) {
        return playlistService.createPlaylist(playlistRequestDto);
    }

    @Override
    public ResponseEntity<PlaylistResponseDto> addSong(Long id, SongRequestDto songRequestDto) {
        return playlistService.addSongToPlaylist(id, songRequestDto);
    }
}
