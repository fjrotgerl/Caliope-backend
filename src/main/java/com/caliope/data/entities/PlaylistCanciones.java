package com.caliope.data.entities;

import javax.persistence.*;

@Entity
public class PlaylistCanciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "playlist_fk")
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "cancion_fk")
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
