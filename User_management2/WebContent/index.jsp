<%@ page import="com.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>



<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="Views/bootstrap.min.css">

<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/usrs.js"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<h1>User Management</h1>
<form id="formUser" name="formUser">
 First Name:
 <input id="fname" name="fname" type="text"
 class="form-control form-control-sm">
 <br> Last Name:
 <input id="Lname" name="Lname" type="text"
 class="form-control form-control-sm">
 <br> Email:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br> Address:
 <input id="address" name="address" type="text"
 class="form-control form-control-sm">
 <br> Phone No:
 <input id="phone_no" name="phone_no" type="text"
 class="form-control form-control-sm">
 <br> Username:
 <input id="username" name="username" type="text"
 class="form-control form-control-sm">
 <br> Password:
 <input id="password" name="password" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidUserIDSave"
 name="hidUserIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
    <div id="alertError" class="alert alert-danger"></div>

<br>

<br><br>

<div id="divUserGrid">
 <%
 User userObj = new User();
 out.print(userObj.readUser());
 %>
</div>

</body>
</html>