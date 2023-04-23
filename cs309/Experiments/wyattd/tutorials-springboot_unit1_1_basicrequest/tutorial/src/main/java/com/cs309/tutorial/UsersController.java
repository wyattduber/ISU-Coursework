package com.cs309.tutorial;

import java.sql.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping()
	public Object getUsers(@RequestBody String type) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","dc3be71680cf6d2e");
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT " + type + " FROM dataContent;");
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.close();
		return rs.getObject(1);
	}

	@PostMapping()
	public String createUser(@RequestBody int id, @RequestBody String name, @RequestBody int age) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","dc3be71680cf6d2e");
			PreparedStatement stmt = con.prepareStatement("INSERT INTO demoContent(id,name,age) VALUES (?,?,?)");
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, age);
			stmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Link Inserted with Values: id: " + id + ", name: " + name + ", age: " + age + "!";
	}

	@DeleteMapping()
	public String deleteUser() {
		return "HTTP DELETE request was sent";

	}

	@PutMapping()
	public String updateUser() {
		return "HTTP PUT request was sent";
	}

	private String sanitize(String string) {
		if (string.substring(string.length() - 1, string.length()).contains("=")) {
			string = string.substring(0, string.length() - 1);
		}

		return string;
	}

}
