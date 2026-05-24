package com.bolsadeideas.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.bolsadeideas.springboot.backend.chat.models.documents.Mensaje;
import com.bolsadeideas.springboot.backend.chat.models.service.ChatService;

//We use "Controller" instead of "RestController" because we are not going to return JSON, but we are going to return a view (HTML)
@Controller
public class ChatController {

    private String[] colores = {"red", "green", "blue", "magenta", "purple", "orange"};

    @Autowired
    private ChatService chatService;

    //Our message broker has a prefix "/chat/", so we need to send the message to that 
    // prefix, and we need to use the @MessageMapping annotation to map the message to the 
    // method that will handle it, and we need to use the @SendTo annotation to specify the 
    // destination where the message will be sent after being processed by the method
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMensaje(Mensaje mensaje) {
        mensaje.setFecha(new Date().getTime());

        if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("nuevo usuario");
        } else {
            chatService.guardar(mensaje);
        }

        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String estaEscribiendo(String username) {
        return username.concat(" está escribiendo...");
    }

}
