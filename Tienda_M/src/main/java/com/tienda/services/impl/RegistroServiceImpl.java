package com.tienda.services.impl;

import com.tienda.domain.Usuario;
import com.tienda.services.CorreoService;
import com.tienda.services.FirebaseStorageService;
import com.tienda.services.RegistroService;
import com.tienda.services.UsuarioService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(
                usuario.getUsername(),
                usuario.getCorreo())) {
            //El usuario NO existe...
            usuario.setPassword(demeClave());
            usuario.setActivo(false);
            usuarioService.save(usuario, false);
            enviarCorreoActivacion(usuario);
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.activacion.ok", null, Locale.getDefault()),
                    usuario.getCorreo());
            model.addAttribute("titulo",
                    messageSource.getMessage("registro.activar", null, Locale.getDefault()));
        } else {  //El username o el correo ya existen
            model.addAttribute("titulo",
                    messageSource.getMessage("registro.activar.error", null, Locale.getDefault()));
            mensaje = String.format(
                    messageSource.getMessage("registro.mensaje.usuario.o.correo", null, Locale.getDefault()),
                    usuario.getUsername(),
                    usuario.getCorreo());
        }
        model.addAttribute("mensaje", mensaje);
        return model;
    }

    @Override
    public Model activarUsuario(Model model, String username, String clave) {
        Usuario usuario = usuarioService.getUsuarioPorUsernameYPassword(username, clave);
        if (usuario!=null) {  //Encontró el usuario
            model.addAttribute("usuario", usuario);
        } else {  //No lo encontró...
            model.addAttribute("titulo", 
                    messageSource.getMessage("registro.activar", null, Locale.getDefault()));
            model.addAttribute("mensaje", 
                    messageSource.getMessage("registro.activar.error", null, Locale.getDefault()));
        }        
        return model;
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
    @Override
    public void habilitaUsuario(Usuario usuario, MultipartFile imagenFile) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));
        if (!imagenFile.isEmpty()) {
            var ruta=firebaseStorageService
                    .cargaImagen(imagenFile, 
                            "usuarios", 
                            usuario.getIdUsuario());
            usuario.setRutaImagen(ruta);
        }
        usuarioService.save(usuario, true);
    }

    @Override
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CorreoService correoService;

    //Continuar acá
    private void enviarCorreoActivacion(Usuario usuario) throws MessagingException {
        String mensaje = messageSource.getMessage("registro.correo.activar", null, Locale.getDefault());
        String asunto = messageSource.getMessage("registro.mensaje.activacion", null, Locale.getDefault());
        mensaje = String.format(mensaje,
                usuario.getNombre(),
                usuario.getApellidos(),
                "localhost",
                usuario.getUsername(),
                usuario.getPassword());
        System.out.println(mensaje);
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

    private String demeClave() {
        String texto = "ABCDEFGHIJKLMNOPQRSTUVXYZ0123456789abcdefghijklmnopqrstuvxyz";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += texto.charAt((int) (Math.random() * texto.length()));
        }
        return clave;
    }

}
