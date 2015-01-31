<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
	   <sec:authorize access="isAuthenticated()">
	   	<td><spring:url value="/projects/${project.id}" var="editproject" />
	       <a href="${editproject}" title="Edit Project">${project.name}</a></td>
	   </sec:authorize>
	    <sec:authorize access="isAnonymous()">
	   		<td><c:out value="${project.name}"/></td>
	    </sec:authorize>
	    <td><c:out value="${project.manager.emailaddress}" /></td>
	   	<td><c:out value="${project.description}" /></td>	   
	   	<td><c:out value="${project.build.name}" /></td>
	  </tr>
	 </c:forEach>
	  
	  <sec:authorize access="hasRole('ROLE_ADMIN')">
	  <tr>
	   <td colspan="7">
	 	<spring:url value="/projects/add" var="addprojects" />
	 	<a href="${addprojects}" title="Add Projects">Add</a>
	   </td>
	  </tr>
	  </sec:authorize>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>