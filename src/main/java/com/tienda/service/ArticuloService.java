
package com.tienda.service;

import com.tienda.domain.Articulo;
import java.util.List;


public interface ArticuloService {
    //Los metodos para poder hacer un CRUD
    //Create read update and delete
    
    public List<Articulo>getArticulos(boolean filtro);
    
    public Articulo getArticulo(Articulo articulo);
    
    public void save(Articulo articulo);
    
    public void delete(Articulo articulo);
    
}
