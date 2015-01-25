<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit project</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="editProject" id="editProjectForm" method="POST"  modelAttribute="project">
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
		<td> <a href="${requestScope['javax.servlet.forward.request_uri']}/builds" title="Builds">Builds</a> </td>
		<td> <form:select path="build.id">
			 <form:options items="${builds}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="build.id" id="errmsg"/> </td>		
	</tr>
	
	<tr>
		<td> <form:label path="manager.id">Manager</form:label> </td>
		<td> <form:select path="manager.id">
			 <form:options items="${mangers}" itemValue="id" itemLabel="emailaddress"/>
			 </form:select> </td>
		<td> <form:errors path="manager.id" id="errmsg"/> </td>
	</tr>
		
<!-- 	<tr> -->
<%-- 		<td colspan="3"> <spring:url value="/projects/" var="projects" /> --%>
<%-- 		<a href="${projects}" title="projects">projects</a> <br><br> </td> --%>
<!-- 	</tr> -->
	<tr>
		<td colspan="3"> 		
			<a href="${requestScope['javax.servlet.forward.request_uri']}/.." title="Return for list">Return for list</a>
	  	</td>
	</tr>
			
	
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>
	
</table>


			
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>

<!-- cssStyle="color:red" -->