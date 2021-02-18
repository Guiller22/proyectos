package com.example.API_REST_Spring_Boot_MYSQL.controller;
import com.example.API_REST_Spring_Boot_MYSQL.model.User;
import com.example.API_REST_Spring_Boot_MYSQL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
//OJO la URL seria http://localhost:4008/users/
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/insertar")
    public void add(@RequestBody User user) {
        userService.saveUser(user);
    }
    
    // PUT   localhost:4004/users/modificar/1
    //{
        //"firstName": "Juan",
        //"lastName": "Perez Rubiales"
   //}
    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer id) {
        try {
            User existUser = userService.getUser(id);
            user.setId(id);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE localhost:4004/users/borrar/5
    @DeleteMapping("/borrar/{id}")
    public void delete(@PathVariable Integer id) {
    	
        userService.deleteUser(id);
    }
  //http://localhost:4004/users/porapellido/Trigueros Tejada
    //resuesta:
    //[{"id":6,"firstName":"Alba","lastName":"Trigueros Tejada"}]
    @GetMapping("/porapellido/{lastName}")
    public List<User> getApellido (@PathVariable String lastName)
    {
    	return userService.getLastname(lastName);
     
    }
    //http://localhost:4004/users/pornombre/Ruben
    //resuesta:
    //[{"id":4,"firstName":"Ruben","lastName":"Diaz Silvan"}]
    @GetMapping("/pornombre/{firstName}")
    public List<User> getFirstName (@PathVariable String firstName)
    {
    	return userService.getFirstName(firstName);
     
    }
    @GetMapping("/agrupada_nombre/")
    public List<Object[]> Consulta_agrupada_nombres ()
    {
    	return userService.Agrupar_por_nombre();
     
    }
    
    
    
}
