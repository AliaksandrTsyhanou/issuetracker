<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>header</title>
</head>
<body>

	<sec:authorize access="isAuthenticated()">
		&nbsp;&nbsp; 
		Welcome <sec:authentication property="principal.username"/> 
		
		&nbsp;&nbsp; 
		<spring:url value="/users/changepassword" var="changepassword" />
		<a href="${changepassword}" title="changepassword">change password</a>
		
		&nbsp;&nbsp; 
		<spring:url value="/users/edit" var="edit" />
		<a href="${edit}" title="edit">Edit Profile</a>
		
		&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
		<a href="<c:url value="/logout"/>">Logout</a>		
	</sec:authorize>
	
	<sec:authorize access="isAnonymous()">
		&nbsp;&nbsp; 
		<spring:url value="/login" var="loginUrl" />
		<a href="${loginUrl}" title="Login">Login</a><br />
	</sec:authorize>
<hr>	
</body>
</html>