package com.caliope.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "contraseña")
    private String contraseña;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "permiso")
    private Integer permiso;

    @Column(name = "seguidores")
    private Integer numeroSeguidores;

    @Column(name = "seguidos")
    private Integer numeroSeguidos;

    @Column(name = "fecha_registro")
    private Date fechaRegistro;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getPermiso() {
        return permiso;
    }

    public void setPermiso(Integer permiso) {
        this.permiso = permiso;
    }

    public Integer getNumeroSeguidores() {
        return numeroSeguidores;
    }

    public void setNumeroSeguidores(Integer seguidores) {
        this.numeroSeguidores = seguidores;
    }

    public Integer getNumeroSeguidos() {
        return numeroSeguidos;
    }

    public void setNumeroSeguidos(Integer numeroSeguidos) {
        this.numeroSeguidos = numeroSeguidos;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fecha_registro) {
        this.fechaRegistro = fecha_registro;
    }

}