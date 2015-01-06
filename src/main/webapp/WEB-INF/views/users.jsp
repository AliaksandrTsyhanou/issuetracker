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
	</c:if>

	<table>
	 <tr>
	  <td width="50">Id</td>
	  <td width="150">First Name</td>
	  <td width="150">Last Name</td>
	  <td width="150">emailaddress</td>
	  <td width="100">password</td>
	  <td width="150">role</td>
	  <td width="100"></td>
<!-- 	  <td width="100">role Name</td> -->
	 </tr>
	 <c:forEach items="${users}" var="user">
	  <tr>
	   <td><c:out value="${user.id}" /></td>
	   <td><c:out value="${user.firstname}" /></td>
	   <td><c:out value="${user.lastname}" /></td>
	   <td><c:out value="${user.emailaddress}" /></td>
	   <td><c:out value="${user.password}" /></td>
	   <td><c:out value="${user.role.name}" /></td> 
	   <td><spring:url value="/users/${user.id}" var="user" />
	    <a href="${user}" title="Edit User">Edit</a></td>
	  </tr>
	 </c:forEach>
	  <tr>
	   <td colspan="7">
	 	<spring:url value="/users/add" var="adduser" />
	 	<a href="${adduser}" title="Add User">Add User</a>
	   </td>
	  </tr>
	</table>
	
	
	
</h3>
</form:form>
<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>