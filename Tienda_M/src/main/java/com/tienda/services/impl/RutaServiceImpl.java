package com.tienda.services.impl;

import com.tienda.dao.RutaDao;
import com.tienda.domain.Ruta;
import com.tienda.services.RutaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RutaServiceImpl 
        implements RutaService {

    @Autowired
    private RutaDao rutaDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Ruta> getRutas() {
        var lista = rutaDao.findAll();               
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Ruta getRuta(Ruta ruta) {
        return rutaDao.findById(ruta.getIdRuta()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Ruta ruta) {
        rutaDao.save(ruta);
    }

    @Override
    @Transactional
    public void delete(Ruta ruta) {
        rutaDao.delete(ruta);
    }
}