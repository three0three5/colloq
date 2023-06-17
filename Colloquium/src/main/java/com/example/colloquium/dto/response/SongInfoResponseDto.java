package com.example.colloquium.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class SongInfoResponseDto {
    private Long id;
    private String name;
    private String author;
    private String genre;
    private String duration;
    private Long playlistId;
}
