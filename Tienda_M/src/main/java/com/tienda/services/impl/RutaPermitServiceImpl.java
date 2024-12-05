package com.tienda.services.impl;

import com.tienda.dao.RutaPermitDao;
import com.tienda.domain.RutaPermit;
import com.tienda.services.RutaPermitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RutaPermitServiceImpl 
        implements RutaPermitService {

    @Autowired
    private RutaPermitDao rutaPermitDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<RutaPermit> getRutaPermits() {
        var lista = rutaPermitDao.findAll();
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public String[] getRutaPermitsString(){
        var lista = rutaPermitDao.findAll();
        String[] rutasPermit = new String[lista.size()];
        int i = 0;
        for (RutaPermit rp : lista) {
            rutasPermit[i] = rp.getPatron();
            i++;
        }
        return rutasPermit;
    }
    
    @Override
    @Transactional(readOnly=true)
    public RutaPermit getRutaPermit(RutaPermit rutaPermit) {
        return rutaPermitDao.findById(rutaPermit.getIdRuta()).orElse(null);
    }

    @Override
    @Transactional
    public void save(RutaPermit rutaPermit) {
        rutaPermitDao.save(rutaPermit);
    }

    @Override
    @Transactional
    public void delete(RutaPermit rutaPermit) {
        rutaPermitDao.delete(rutaPermit);
    }
}
