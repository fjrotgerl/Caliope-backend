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
    private Playlist playlist;

    @ManyToOne
    private Cancion cancion;

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

    public Cancion getCanciones() {
        return cancion;
    }

    public void setCanciones(Cancion canciones) {
        this.cancion = canciones;
    }
}
