package com.tienda.services;

import com.tienda.domain.Role;
import java.util.List;

public interface RoleService {
    
    // Se obtiene un listado de roles en un List
    public List<Role> getRoles();
    
    // Se obtiene un Role, a partir del id de un role
    public Role getRole(Role role);
       
    public void save(Role role);
    
    // Se elimina el role que tiene el id pasado por parámetro
    public void delete(Role role);
    
}
