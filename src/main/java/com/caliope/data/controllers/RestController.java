package com.caliope.data.controllers;

import com.caliope.data.entities.*;
import com.caliope.data.error.InfoEntity;
import com.caliope.data.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private CancionRepository cancionRepository;
    private GeneroRepository generoRepository;
    private PlaylistRepository playlistRepository;
    private PlaylistCancionesRepository playlistCancionesRepository;
    private UsuarioRepository usuarioRepository;
    private SeguidorRepository seguidorRepository;
    private CancionesMeGustaRepository cancionesMeGustaRepository;

    @Autowired
    public RestController(CancionRepository cancionRepository, GeneroRepository generoRepository, PlaylistRepository playlistRepository,
                          PlaylistCancionesRepository playlistCancionesRepository, UsuarioRepository usuarioRepository, SeguidorRepository seguidorRepository,
                          CancionesMeGustaRepository cancionesMeGustaRepository) {
        this.cancionRepository           = cancionRepository;
        this.generoRepository            = generoRepository;
        this.playlistRepository          = playlistRepository;
        this.playlistCancionesRepository = playlistCancionesRepository;
        this.usuarioRepository           = usuarioRepository;
        this.seguidorRepository          = seguidorRepository;
        this.cancionesMeGustaRepository  = cancionesMeGustaRepository;
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Canciones */
    @RequestMapping(value = "/getCanciones", method = RequestMethod.GET)
    public @ResponseBody List<Cancion> getCanciones() {
        return (List<Cancion>) this.cancionRepository.findAll();
    }

    /* Obtener cancion por id */
    @RequestMapping(value = "/getCancionById/{cancionId}", method = RequestMethod.GET)
    public @ResponseBody Optional<Cancion> getUsuario(@PathVariable("cancionId") Integer cancionId) {
        return this.cancionRepository.findById(cancionId);
    }

    /* Añadir cancion */
    @RequestMapping(value = "/añadir/cancion", method = RequestMethod.POST)
    public @ResponseBody InfoEntity añadirCancion(@RequestBody Cancion cancion) {

        if (this.cancionRepository.findById(cancion.getId()).isPresent()) { return new InfoEntity(HttpStatus.CONFLICT,"Cancion duplicada"); }

        this.cancionRepository.save(cancion);

        return new InfoEntity(HttpStatus.OK,"Canción añadida");

    }

    /* Obtener todas las canciones de un usuario */
    @RequestMapping(value = "/getSongsFromUser/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Optional<Cancion>> getSongsFromUser(@PathVariable("userId") String userId ) {
        List<Optional<Cancion>> canciones = new ArrayList<>();
        for ( Integer idCancion : this.cancionRepository.getSongsFromUserById(userId)) {
            if (this.cancionRepository.findById(idCancion).isPresent()) { canciones.add(this.cancionRepository.findById(idCancion)); }
        }
        return canciones;
    }

    /* Eliminar una cancion por id de cancion */
    @RequestMapping(value = "/deleteSongById/{cancionId}", method = RequestMethod.PUT)
    public @ResponseBody Integer deleteSongById(@PathVariable("cancionId") Integer cancionId) {
        return this.cancionRepository.deleteSongById(cancionId);
    }

    /* Obtener las canciones las cuales un usuario ha dado me gusta */
    @RequestMapping(value = "/getLikedSongsByUserId/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Optional<Cancion>> getLikedSongsByUserId (@PathVariable("userId") String userId) {

        List<Optional<Cancion>> canciones = new ArrayList<>();
        for ( Integer idCancion : this.cancionesMeGustaRepository.getLikedSongsByUserId(userId)) {
            if (this.cancionRepository.findById(idCancion).isPresent()) { canciones.add(this.cancionRepository.findById(idCancion)); }
        }
        return canciones;
    }

    /* Añadir cancion a me gusta */
    @RequestMapping(value = "/addSongToLikedList/{cancionId}/{userId}", method = RequestMethod.PUT)
    public @ResponseBody int addSongToLikedList (@PathVariable("cancionId") Integer cancionId, @PathVariable("userId") String userId) {
        return this.cancionesMeGustaRepository.addNewLikeSong(userId,cancionId);
    }

    /* Borrar cancion de me gusta */
    @RequestMapping(value = "/removeSongFromLikedList/{cancionId}/{userId}", method = RequestMethod.POST)
    public @ResponseBody int removeSongFromLikedList (@PathVariable("cancionId") Integer cancionId, @PathVariable("userId") String userId) {
        return this.cancionesMeGustaRepository.deleteByUsuarioAndCancion(userId, cancionId);
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Generos */
    @RequestMapping(value = "/getGeneros", method = RequestMethod.GET)
    public @ResponseBody List<Genero> getGeneros() {
        return (List<Genero>) this.generoRepository.findAll();
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Playlists */
    @RequestMapping(value = "/getPlaylist", method = RequestMethod.GET)
    public @ResponseBody List<Playlist> getPlaylists() {
        return (List<Playlist>) this.playlistRepository.findAll();
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* Crear Playlist */
    @RequestMapping(value = "/createPlaylist", method = RequestMethod.POST)
    public @ResponseBody Integer createPlaylist(@RequestParam(value = "nombrePlaylist") String nombrePlaylist, @RequestParam("userId") String userId) {
        return this.playlistRepository.createPlaylist(nombrePlaylist, userId);
    }

    /* Borrar Playlist */
    @RequestMapping(value = "/deletePlaylist/{playlistId}", method = RequestMethod.PUT)
    public @ResponseBody void deletePlaylist(@PathVariable("playlistId") Integer playlistId) {
        this.playlistRepository.deleteById(playlistId);
    }

    /* ------------------------------------------------------------------------------------------------------- */
    /* PlaylistCanciones */
    @RequestMapping(value = "/getPlaylistCanciones", method = RequestMethod.GET)
    public @ResponseBody List<PlaylistCanciones> getPlaylistCanciones() {
        return (List<PlaylistCanciones>) this.playlistCancionesRepository.findAll();
    }

    /* Encontrar todas las playlists de un usuario */
    @RequestMapping(value = "/getUserPlaylistsByUserId/{userId}", method = RequestMethod.GET)
    public @ResponseBody List<Playlist> getUserPlaylistsByUserId2(@PathVariable("userId") String userId) {

        return this.playlistRepository.getAllUserPlaylists(userId);
    }

    /* Añadir cancion a playlist */
    @RequestMapping(value = "/addSongToPlaylist/{playlistId}/{cancionId}", method = RequestMethod.PUT)
    public @ResponseBody int addSongToPlaylist(@PathVariable("playlistId") Integer playlistId, @PathVariable("cancionId") Integer cancionId) {
        return this.playlistCancionesRepository.addSongToPlaylist(playlistId, cancionId);
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener todas las canciones de una playlist en concreto */
    @RequestMapping(value = "/getCancionesFromPlaylist/{playlistId}", method = RequestMethod.GET)
    public @ResponseBody
    List<Optional<Cancion>> getPlaylistCanciones(@PathVariable("playlistId") Integer playlistId) {
        List<Optional<Cancion>> canciones = new ArrayList<>();
        for ( Integer cancionId : this.playlistCancionesRepository.getAllPlaylistSong(playlistId)) {
            if (this.cancionRepository.findById(cancionId).isPresent()) { canciones.add(this.cancionRepository.findById(cancionId)); }
        }
        return canciones;
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Seguidores */
    /* Obtener seguidores de 'x' usuario */
    @RequestMapping(value = "/getSeguidores/{usuario}", method = RequestMethod.GET)
    public @ResponseBody List<Optional<Usuario>> getSeguidores(@PathVariable(value = "usuario") String usuario) {
        List<Optional<Usuario>> usuarios = new ArrayList<>();
        for ( String nombre : this.seguidorRepository.getSeguidoresDeUsuario(usuario)) {
            if (this.usuarioRepository.findById(nombre).isPresent()) { usuarios.add(this.usuarioRepository.findById(nombre)); }
        }
        return usuarios;
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener la gente que sigue 'x' usuario */
    @RequestMapping(value = "/getSeguidos/{usuario}", method = RequestMethod.GET)
    public @ResponseBody List<Optional<Usuario>> getSeguidos(@PathVariable(value = "usuario") String usuario) {
        List<Optional<Usuario>> usuarios = new ArrayList<>();
        for ( String nombre : this.seguidorRepository.getSeguidosDeUsuario(usuario)) {
            if (this.usuarioRepository.findById(nombre).isPresent()) { usuarios.add(this.usuarioRepository.findById(nombre)); }
        }
        return usuarios;
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Usuarios */
    /* Obtener todos los usuarios */
    @RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
    public @ResponseBody List<Usuario> getUsuarios() {
        return (List<Usuario>) this.usuarioRepository.findAll();
    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Registrar usuario */
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public @ResponseBody InfoEntity añadirUsuario(@RequestBody Usuario usuario) {

        if (this.usuarioRepository.findById(usuario.getUsername()).isPresent()) { return new InfoEntity(HttpStatus.NOT_FOUND,"Usuario duplicado"); }

        this.usuarioRepository.save(usuario);

        return new InfoEntity(HttpStatus.OK,"Usuario creado", usuario);

    }

    /* ------------------------------------------------------------------------------------------------------- */

    /* Obtener usuario por username */
    @RequestMapping(value = "/getUsuarioById/{usuarioId}", method = RequestMethod.GET)
    public @ResponseBody Usuario getUsuario(@PathVariable("usuarioId") String usuarioId) {
        return this.usuarioRepository.findUsuarioByUsername(usuarioId);
    }

    /* ------------------------------------------------------------------------------------------------------- */
    /* Obtener usuario por email */
    @RequestMapping(value = "/getUsuarioByEmail/{usuarioEmail}", method = RequestMethod.GET)
    public @ResponseBody Usuario getUsuarioByEmail(@PathVariable("usuarioEmail") String usuarioEmail) {
        return this.usuarioRepository.findUsuarioByEmail(usuarioEmail);
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* ------------------------------------------------------------------------------------------------------- */
    /* Obtener usuario por token */
    @RequestMapping(value = "/getUsuarioByToken/{userToken}", method = RequestMethod.GET)
    public @ResponseBody Usuario getUsuarioByToken(@PathVariable("userToken") String userToken) {
        return this.usuarioRepository.findUsuarioByToken(userToken);
    }
    /* ------------------------------------------------------------------------------------------------------- */

    /* Añadir o modificar token de usuario */
    @RequestMapping(value = "/setUsuarioToken/{userId}/{token}", method = RequestMethod.PUT)
    public @ResponseBody int setUsuarioToken(@PathVariable("userId") String userId, @PathVariable("token") String token) {
        return this.usuarioRepository.setUsuarioToken(token,userId);
    }
    /* ------------------------------------------------------------------------------------------------------- */

}
