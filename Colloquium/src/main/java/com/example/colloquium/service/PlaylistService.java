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

import java.util.List;
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
        Playlist playlist = optional.get();
        Song song = optionalSong.get();
        List<Song> songs = playlist.getSongs();
        if (songs.contains(song)) {
            return ResponseEntity.ok(null);
        }
        song.setPlaylist(playlist);
        song = songRepository.save(song);
        songs.add(song);
        playlist.setSongs(songs);
        playlist = playlistRepository.save(playlist);
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
