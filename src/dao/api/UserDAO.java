package dao.api;

import java.util.List;

import dto.UserDTO;
import entity.Product;
import entity.User;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public interface UserDAO {

    User findByLoginAndPassword(String login, String password);

    User findByLoginAndEmail(String login, String email);

    User create(User user);
    
    User FindById(int id);

    boolean delete(Integer id);

    User update(int i, User user);
    
    List<User> AllUsers();
    
  

}
