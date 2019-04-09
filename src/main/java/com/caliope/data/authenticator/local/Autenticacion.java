package com.caliope.data.authenticator.local;

import com.caliope.data.entities.Usuario;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Autenticacion {

    private Usuario usuario;
    private Estado estado;

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario user) {
        this.usuario = user;
    }

    public Estado getStatus() {
        return estado;
    }

    public void setStatus(Estado status) {
        this.estado = status;
    }
}
