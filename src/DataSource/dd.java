package DataSource;

import java.sql.SQLException;
import java.util.Date;

import entity.User;
import entity.UserRole;
import entity.UserSex;

public class dd {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MySQL app = new MySQL();
		app.run();
		
		User u = new User();
		u.setEmail("email");
		u.setFirstName("test1");
		u.setId(1);
		u.setLogin("login");
		u.setPassword("pass1");
		u.setSecondName("name2");
		u.setSex(UserSex.FEMALE);
		u.setRole(UserRole.USER);
		u.setBirthday(new Date());
		
		MySQL m = new MySQL(); 
		m.insertUser(u);
	 
	}

}
