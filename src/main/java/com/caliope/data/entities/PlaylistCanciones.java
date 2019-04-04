package com.caliope.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class PlaylistCanciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @Column(name = "playlist_fk")
    private Playlist playlist;

    @ManyToOne
    @Column(name = "cancion_fk")
    private List<Cancion> canciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
