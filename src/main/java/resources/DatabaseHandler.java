package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {

//	String host = "localhost";
//	String port = "3306";
	public Connection con;
//	Statement statement;

	public DatabaseHandler() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Gurpreet@123");
//		statement = con.createStatement();

	}

	public void insertNewCustomer(int id, String name, String gender, String dob, String address, String city,
			String state, String pin, String phone, String email, String password) throws SQLException {

		Statement statement = con.createStatement();

		String insertCustomer = "insert into customer values(" + id + ",'" + name + "','" + gender + "','" + dob + "','"
				+ address + "','" + city + "','" + state + "'," + pin + ",'" + phone + "','" + email + "','" + password
				+ "')";

		statement.execute(insertCustomer);

//		PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//		ps.setInt(1, id);
//		ps.setString(2, name);
//		ps.setString(3, gender);
//		ps.setString(4, dob);
//		ps.setString(5, address);
//		ps.setString(6, city);
//		ps.setString(7, state);
//		ps.setInt(8, Integer.parseInt(pin));
//		ps.setString(9, phone);
//		ps.setString(10, email);
//		ps.setString(11, password);
	}
}
