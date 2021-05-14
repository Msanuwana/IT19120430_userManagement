package com;

import java.sql.*;

public class User {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_m","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	public String insertUser( String Fname, String Lname, String Email, String Address,String Phone_n ,String username ,String password)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = " insert into `users`(`Fname`,`Lname`,`Email`,`Address`,`Phone_n','username','password')"+ " values ( ?, ?, ?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, Fname);
	 preparedStmt.setString(3, Lname);
	 preparedStmt.setString(4, Email);
	 preparedStmt.setString(5, Address);
	 preparedStmt.setInt(6,Integer.parseInt(Phone_n));
	 preparedStmt.setString(7, username);
	 preparedStmt.setString(8, password);
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newUser = readUser();
	 output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
	 }
	catch (Exception e)
	{
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String readUser()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Fname</th>"
	 + "<th>Lname</th><th>Email</th>"
	 + "<th>Address</th><th>Phone_n</th>"
	 + "<th>Username</th><th>Password</th></tr>";
	 String query = "select * from `users`";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String Id = Integer.toString(rs.getInt("Id"));
	 String Fname = rs.getString("Fname");
	 String Lname = rs.getString("Lname");
	 String Email = rs.getString("Email");
	 String Address = rs.getString("Address");
	 String Phone_no = Integer.toString(rs.getInt("Phone_no"));
	 String username = rs.getString("username");
	 String password = rs.getString("password");
	
	// Add into the html table
	 output += "<tr><td>" + Id + "</td>";
	 output += "<td>" + Fname + "</td>";
	 output += "<td>" + Lname + "</td>";
	 output += "<td>" + Email + "</td>";
	 output += "<td>" + Address + "</td>";
	 output += "<td>" + Phone_no + "</td>";
	 output += "<td>" + username + "</td>";
	 output += "<td>" + password + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-Id='" + Id + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-itemid='" + Id + "'></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the users.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}

	
	public String updateUser(String Id, String Fname ,String Lname ,String Email ,String Address ,String Phone_n ,String username ,String password)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database";
	}
	// create a prepared statement
	String query = " UPDATE `users` SET `Fname`='"+Fname+"',`Lname`='"+Lname+"',`Email`='"+Email+"',`Address`='"+Address+"',`Phone_no`='"+Phone_n+"' + ' WHERE `Id`='"+Id+"'";
	PreparedStatement preparedStmt = con.prepareStatement(query);

	//execute the statement
	preparedStmt.execute();
	con.close();
	output = "Inserted successfully";
	}
	catch (Exception e)
	{
	output = "Error while inserting";
	System.err.println(e.getMessage());
	}
	return output;
	}

	
	public String deleteUser(String Id)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from `users` where Id=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(Id));
	// execute the statement
	preparedStmt.execute();
	con.close();
	String newUsers = readUser();
	output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
	}
	catch (Exception e)
	{
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the User.\"}";
	 System.err.println(e.getMessage());
	}
	return output;
	}	
}


