package com.caliope.data.controllers;

import com.caliope.data.entities.*;
import com.caliope.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private CancionRepository cancionRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private PlaylistCancionesRepository playlistCancionesRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SeguidorRepository seguidorRepository;

    /* Canciones */
    @RequestMapping(value = "/getCanciones", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Cancion> getCanciones() {
        return (List<Cancion>) this.cancionRepository.findAll();
    }

    /* Generos */
    @RequestMapping(value = "/getGeneros", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Genero> getGeneros() {
        return (List<Genero>) this.generoRepository.findAll();
    }

    /* Playlists */
    @RequestMapping(value = "/getPlaylist", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Playlist> getPlaylists() {
        return (List<Playlist>) this.playlistRepository.findAll();
    }

    /* PlaylistCanciones */
    @RequestMapping(value = "/getPlaylistCanciones", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<PlaylistCanciones> getPlaylistCanciones() {
        return (List<PlaylistCanciones>) this.playlistCancionesRepository.findAll();
    }

    /* Seguidores */
    /* Obtener followers */
    @RequestMapping(value = "/getSeguidores/{usuario}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Usuario> getSeguidores(@PathVariable(value = "usuario") String nombreUsuario) {
        return this.seguidorRepository.getSeguidoresDeUsuario(nombreUsuario);
    }

    /* Usuarios */
    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Usuario> getUsuarios() {
        return (List<Usuario>) this.usuarioRepository.findAll();
    }

    /* Consultas personalizadas */

}
