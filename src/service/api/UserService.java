package service.api;

import java.util.List;

import dto.UserDTO;
import entity.User;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public interface UserService {

    UserDTO findByLoginAndPassword(String login, String password);

    UserDTO findByLoginAndEmail(String login, String email);

    UserDTO create(UserDTO user);

    boolean delete(Integer id);

    UserDTO update( int i, UserDTO newUser);

	UserDTO create(User user);
     
	  UserDTO FindById(int id);
    
	
	List<UserDTO> getAllUsersDTO();
}
