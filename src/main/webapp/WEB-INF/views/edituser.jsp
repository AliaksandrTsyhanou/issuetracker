<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit user</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<%-- <c:url var="saveUrl" value="/users/${userEditDto.userId}" /> --%>
<%-- action="${saveUrl}" --%>
<form:form name="editUser" id="editUserForm" method="POST"  modelAttribute="userEditDto">
	<form:input path="userId" type="hidden"/>
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
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<tr>
			<td> <form:label path="roleId">role</form:label> </td>
			<td> <form:select path="roleId">
				 <form:options items="${roles}" itemValue="id" itemLabel="name"/>
				 </form:select> </td>
			<td> <form:errors path="roleId" id="errmsg"/> </td>
		<tr>
		<tr>
			<td colspan="3"> <spring:url value="/users/${userEditDto.userId}/changepassword/" var="changepassword" />
			<a href="${changepassword}" title="changepassword">Change Password</a> <br><br> </td>
		</tr>
	</sec:authorize>		
	
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>
	
</table>


			
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>

<!-- cssStyle="color:red" -->