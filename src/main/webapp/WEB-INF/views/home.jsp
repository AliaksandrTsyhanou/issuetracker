<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Home</title>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp"/>

	<h1>Hello world!</h1>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<br>
			<spring:url value="/users/" var="users" />
			<a href="${users}" title="users">users</a>
		</sec:authorize>	

		
		<br><br>
		<spring:url value="/users/changepassword" var="changepassword" />
		<a href="${changepassword}" title="changepassword">change password</a>
		
		
		<sec:authorize access="isAuthenticated()">
			<br><br>
			<spring:url value="/users/edit" var="edit" />
			<a href="${edit}" title="edit">Edit Profile</a>
		</sec:authorize>

	
</body>
</html>
