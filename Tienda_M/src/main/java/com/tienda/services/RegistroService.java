package com.tienda.services;

import com.tienda.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {
    
    //Método para crear inicialmente un usuario de manera parcial
    public Model crearUsuario(Model model, Usuario usuario) 
            throws MessagingException;
    
    //Método para activar un usuario desde el enlace de correo enviado
    public Model activarUsuario(Model model, 
            String username, 
            String clave);
    
    //Método para habilitar completamente un usuario
    public void habilitaUsuario(Usuario usuario, 
            MultipartFile imagenFile);
    
    //Método para recordar la clave de un usuario
    public Model recordarUsuario(Model model, Usuario usuario) 
            throws MessagingException;
}
