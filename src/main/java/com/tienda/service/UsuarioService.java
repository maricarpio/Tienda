
package com.tienda.service;
import com.tienda.dao.UsuarioDao;
import com.tienda.domain.Rol;
import com.tienda.domain.Usuario;
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
public class UsuarioService implements UserDetailsService{
    
    //Provoca que si el objeto ya esta en memoria se usa ese 
    //Si no esta en memoria se crea el objeto
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //Se busca el usuario en la tabla de usuarios
        Usuario usuario = usuarioDao.findByUsername(username);
        
        if(usuario==null){ //no se encontro el usuario
            throw new UsernameNotFoundException(username);
        }
        
        //Se carga los roles que tiene el usuario
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Rol rol : usuario.getRoles()){//se recorre la lista de roles del usuario
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        
        //Se retorna la info de userDetails
        return new User(usuario.getUsername(),usuario.getPassword(),roles);
    }
   
    

}
    