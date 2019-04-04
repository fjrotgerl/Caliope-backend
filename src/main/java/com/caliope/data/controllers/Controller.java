package com.caliope.data.controllers;

import com.caliope.data.entities.*;
import com.caliope.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /* ------------------------------------------------------------------------------------------------------- */

    /* Canciones */
    @RequestMapping(value = "/getCanciones", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Cancion> getCanciones() {
        return (List<Cancion>) this.cancionRepository.findAll();
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Generos */
    @RequestMapping(value = "/getGeneros", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Genero> getGeneros() {
        return (List<Genero>) this.generoRepository.findAll();
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Playlists */
    @RequestMapping(value = "/getPlaylist", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Playlist> getPlaylists() {
        return (List<Playlist>) this.playlistRepository.findAll();
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* PlaylistCanciones */
    @RequestMapping(value = "/getPlaylistCanciones", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<PlaylistCanciones> getPlaylistCanciones() {
        return (List<PlaylistCanciones>) this.playlistCancionesRepository.findAll();
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener todas las canciones de una playlist en concreto */
    /* NO FUNCIONA */
    @RequestMapping(value = "/getCancionesFromPlaylist/{username}/{nombrePlaylist}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Cancion> getPlaylistCanciones(@PathVariable("username") String username, @PathVariable("nombrePlaylist") String nombrePlaylist) {
        return this.playlistCancionesRepository.getCancionesFromPlaylist(username, nombrePlaylist);
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Seguidores */
    /* Obtener seguidores de 'x' usuario */
    @RequestMapping(value = "/getSeguidores/{usuario}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Optional<Usuario>> getSeguidores(@PathVariable(value = "usuario") String usuario) {
        List<Optional<Usuario>> usuarios = new ArrayList<>();
        for ( String nombre : this.seguidorRepository.getSeguidoresDeUsuario(usuario)) {
            if (this.usuarioRepository.findById(nombre).isPresent()) { usuarios.add(this.usuarioRepository.findById(nombre)); }
        }
        return usuarios;
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener la gente que sigue 'x' usuario */
    @RequestMapping(value = "/getSeguidos/{usuario}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Optional<Usuario>> getSeguidos(@PathVariable(value = "usuario") String usuario) {
        List<Optional<Usuario>> usuarios = new ArrayList<>();
        for ( String nombre : this.seguidorRepository.getSeguidosDeUsuario(usuario)) {
            if (this.usuarioRepository.findById(nombre).isPresent()) { usuarios.add(this.usuarioRepository.findById(nombre)); }
        }
        return usuarios;
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* Usuarios */
    /* Obtener todos los usuarios */
    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody List<Usuario> getUsuarios() {
        return (List<Usuario>) this.usuarioRepository.findAll();
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener usuario por username */
    @RequestMapping(value = "/getUsuarioById/{usuarioId}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    Optional<Usuario> getUsuario(@PathVariable("usuarioId") String usuarioId) {
        return this.usuarioRepository.findById(usuarioId);
    }
    /* ------------------------------------------------------------------------------------------------------- */

}
