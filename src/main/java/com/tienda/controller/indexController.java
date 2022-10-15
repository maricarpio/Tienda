
package com.tienda.controller;

import com.tienda.dao.ClienteDao;
import com.tienda.domain.Cliente;
import com.tienda.service.ClienteService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    //Provoca que el objeto se cree si no existe o se use el que no exites...no hace mas de 1 objeto
    //Esto se conoce como inyeccion de dependencias...
    
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/")
    public String inicio(Model model){
        var texto="Estamos en semana 4";
        model.addAttribute("saludo",texto);
        
        /*Cliente cliente1 = new Cliente("Pedro","Jimenez Retana","pjimenez@gmail.com","8687-9069");
        Cliente cliente2 = new Cliente("Juan","Jimenez Retana","pretana@gmail.com","8483-2134");
        
        var clientes = Arrays.asList(cliente1,cliente2);*/
        
        var clientes = clienteService.getClientes();
        
        model.addAttribute("clientes",clientes);
        return "index";
    }
    
}
