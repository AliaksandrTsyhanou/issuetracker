<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>add issue</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<script type="text/javascript"> <%@include file="/resources/js/updateselect.js"%> </script>

<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="addIssue" id="addIssueForm" method="POST"  modelAttribute="issueDto">
	<form:input path="id" type="hidden"/>
<table>
	<tr>
		<td> <form:label path="summary">Summary</form:label> </td>
		<td> <form:input path="summary"/> </td>
		<td> <form:errors path="summary" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="description">Description</form:label> </td>
		<td> <form:input path="description"/> </td>
		<td> <form:errors path="description" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="status.id">Status</form:label> </td>
		<td> <form:select path="status.id">
			 <form:options items="${statuses}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="status.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="type.id">Type</form:label> </td>
		<td> <form:select path="type.id">
			 <form:options items="${types}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="type.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="priority.id">Priority</form:label> </td>
		<td> <form:select path="priority.id">
			 <form:options items="${priorities}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="priority.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="project.id">Project</form:label> </td>
		<td> <spring:url value="/projects/" var="projectbuilds" />
			 <form:select path="project.id" onchange="updateSelect('${projectbuilds}', '/builds', this, 'selectFiltred')">
			 <form:options items="${projects}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="project.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="build.id">Builds</form:label> </td>
		<td> <form:select id="selectFiltred" path="build.id">
			 <form:options items="${builds}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="build.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="assignee.id">Assignee</form:label> </td>
		<td> <form:select path="assignee.id">
			 <form:options items="${assignees}" itemValue="id" itemLabel="emailaddress"/>
			 </form:select> </td>
		<td> <form:errors path="assignee.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td colspan="3"> 		
			<a href="${requestScope['javax.servlet.forward.request_uri']}/.." title="Return for list">Return for list</a>
	  	</td>
	</tr>
	
	<tr>
		<td colspan="3"> <input type="submit" value="Add"/> </td>
	</tr>	
</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html> 