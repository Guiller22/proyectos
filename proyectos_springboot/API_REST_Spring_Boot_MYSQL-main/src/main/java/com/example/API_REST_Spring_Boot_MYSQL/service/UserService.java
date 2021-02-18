package com.example.API_REST_Spring_Boot_MYSQL.service;

import com.example.API_REST_Spring_Boot_MYSQL.model.User;
import com.example.API_REST_Spring_Boot_MYSQL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {

        userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }
    //estas 2 funciones estan creadas en el interface JPA
   public List<User> getLastname(String Lastname)
    {
      return userRepository.findByLastName(Lastname);
    }
   public List<User> getFirstName(String FirstName)
   {
     return userRepository.Consulta_por_Nombre(FirstName);
   }
   public List<Object[]> Agrupar_por_nombre()
   {
     return userRepository.Consulta_Agrupada_Nombre();
   }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
