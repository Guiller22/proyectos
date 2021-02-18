package com.reytech.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Controladores
//Para manejar peticiones Web es necesario crear controladores, los cuales manejan la solicitud
//a partir de una ruta de acceso. Creamos la clase ControllerIndex en el paquete com.proyecto.controller. 
//Agregamos las anotaciones @Controller y @GetMapping, .....


@Controller
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "hello";
    }

     //el método message será ejecutado cuando se solicite la URL de inicio http://localhost:8090/.
    //En ese momento, el método será ejecutado añadiendo un mensaje a una variable con nombre message
    //que se guarda en model, objeto que se pasa a la vista, para que pueda construir el html dinámicamente.
    
    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("mensaje", "Esto es un mensaje personalizado");
        //Spring pasará el control a la vista con nombre message correspondiente al fichero message.html 
        //de la carpeta de templates, tal y como indica el return «index», 
        //en el que no hace falta indicar la extensión .html.
        return "message";
    }
}
