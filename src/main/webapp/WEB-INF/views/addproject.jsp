<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>add project</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="addProject" id="editProjectForm" method="POST"  modelAttribute="project">
	<form:input path="id" type="hidden"/>
<table>
	<tr>
		<td> <form:label path="name">Name</form:label> </td>
		<td> <form:input path="name"/> </td>
		<td> <form:errors path="name" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="description">Description</form:label> </td>
		<td> <form:input path="description"/> </td>
		<td> <form:errors path="description" id="errmsg"/> </td>
	</tr>
	
	<tr>
		<td> <form:label path="build.name">Build</form:label> </td>
		<td> <form:input path="build.name"/> </td>
		<td> <form:errors path="build.name" id="errmsg"/> </td>
	</tr>

	<tr>
		<td> <form:label path="manager.id">Manager</form:label> </td>
		<td> <form:select path="manager.id">
			 <form:options items="${mangers}" itemValue="id" itemLabel="emailaddress"/>
			 </form:select> </td>
		<td> <form:errors path="manager.id" id="errmsg"/> </td>
	</tr>
		
	<tr>
		<td colspan="3"> <spring:url value="/projects/" var="projects" />
		<a href="${projects}" title="projects">projects</a> <br><br> </td>
	</tr>
			
	
	<tr>
		<td colspan="3"> <input type="submit" value="add"/> </td>
	</tr>
	
</table>


			
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>

<!-- cssStyle="color:red" -->