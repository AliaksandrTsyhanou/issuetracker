<%@ page import="by.epam.lab.issuetracker.constants.ConstantsJSP" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>users</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<form:form name="Users" id="UsersForm" method="get"  modelAttribute="users">

<h3>
	<c:if test="${not empty errorMessage}">
		<c:out value="${errorMessage}"/>
		<br>
	</c:if>

	<br>
	<spring:url value="/adduser/" var="adduser" />
	<a href="${adduser}" title="Add User">Add User</a>
	<br>
		
 	<c:forEach items="${users}" var="user">
   	 	<br>
   	 	<spring:url value="/users/${user.id}" var="user" />
	    <a href="${user}" title="Edit User">Edit User</a>
   	 	<c:out value="${user}" />
   	 	<br>
 	</c:forEach>
	
</h3>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>