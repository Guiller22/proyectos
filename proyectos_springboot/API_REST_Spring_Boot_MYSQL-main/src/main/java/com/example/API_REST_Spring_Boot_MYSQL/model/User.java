package com.example.API_REST_Spring_Boot_MYSQL.model;

import javax.persistence.*;
//Por ejemplo, si queremos que una clase sea persistente en base de datos, 
//le pondremos la anotación @Entity. A su atributo que haga de clave primaria 
//le pondremos @Id y @GeneratedValue(que no es este caso). 
//Todos los atributos de la clase anotada con @Entity se guardarán a su vez
//en base de datos, por lo que no es necesario anotarlos uno a uno
@Entity
@Table(name = "users") //nombre de la tabla en la BD
public class User {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    private String firstName;
    private String lastName;
    public User() {
    }

    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
