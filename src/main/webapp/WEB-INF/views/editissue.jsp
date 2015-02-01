<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit issue</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<script type="text/javascript"> <%@include file="/resources/js/updateselect.js"%> </script>

<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="editIssue" id="editIssueForm" method="POST"  modelAttribute="issue">
	<form:input path="id" type="hidden"/>
<table>
	<tr>
		<td> <form:label path="id">Id</form:label> </td>
		<td> <form:input path="id" readonly="true" disabled="${isClosed}"/> </td>
	</tr>
	<tr>
		<td> <form:label path="createdate">Create Date</form:label> </td>
		<td><fmt:formatDate value="${issue.createdate}" pattern="yyyy-MM-dd"/> </td>
	</tr>
	<tr>
		<td> <form:label path="creator.emailaddress">Create By</form:label> </td>
		<td> <c:out value="${issue.creator.emailaddress}"/> </td>
	</tr>
	<tr>
		<td> <form:label path="modifydate">Modify Date</form:label> </td>
		<td><fmt:formatDate value="${issue.modifydate}" pattern="yyyy-MM-dd"/> </td>
	</tr>
	<tr>
		<td> <form:label path="modifier">Modify By</form:label> </td>
		<td> <c:out value="${issue.modifier.emailaddress}"/> </td>
	</tr>
	<tr>
		<td> <form:label path="summary">Summary</form:label> </td>
		<td> <form:input path="summary" disabled="${isClosed}"/> </td>
		<td> <form:errors path="summary" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="description">Description</form:label> </td>
		<td> <form:input path="description" disabled="${isClosed}"/> </td>
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
		<td> <form:label path="resolution.id">Resolution</form:label> </td>
		<td> <form:select path="resolution.id" disabled="${!isResolved}">
			 <form:options items="${resolutions}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="resolution.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="type.id">Type</form:label> </td>
		<td> <form:select path="type.id" disabled="${isClosed}">
			 <form:options items="${types}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="type.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="priority.id">Priority</form:label> </td>
		<td> <form:select path="priority.id" disabled="${isClosed}">
			 <form:options items="${priorities}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="priority.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="project.id">Project</form:label> </td>
		<td> <spring:url value="/projects/" var="projectbuilds" />
			 <form:select path="project.id" disabled="${isClosed}" 
			 onchange="updateSelect('${projectbuilds}', '/builds', this, 'selectFiltred')">
			 <form:options items="${projects}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="project.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="build.id">Builds</form:label> </td>
		<td> <form:select id="selectFiltred" path="build.id" disabled="${isClosed}">
			 <form:options items="${builds}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="build.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="assignee.id">Manager</form:label> </td>
		<td> <form:select path="assignee.id" disabled="${isClosed}">
			 <form:options items="${assignees}" itemValue="id" itemLabel="emailaddress"/>
			 </form:select> </td>
		<td> <form:errors path="assignee.id" id="errmsg"/> </td>
	</tr>
	
	<tr>
		<td colspan="3"> <spring:url value="/issues/" var="issues" />
		<a href="${issues}" title="issues">issues</a> <br><br> </td>
	</tr>	
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>	
</table>

<spring:url value="/files/issue/{id}" var="fileslinks" context="">
	<spring:param name="id" value="${issue.id}" />
</spring:url>
<spring:url value="/files/issue/${issue.id}" var="action"/>

</form:form>

<jsp:include page="${fileslinks}">
    <jsp:param name="action" value="${action}"/>
</jsp:include>


<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html> 