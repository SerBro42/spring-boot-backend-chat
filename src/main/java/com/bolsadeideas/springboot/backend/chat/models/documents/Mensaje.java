package com.bolsadeideas.springboot.backend.chat.models.documents;

import java.io.Serializable;

//We implement Serializable because we are going to send this object through the WebSocket, and it needs to be serializable to be sent as a message
public class Mensaje implements Serializable {

    private String texto;
    private Long fecha;

    

    public String getTexto() {
        return texto;
    }



    public void setTexto(String texto) {
        this.texto = texto;
    }



    public Long getFecha() {
        return fecha;
    }



    public void setFecha(Long fecha) {
        this.fecha = fecha;
    }



    private static final long serialVersionUID = 1L;
}
