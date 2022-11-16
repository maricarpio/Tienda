package com.tienda.service;
import com.tienda.dao.ArticuloDao;
import com.tienda.domain.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService{
    
    //@Autowired, provoca que si el objeto ya está en memoria no se crea y se usa ese mismo
    //Si no está en memoria se crea el objeto
    @Autowired
    private ArticuloDao articuloDao;
    
    //Los metodos para poder hacer un CRUD
    //Create--Read--Update--Delete
    
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean filtro){
        var lista = (List<Articulo>)articuloDao.findAll();//aqui se recupera la lista completa
        
        if (filtro) {//si es verdadero sólo los articulos activos se deben retornar
            lista.removeIf(e -> !e.isActivo()); 
            //Significa remueva los elementos que cumplen  esta condición "!e.isActivo()" y esto sinifica "no están activos.
            // La "e" significa "Elemeto"
            // "!e" es una negación del elemento 
            // La palabra isActivo es por default, defecto, es necesario en los booleanos porque no tiene get.
        
        }
        
        return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo){
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }
    
    @Override
    @Transactional
    public void save(Articulo articulo){
        //si el idArticulo es 0, lo incerta...
        //Si tiene un valor.. hace el update de ese registro.
        articuloDao.save(articulo);
    }
    
    @Override
    @Transactional
    public void delete (Articulo articulo){
        articuloDao.delete(articulo);
    }
}