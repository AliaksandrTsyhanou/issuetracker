<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>projects</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="Projects" id="ProjectsForm" method="get"  modelAttribute="projects">
	<table>
	 <tr>
	  	<td width="150">Name</td>
	  	<td width="150">Manager</td>
	  	<td width="150">Description</td>
	  	<td width="150">Build</td>
	 </tr>
	 <c:forEach items="${projects}" var="project">
	  <tr>
	   <td><spring:url value="/projects/${project.id}" var="editproject" />
	       <a href="${editproject}" title="Edit Project">${project.name}</a></td>
	   	<td><c:out value="${project.idmanager}" /></td>
	   	<td><c:out value="${project.description}" /></td>	   
	   	<td><c:out value="${project.build.name}" /></td>
	  </tr>
	 </c:forEach>
	  <tr>
	   <td colspan="7">
	 	<spring:url value="/projects/add" var="addprojects" />
	 	<a href="${addprojects}" title="Add Projects">Add</a>
	   </td>
	  </tr>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>