package com.caliope.data.entities;

import javax.persistence.*;

@Entity
public class Seguidor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne
    @Column(name = "usuario_fk")
    private Usuario usuarioSeguido;

    @ManyToOne
    @Column(name = "usuario_seguidor_fk")
    private Usuario seguidor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuarioSeguido() {
        return usuarioSeguido;
    }

    public void setUsuarioSeguido(Usuario usuarioSeguido) {
        this.usuarioSeguido = usuarioSeguido;
    }

    public Usuario getSeguidor() {
        return seguidor;
    }

    public void setSeguidor(Usuario seguidor) {
        this.seguidor = seguidor;
    }
}
