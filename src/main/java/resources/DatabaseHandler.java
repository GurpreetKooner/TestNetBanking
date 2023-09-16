package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabaseHandler {

//	String host = "localhost";
//	String port = "3306";
	public Connection con;
	Statement statement;

	public DatabaseHandler() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "Gurpreet@123");
		statement = con.createStatement();

	}

	public void insertNewCustomer(int id, String name, String gender, String dob, String address, String city,
			String state, String pin, String phone, String email, String password) throws SQLException {

		String insertCustomer = "insert into customer values(" + id + ",'" + name + "','" + gender + "','" + dob + "','"
				+ address + "','" + city + "','" + state + "'," + pin + ",'" + phone + "','" + email + "','" + password
				+ "')";

		statement.execute(insertCustomer);
	}

	public void updateCustomerInfo(String id, List<String> columnNames, List<String> columnValues) throws SQLException {

		String updateCustomer = "update customer set ";

		for (int i = 0; i < columnNames.size(); i++) {
			updateCustomer += (columnNames.get(i) + "= '" + columnValues.get(i) + "'");

			if (i != columnNames.size() - 1) {
				updateCustomer += " ,";
			}
		}

		updateCustomer += ("where id = " + id);

		statement.execute(updateCustomer);
	}
	
	public void deleteCustomer(String id) throws SQLException {
		String deleteCustomer = "delete from customer where id = " + id;
		statement.execute(deleteCustomer);
	}
}
