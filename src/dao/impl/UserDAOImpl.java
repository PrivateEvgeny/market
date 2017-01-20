package dao.impl;

import java.util.LinkedList;
import java.util.List;

import DataSource.InMemoryDatabase;
import DataSource.InterfaceInMemory;
import DataSource.MySQL;
import dao.api.UserDAO;
import entity.User;

/**
 * Created by anton.kovalenko on 10/10/16.
 */
public class UserDAOImpl implements UserDAO{
private MySQL inmem = new MySQL();
    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = null;
        user = inmem.FindUserByLoginAndPassword(login, password);
        return user;
    }

    @Override
    public User findByLoginAndEmail(String login, String email) {
        return new User();
    }

    @Override
    public User create(User user) {
        return inmem.addUser(user);
    }

    @Override
    public boolean delete(Integer id) {
        return inmem.deleteUser(id);
    }

    @Override
    public User update(int i, User user) {
    	
    	if (inmem.findById(i) != null){
    		user.setId(i);
    		inmem.updateUser(user);
    		
    	}else
    	{
    		user= null;
    		System.out.println("id =" + i + " not found");
    	}
    	return user;
         
    }
    
    public User update(int id) {
       User user = new User();
    	if (inmem.findById(id) != null){
    		inmem.updateUser(user);
    		
    	}else
    	{
    		user= null;
    		System.out.println("id =" + id + " not found");
    	}
    	return user;
        
   }

	@Override
	public User FindById(int id) {
		 User user = null;
		 user = inmem.findById(id);
		return user;
	}

	@Override
	public List<User> AllUsers() {
		List<User> l = new LinkedList<>();
	     l = inmem.getAllUsers();
		return l;
	}

	
}
