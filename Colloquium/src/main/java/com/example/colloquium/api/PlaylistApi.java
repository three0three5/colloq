package com.example.colloquium.api;

import com.example.colloquium.dto.request.PlaylistRequestDto;
import com.example.colloquium.dto.request.SongRequestDto;
import com.example.colloquium.dto.response.PlaylistResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/playlists")
public interface PlaylistApi {
    @PostMapping("/")
    ResponseEntity<PlaylistResponseDto> createPlaylist(@RequestBody PlaylistRequestDto playlistRequestDto);

    @PostMapping("/{id}/songs")
    ResponseEntity<PlaylistResponseDto> addSong(@PathVariable Long id,
                                                @RequestBody SongRequestDto songRequestDto);
}
