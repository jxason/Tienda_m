package com.tienda.services.impl;

import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
import com.tienda.services.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service("userDetailsService")
public class UsuarioDetailsServiceImpl
        implements UsuarioDetailsService, UserDetailsService {
 
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private HttpSession session;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        
        //Se busca el usuario en la tabla usuarios por medio del username
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //Se valida si se encontró el usuario con el username pasado...
        if (usuario == null) {
            //El usuario NO se encontró
            throw new UsernameNotFoundException(username);
        }
        
        //Si estamos acá entonces SI se encontró el usuario...
        //Guarmanos la imagen del usuario en una variable de session.
        session.removeAttribute("imagenUsuario");
        session.setAttribute("imagenUsuario", usuario.getRutaImagen());
        
        
        //Se deben recuperar los roles del usuario y crear un ArrayList con Roles de seguridad
        var roles = new ArrayList<GrantedAuthority>();
        //Se revisan los roles del usuario y se convierten en roles de seguridad
        for (Rol r: usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_"+r.getNombre()));
            System.out.println("[ROLE_"+r.getNombre()+"]");
        }
        //Se retorna un usuario de Seguridad con roles incluídos...
        return new User(usuario.getUsername(),
                usuario.getPassword(),
                roles);
    }
}
