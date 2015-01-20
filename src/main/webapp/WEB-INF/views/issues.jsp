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

<form:form name="Issues" id="IssuesForm" method="get"  modelAttribute="issues">
	<table>
	 <tr>
	  	<td width="150">id          </td>
	  	<td width="150">Summary     </td>
	  	<td width="150">Description </td>
	  	<td width="150">Status      </td>
	  	<td width="150">Type        </td>
	  	<td width="150">Priority    </td>
	  	<td width="150">Project     </td>
	  	<td width="150">Build found </td>
	  	<td width="150">Assignee    </td>	  	
	 </tr>
	 
	 <c:forEach items="${issues}" var="issue">
	  <tr>
	   	<td><c:out value="${issue.id}"            /></td>	 
	    <td><spring:url value="/issues/${issue.id}" var="editissue" />
	       <a href="${editissue}" title="Edit Issue">${issue.summary}</a></td>
	   	<td><c:out value="${issue.description}"   /></td>
	   	<td><c:out value="${issue.status.name}"   /></td>	   
	   	<td><c:out value="${issue.type.name}"     /></td>
	   	<td><c:out value="${issue.priority.name}" /></td>
	   	<td><spring:url value="/projects/${issue.project.id}" var="editproject" />
	       <a href="${editproject}" title="Edit Project">${issue.project.name}</a></td>
	   	<td><c:out value="${issue.build.name}" /></td>
	   	<td><c:out value="${issue.user.emailaddress}" /></td>
	  </tr>
	 </c:forEach>
	  <tr>
	   <td colspan="9">
	 	<spring:url value="/issues/add" var="addissues" />
	 	<a href="${addissues}" title="Add issues">Add</a>
	   </td>
	  </tr>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>