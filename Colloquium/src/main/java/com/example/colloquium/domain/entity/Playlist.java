package com.example.colloquium.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "playlist")
@Data
@Accessors(chain = true)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
    List<Song> songs;
}
