package com.example.radio_station.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;
    private String filePath;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
}
