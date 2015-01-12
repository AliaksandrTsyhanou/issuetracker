<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<html>
<head>
<title>Home</title>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp"/>

	<h1>Hello world!</h1>
		
		<br>
		<spring:url value="/users/" var="users" />
		<a href="${users}" title="users">users</a>
		
		<br><br>
		<spring:url value="/users/changepassword" var="changepassword" />
		<a href="${changepassword}" title="changepassword">change password</a>
		
		<br><br>
		<spring:url value="/users/edit" var="edit" />
		<a href="${edit}" title="edit">Edit Profile</a>
	
</body>
</html>
