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
<c:url var="saveUrl" value="/users/${userEditDto.id}" />

<form:form name="editUser" id="editUserForm" method="POST" action="${saveUrl}" modelAttribute="userEditDto">
<table>
	<tr>
		<td> <form:label path="firstname">First Name</form:label> </td>
		<td> <form:input path="firstname"/> </td>
		<td> <form:errors path="firstname" id="errmsg"/> </td>
	<tr>
	<tr>
		<td> <form:label path="lastname">Last Name</form:label> </td>
		<td> <form:input path="lastname"/> </td>
		<td> <form:errors path="lastname" id="errmsg"/> </td>
	<tr>
	<tr>
		<td> <form:label path="emailaddress">Email Address</form:label> </td>
		<td> <form:input path="emailaddress"/> </td>
		<td> <form:errors path="emailaddress" id="errmsg"/> </td>
	<tr>
	<tr>
		<td> <form:label path="roleId">role</form:label> </td>
		<td> <form:select path="roleId">
			 <form:options items="${roles}" itemValue="id" itemLabel="name"/>
			 </form:select><br><br> </td>
		<td> <form:errors path="roleId" id="errmsg"/> </td>
	<tr>
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>
	<tr>
		<td colspan="3"> <spring:url value="/users/" var="users" />
			<a href="${users}" title="users">back</a> </td>
	</tr>
</table>

<spring:url value="/users/${userEditDto.id}/changepassword/" var="changepassword" />
			<a href="${changepassword}" title="changepassword">Change Password</a>
			
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>

<!-- cssStyle="color:red" -->