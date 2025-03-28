package com.tienda.controller;
import com.tienda.domain.Cliente;
import com.tienda.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ClienteController {
   //provaca que el objeto se cree si no existe o se use el que exista.... no hace más de 1 objeto
    //Esto se conoce como inyección de dependecias...
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/cliente/listado")
    public String inicio(Model model){
        var clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        
        var limiteTotal=0;
        for (var c: clientes) {
            limiteTotal+=c.credito.limite;
        }
        model.addAttribute("limiteTotal",limiteTotal);
        model.addAttribute("totalClientes",clientes.size());
        return "/cliente/listado";
   
    }
   @GetMapping("/cliente/nuevo")
    public String clienteNuevo(Cliente cliente) {
        return "/cliente/modificar";//esto llama al html del folder templates.cliente
    }
   @PostMapping("/cliente/guardar")
    public String clienteGuardar(Cliente cliente) {
        clienteService.save(cliente);
        return "redirect:/cliente/listado";//esto llama al html del folder templates.cliente
    }
   @GetMapping("/cliente/modificar/{idCliente}")
    public String clienteActualiza(Cliente cliente, Model model) {
        cliente = clienteService.getCliente(cliente);
        model.addAttribute("cliente",cliente);
        return "/cliente/modificar";
    }
    
    @GetMapping("/cliente/eliminar/{idCliente}")
    public String clienteElimina(Cliente cliente) {
        clienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }
}