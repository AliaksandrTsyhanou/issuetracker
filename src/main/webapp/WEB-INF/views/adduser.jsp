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
<form:form name="addUser" id="addUserForm" method="POST" modelAttribute="user">

<h3>
<c:if test="${not empty errorMessage}">
<c:out value="${errorMessage}"/>
<br>
</c:if>
	 
	<form:label path="firstname">firstname</form:label>
	<form:input path="firstname"/><br><br>
	<form:label path="lastname">lastname</form:label>
	<form:input path="lastname"/><br><br>
	<form:label path="emailaddress">emailaddress</form:label>
	<form:input path="emailaddress"/><br><br>
	<form:label path="password">password</form:label>
	<form:input path="password"/><br><br>
	<form:label path="role.id">role</form:label>
    <form:select path="role.id">
		<form:options items="${roles}" itemValue="id" itemLabel="name"/>
	</form:select><br><br>
	
	<input type="submit" value="add User"/> &nbsp;&nbsp;
</h3>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>