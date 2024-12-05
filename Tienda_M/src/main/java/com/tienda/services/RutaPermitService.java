package com.tienda.services;

import com.tienda.domain.RutaPermit;
import java.util.List;

public interface RutaPermitService {
    
    // Se obtiene un listado de rutapermits en un List
    public List<RutaPermit> getRutaPermits();
    
    public String[] getRutaPermitsString();
    
    // Se obtiene un RutaPermit, a partir del id de un rutaPermit
    public RutaPermit getRutaPermit(RutaPermit rutaPermit);
       
    public void save(RutaPermit rutapermit);
    
    // Se elimina el rutapermit que tiene el id pasado por parámetro
    public void delete(RutaPermit rutaPermit);
    
}