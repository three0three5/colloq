package com.example.colloquium.service;

import com.example.colloquium.domain.entity.Song;
import com.example.colloquium.domain.repository.SongRepository;
import com.example.colloquium.dto.request.RateSongDto;
import com.example.colloquium.dto.response.SongInfoResponseDto;
import com.example.colloquium.dto.response.SongResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public ResponseEntity<List<SongResponseDto>> getAllSongs() {
        List<SongResponseDto> result = songRepository.findAll().stream().map(
                x -> (new SongResponseDto())
                        .setAuthor(x.getAuthor())
                        .setName(x.getName())
                        .setGenre(x.getGenre())
        ).toList();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<SongInfoResponseDto> getSongById(Long id) {
        Optional<Song> song = songRepository.findById(id);
        if (song.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        long mins = song.get().getDuration() / 60;
        long seconds = song.get().getDuration() % 60;
        String duration = "%dm%ds".formatted(mins, seconds);
        SongInfoResponseDto result = new SongInfoResponseDto()
                .setAuthor(song.get().getAuthor())
                .setName(song.get().getName())
                .setId(song.get().getId())
                .setGenre(song.get().getGenre())
                .setDuration(duration);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<String> rateSong(RateSongDto rateSongDto) {
        Long songId = rateSongDto.getId();
        Integer rating = rateSongDto.getRating();
        Optional<Song> songOptional = songRepository.findById(songId);
        if (songOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Song song = songOptional.get();
        song.setRating(rating);
        songRepository.save(song);
        return ResponseEntity.ok("Success");
    }
}
