package com.example.colloquium.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "song")
@Data
@Accessors(chain = true)
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
}
