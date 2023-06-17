package com.example.colloquium.service;

import com.example.colloquium.domain.entity.Playlist;
import com.example.colloquium.domain.entity.Song;
import com.example.colloquium.domain.repository.PlaylistRepository;
import com.example.colloquium.domain.repository.SongRepository;
import com.example.colloquium.dto.request.PlaylistRequestDto;
import com.example.colloquium.dto.request.SongRequestDto;
import com.example.colloquium.dto.response.PlaylistResponseDto;
import com.example.colloquium.dto.response.SongResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;

    public ResponseEntity<PlaylistResponseDto> createPlaylist(PlaylistRequestDto playlistRequestDto) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistRequestDto.getName());
        playlist = playlistRepository.save(playlist);
        return ResponseEntity.ok(new PlaylistResponseDto()
                .setId(playlist.getId())
                .setName(playlistRequestDto.getName())
        );
    }

    public ResponseEntity<PlaylistResponseDto> addSongToPlaylist(Long id, SongRequestDto songRequestDto) {
        Optional<Playlist> optional = playlistRepository.findById(id);
        Optional<Song> optionalSong = songRepository.findById(songRequestDto.getId());
        if (optional.isEmpty() || optionalSong.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Song song = optionalSong.get();
        songRepository.save(song.setPlaylist(optional.get()));

        Playlist playlist = playlistRepository.findById(id).get();
        return ResponseEntity.ok(PlaylistToPlaylistResponseDto(playlist));
    }

    private PlaylistResponseDto PlaylistToPlaylistResponseDto(Playlist playlist) {
        return new PlaylistResponseDto()
                .setId(playlist.getId())
                .setName(playlist.getName())
                .setSongs(playlist.getSongs().stream()
                        .map(this::SongToSongResponseDto)
                        .toList()
                );
    }

    private SongResponseDto SongToSongResponseDto(Song song) {
        return new SongResponseDto()
                .setGenre(song.getGenre())
                .setAuthor(song.getAuthor())
                .setName(song.getName());
    }
}
