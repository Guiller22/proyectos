package com.example.API_REST_Spring_Boot_MYSQL.repository;

import java.util.List;

import com.example.API_REST_Spring_Boot_MYSQL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public final static String FIND_BY_FirstName_QUERY = "SELECT u " + 
            "FROM User u  " +
            "WHERE u.firstName = :firstName";
	
	/**
     * Find persons like first name.
     */
    public List<User> findByFirstNameLike(String firstName);

    /**
     * Find persons by last name.
     */
    public List<User> findByLastName(String lastName);
    
    @Query(FIND_BY_FirstName_QUERY)
    public List<User> Consulta_por_Nombre(@Param("firstName") String FirstName);
    
    //otra forma de hacerlo
    @Query("SELECT u.firstName,count(u) as contadorpornombre from User u  group by u.firstName")
    public List<Object[]> Consulta_Agrupada_Nombre();
    
    
	
}
