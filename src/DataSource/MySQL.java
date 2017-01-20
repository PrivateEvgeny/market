package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import dto.ProductDTO;
import entity.Product;
import entity.ProductGroup;
import entity.Transaction;
import entity.User;
import entity.UserRole;
import entity.UserSex;

public class MySQL implements InterfaceInMemory {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/**
	 * The name of the database we are testing with (this default is installed
	 * with MySQL)
	 */
	private final String dbName = "test";

	/** The name of the table we are testing with */
	private final String tableName = "JDBC_TEST2";

	private static Connection con = null;

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = con;
		if (con == null) {

			Properties connectionProps = new Properties();
			connectionProps.put("user", this.userName);
			connectionProps.put("password", this.password);

			conn = DriverManager.getConnection(
					"jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);
			con = conn;
		}
		return conn;
	}

	public Connection getConnection(String dbname) throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException
	 *             If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it
											// fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void run() {

		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		// Create a table
		// exec(conn);
	}

	public void exec(Connection conn) {
		try {
			String createString = "CREATE TABLE " + this.tableName + " ( " + "ID INTEGER NOT NULL, "
					+ "NAME varchar(40) NOT NULL, " + "STREET varchar(40) NOT NULL, " + "CITY varchar(20) NOT NULL, "
					+ "STATE char(2) NOT NULL, " + "ZIP char(5), " + "PRIMARY KEY (ID))";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}

		// Drop the table
		try {
			String dropString = "DROP TABLE " + this.tableName;
			this.executeUpdate(conn, dropString);
			System.out.println("Dropped the table");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not drop the table");
			e.printStackTrace();
			return;
		}
	}

	public void insertUser(User user) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection connection = getConnection();
		preparedStatement = connection.prepareStatement(
				"INSERT INTO users( firstName, secondName, Birthday, login,password,email, sex, role) values(?,?,?,?,?,?,?,?)");

		// preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getSecondName());
		preparedStatement.setString(3, "1");
		preparedStatement.setString(4, user.getLogin());
		preparedStatement.setString(5, user.getPassword());
		preparedStatement.setString(6, user.getEmail());
		preparedStatement.setString(7, user.getSex().name());
		preparedStatement.setString(8, user.getRole().name());

		preparedStatement.executeUpdate();

		System.out.println("Выводим PreparedStatement");
		// while (result2.next()) {
		// System.out.println("Номер в выборке #" + result2.getRow()
		// + "\t Номер в базе #" + result2.getInt("id")
		// + "\t" + result2.getString("username"));
		// }

		// preparedStatement = connection.prepareStatement(
		// "INSERT INTO users(username) values(?)");
		// preparedStatement.setString(1, "user_name");
		// метод принимает значение без параметров
		// темже способом можно сделать и UPDATE
		// preparedStatement.executeUpdate();

	}

	public ProductGroup addProductGroup(ProductGroup pg) {

		PreparedStatement preparedStatement = null;
		Connection connection;
		try {
			connection = getConnection();

			preparedStatement = connection
					.prepareStatement("INSERT INTO productgroup( title, description) values(?,?)");

			// preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(1, pg.getTitle());
			preparedStatement.setString(2, pg.getDescription());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Выводим PreparedStatement");
		return pg;
	}

	@Override
	public User FindUserByLoginAndPassword(String login, String password) {
		User u = new User();
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM users where login =? AND password =?");

			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);

			ResultSet res = preparedStatement.executeQuery();

			if (res.next()) {
				u.setEmail(res.getString("email"));
				u.setFirstName(res.getString("firstName"));
				u.setId(res.getInt("id"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				u.setSecondName(res.getString("secondName"));
				u.setSex(UserSex.valueOf(res.getString("sex")));
				u.setRole(UserRole.valueOf(res.getString("role")));
				u.setBirthday(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;
	}

	@Override
	public boolean updateUser(User user) {
		boolean res = false;

		try {
			PreparedStatement preparedStatement = null;
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement(

					"UPDATE users SET firstName=?, secondName=?, Birthday=?, login=?,password=?,email=?, sex=?, role=? WHERE id=?");

			preparedStatement.setInt(9, user.getId());
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getSecondName());
			preparedStatement.setString(3, "1");
			preparedStatement.setString(4, user.getLogin());
			preparedStatement.setString(5, user.getPassword());
			preparedStatement.setString(6, user.getEmail());
			preparedStatement.setString(7, user.getSex().name());
			preparedStatement.setString(8, user.getRole().name());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public User addUser(User user) {
		try {
			insertUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean deleteUser(Integer id) {
		// TODO Auto-generated method stub
		// DELETE FROM `test`.`users` WHERE `users`.`id` = 1;
		boolean result = true;

		User u = new User();
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM users where id =?");

			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();
			System.out.println("delete: " + result);

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public User findById(int id) {

		User u = new User();
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM users where id =? ");

			preparedStatement.setInt(1, id);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {
				u.setEmail(res.getString("email"));
				u.setFirstName(res.getString("firstName"));
				u.setId(res.getInt("id"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				u.setSecondName(res.getString("secondName"));
				u.setSex(UserSex.valueOf(res.getString("sex")));
				u.setRole(UserRole.valueOf(res.getString("role")));
				u.setBirthday(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return u;

	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> l = new LinkedList<>();

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM users ");

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				User u = new User();
				u.setEmail(res.getString("email"));
				u.setFirstName(res.getString("firstName"));
				u.setId(res.getInt("id"));
				u.setLogin(res.getString("login"));
				u.setPassword(res.getString("password"));
				u.setSecondName(res.getString("secondName"));
				u.setSex(UserSex.valueOf(res.getString("sex")));
				u.setRole(UserRole.valueOf(res.getString("role")));
				u.setBirthday(new Date());
				l.add(u);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return l;
	}

	@Override
	public void putProductToProductGroup(ProductGroup productGroup, int counterProductGroups) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateGroup(ProductGroup productGroup) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createProduct(Product product) {
		boolean result = true;

		PreparedStatement preparedStatement = null;
		Connection connection;
		try {
			connection = getConnection();

			preparedStatement = connection
					.prepareStatement("INSERT INTO product( Description, count, price, title) values(?,?,?,?)");

			// preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(1, product.getDescription());
			preparedStatement.setInt(2, product.getCount());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setString(4, product.getTitle());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public boolean buyProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductGroup(int id) {
		boolean result = true;

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM productgroup where id =?");

			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();

			preparedStatement = connection.prepareStatement("DELETE FROM ppg where idPG =?");

			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		System.out.println("delete product Group: " + result);
		return result;
	}

	@Override
	public ProductGroup findProductGroupbyId(int id) {
		ProductGroup pg = new ProductGroup();
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM productgroup where id =? ");

			preparedStatement.setInt(1, id);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {

				pg.setDescription(res.getString("description"));
				pg.setTitle(res.getString("title"));
				pg.setId(res.getInt("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pg;
	}

	public ProductGroup findProductGroupbyName(String name) {
		ProductGroup pg = new ProductGroup();
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM productgroup where title =? ");

			preparedStatement.setString(1, name);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {
				pg.setDescription(res.getString("description"));
				pg.setTitle(res.getString("title"));
				pg.setId(res.getInt("id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pg;
	}

	@Override
	public List<ProductGroup> getAllProductGroups() {
		List<ProductGroup> newList = new LinkedList<>();

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM productgroup ");

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {
				ProductGroup pg = new ProductGroup();
				pg.setDescription(res.getString("description"));
				pg.setTitle(res.getString("title"));
				pg.setId(res.getInt("id"));
				newList.add(pg);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newList;
	}

	@Override
	public boolean updateGroup(int i, ProductGroup productGroup) {
		boolean res = true;

		try {
			PreparedStatement preparedStatement = null;
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement(

					"UPDATE productgroup SET title=?, description=? WHERE id=?");

			preparedStatement.setInt(3, i);
			preparedStatement.setString(1, productGroup.getTitle());
			preparedStatement.setString(2, productGroup.getDescription());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	@Override
	public Product findProductById(int i) {
		Product newProduct = new Product();

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM product where id =? ");

			preparedStatement.setInt(1, i);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {

				newProduct.setDescription(res.getString("description"));
				newProduct.setTitle(res.getString("title"));
				newProduct.setId(res.getInt("id"));
				newProduct.setCount(res.getInt("count"));
				newProduct.setPrice(res.getDouble("price"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newProduct;
	}

	@Override
	public Product findProductByName(String name) {
		Product newProduct = new Product();

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM product where title =? ");

			preparedStatement.setString(1, name);

			ResultSet res = preparedStatement.executeQuery();
			if (res.next()) {

				newProduct.setDescription(res.getString("description"));
				newProduct.setTitle(res.getString("title"));
				newProduct.setId(res.getInt("id"));
				newProduct.setCount(res.getInt("count"));
				newProduct.setPrice(res.getDouble("price"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newProduct;
	}

	@Override
	public boolean addGroupToProduct(ProductGroup newProGroup, Product newPro) {
		boolean result = true;
		if (newProGroup != null && newPro != null) {

			PreparedStatement preparedStatement = null;
			Connection connection;
			try {
				connection = getConnection();

				preparedStatement = connection.prepareStatement("INSERT INTO ppg( idP, idPG) values(?,?)");

				// preparedStatement.setInt(1, user.getId());
				preparedStatement.setInt(1, newPro.getId());
				preparedStatement.setInt(2, newProGroup.getId());

				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			}

		}

		return result;
	}

	@Override
	public List<Integer> getAllIdGroupinProduct(int i) {

		List<Integer> newListId = new LinkedList<>();

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT idPG FROM ppg WHERE idP=? ");

			preparedStatement.setInt(1, i);
			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {

				newListId.add(res.getInt("idPG"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newListId;
	}

	@Override
	public boolean deleteProduct(Integer id) {
		boolean result = true;

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM product where id =?");

			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();

			preparedStatement = connection.prepareStatement("DELETE FROM ppg where idP =?");

			preparedStatement.setInt(1, id);
			result = preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		System.out.println("delete product id: " + id + ":" + result);
		return result;
	}

	public List<Product> findAllProducts() {

		PreparedStatement preparedStatement = null;
		List<Product> newList = new LinkedList<>();
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM product");

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {

				Product newProduct = new Product();
				newProduct.setDescription(res.getString("description"));
				newProduct.setTitle(res.getString("title"));
				newProduct.setId(res.getInt("id"));
				newProduct.setCount(res.getInt("count"));
				newProduct.setPrice(res.getDouble("price"));
				newList.add(newProduct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}

	@Override
	public Product updateProduct(Product product) {
		boolean res = true;

		try {

			PreparedStatement preparedStatement = null;
			Connection connection = getConnection();
			preparedStatement = connection
					.prepareStatement("UPDATE product SET Description=?, count=?, price=?, title=? WHERE id=?");

			preparedStatement.setString(1, product.getDescription());
			System.out.println("count=" + product.getCount());
			preparedStatement.setInt(2, product.getCount());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setString(4, product.getTitle());
			preparedStatement.setInt(5, product.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Product updateProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteGroupDTOfromProduct(Integer id, int id2) {
		boolean result = true;

		PreparedStatement preparedStatement = null;
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM ppg WHERE idP =? AND idPG=?");

			preparedStatement.setInt(1, id2);
			preparedStatement.setInt(2, id);
			result = preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	@Override
	public List<Product> GetAllProductInGroup(Integer id) {
		PreparedStatement preparedStatement = null;
		List<Product> newList = new LinkedList<>();
		try {
			Connection connection = getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT p.*, pg.* FROM product p LEFT JOIN ppg pg on p.id=pg.idP where pg.idPG=?");

			preparedStatement.setInt(1, id);

			ResultSet res = preparedStatement.executeQuery();
			while (res.next()) {

				Product newProduct = new Product();
				newProduct.setDescription(res.getString("description"));
				newProduct.setTitle(res.getString("title"));
				newProduct.setId(res.getInt("id"));
				newProduct.setCount(res.getInt("count"));
				newProduct.setPrice(res.getDouble("price"));
				newList.add(newProduct);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}

	@Override
	public List<Transaction> getAllTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionByUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTransaction(Transaction tr)  {
		PreparedStatement preparedStatement = null;
		boolean result= true;
		try{Connection connection = getConnection();
		preparedStatement = connection.prepareStatement(
				"INSERT INTO transaction( product, count, price, date,user) values(?,?,?,?,?)");

		// preparedStatement.setInt(1, user.getId());
		preparedStatement.setInt(1, tr.getProduct().getId());
		preparedStatement.setInt(2, tr.getProductCount());
		preparedStatement.setDouble(3, tr.getProductPrice());
		preparedStatement.setString(4, "1");
		preparedStatement.setInt(5, tr.getUser().getId());
		
		
		
		
		preparedStatement.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
			result= false;
		}
		System.out.println("Выводим PreparedStatement");
		// while (result2.next()) {
		// System.out.println("Номер в выборке #" + result2.getRow()
		// + "\t Номер в базе #" + result2.getInt("id")
		// + "\t" + result2.getString("username"));
		// }

		// preparedStatement = connection.prepareStatement(
		// "INSERT INTO users(username) values(?)");
		// preparedStatement.setString(1, "user_name");
		// метод принимает значение без параметров
		// темже способом можно сделать и UPDATE
		// preparedStatement.executeUpdate();
		return result;
	}
}
