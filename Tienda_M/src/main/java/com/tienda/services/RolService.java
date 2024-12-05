package com.tienda.services;

import com.tienda.domain.Rol;
import java.util.List;

public interface RolService {
    
    // Se obtiene un listado de roles en un List
    public List<Rol> getRoles();
    
    // Se obtiene un Role, a partir del id de un role
    public Rol getRol(Rol rol);
       
    public void save(Rol rol);
    
    // Se elimina el role que tiene el id pasado por parámetro
    public void delete(Rol rol);
    
}
