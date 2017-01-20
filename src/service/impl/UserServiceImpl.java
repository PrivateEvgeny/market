package service.impl;

import java.util.LinkedList;
import java.util.List;

import dao.api.UserDAO;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import entity.User;
import helper.Transformer;
import service.api.UserService;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();
    public int counter =0;
    @Override
    public UserDTO findByLoginAndPassword(String login, String password) {
        User user = userDAO.findByLoginAndPassword(login, password);
        System.out.println("find:"+ user.toString());
        UserDTO userDTO = Transformer.transformUserToUserDTO(user);
        if (userDTO == null){
        	userDTO = null;
        }
        return userDTO;
    }

    @Override
    public UserDTO findByLoginAndEmail(String login, String email) {
        return null;
    }

    @Override
    public UserDTO create(User user) {
    	UserDTO u = Transformer.transformUserToUserDTO(user);
    	User userNew =userDAO.create(user);
    	
    	return u;
    }

    @Override
    public boolean delete(Integer id) {
        boolean res = userDAO.delete(id);
        return res;
    }

    @Override
    public UserDTO update( int i, UserDTO newUser) {
    	if (newUser!=null){
    	User u = Transformer.transformUserDTOToUser(newUser);
		userDAO.update(i, u);}
    	return newUser;
    }

	public UserDTO create(UserDTO user) {
		counter++;
		user.setId(counter);
		User u = Transformer.transformUserDTOToUser(user);
		User userNew =userDAO.create(u);
		return user;
	}

	@Override
	public UserDTO FindById(int id) {
		UserDTO res = null;
		 User user = userDAO.FindById(id);
		 res = Transformer.transformUserToUserDTO(user);
		
		System.out.println("finding to update:" + res);
		return res;
	}

	@Override
	public List<UserDTO> getAllUsersDTO() {
		List<UserDTO> l = new LinkedList<>();
		List<User> lUsers = new LinkedList<>();
		lUsers= userDAO.AllUsers();
		l = Transformer.ListUserToUSERDTO(lUsers);
		return l;
	}
}
