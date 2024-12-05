package com.tienda.services;

import com.tienda.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    //Se obtiene un arraylist con los registros 
    //de la tabla producto
    //Todos los registros... o sólo los activos
    public List<Producto> getProductos(boolean activos);
    
    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);
    
    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);
    
    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);
    
    //ConsultaAmpliada
    //Recupera los productos que están en un rango de precios ordenados por descripcion
    public List<Producto> consultaAmpliada (double precioInf, double precioSup);
    
    //ConsultaJPQL
    //Recupera los productos que están en un rango de precios ordenados por descripcion    
    public List<Producto> consultaJPQL (double precioInf, double precioSup);
    
    //ConsultaSQL
    //Recupera los productos que están en un rango de precios ordenados por descripcion    
    public List<Producto> consultaSQL (double precioInf, double precioSup);
    
    
}
