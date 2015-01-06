<%@ page import="by.epam.lab.issuetracker.constants.ConstantsJSP" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>add user</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<c:url var="saveUrl" value="/users/${user.id}" />
<form:form name="editUser" id="editUserForm" method="POST" action="${saveUrl}" modelAttribute="user">

<h3>
	<form:label path="firstname">firstname</form:label>
	<form:input path="firstname"/>
	<form:errors path="firstname" id="errmsg"/><br><br>
	<form:label path="lastname">lastname</form:label>
	<form:input path="lastname"/>
	<form:errors path="lastname" id="errmsg"/><br><br>
	<form:label path="emailaddress">emailaddress</form:label>
	<form:input path="emailaddress"/>
	<form:errors path="emailaddress" id="errmsg"/><br><br>
	<form:label path="password">password</form:label>
	<form:input type="password" path="password"/>
	<form:errors path="password" id="errmsg"/><br><br>
    <form:label path="role.id">role</form:label>
    <form:select path="role.id">
		<form:options items="${roles}" itemValue="id" itemLabel="name"/>
	</form:select><br><br>
	
	<input type="submit" value="Save Changes"/> &nbsp;&nbsp;
	

		<spring:url value="/users/" var="users" />
		<a href="${users}" title="users">back</a>
</h3>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>

<!-- cssStyle="color:red" -->