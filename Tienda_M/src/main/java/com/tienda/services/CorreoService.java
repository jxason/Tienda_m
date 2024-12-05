package com.tienda.services;

import jakarta.mail.MessagingException;

public interface CorreoService {
    
    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenido
    ) throws MessagingException;
    
}
