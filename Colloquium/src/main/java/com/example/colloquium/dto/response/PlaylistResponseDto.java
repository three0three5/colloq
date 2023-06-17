package com.example.colloquium.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class PlaylistResponseDto {
    private Long id;
    private String name;
    private List<SongResponseDto> songs;
}
