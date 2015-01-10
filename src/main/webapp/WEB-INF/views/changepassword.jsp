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

<form:form name="changePassword" id="changePasswordForm" method="POST" modelAttribute="changePasswordDto">
<table>
	<tr>
		<td> <form:label path="password">New Password</form:label> </td>
		<td> <form:input path="password"/> </td>
		<td> <form:errors path="password" id="errmsg"/> </td>
	<tr>
	<tr>
		<td> <form:label path="passwordConfirmation">Password Confirmation</form:label> </td>
		<td> <form:input path="passwordConfirmation"/> </td>
		<td> <form:errors path="passwordConfirmation" id="errmsg"/> </td>
	<tr>
	<tr>
		<td colspan="3"> <input type="submit" value="Change Password"/> </td>
	</tr>
	<tr>
		<td colspan="3"> <spring:url value="/users/" var="users" />
			<a href="${users}" title="users">users</a> </td>
	</tr>
</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>