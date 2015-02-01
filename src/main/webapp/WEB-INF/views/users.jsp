<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>users</title></head>
<style type="text/css"> <%@include file="/resources/css/displaytag.css" %> </style>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div>
	<spring:url value="/users/" var="edituser" />
	<display:table name="users" sort="list" id="table" export="true" requestURI="" style="margin: auto;">
       	<display:column title="Id" sortable="true" headerClass="sortable" style="width: 20%">
		   <a href="${edituser}${table.id}" title="Edit Issue">${table.id}</a>
		</display:column>
		<display:column property="firstname" title="First Name" sortable="true" headerClass="sortable"  style="width: 20%" />
		<display:column property="lastname" title="Last Name" sortable="true" headerClass="sortable"  style="width: 20%" />
		<display:column property="emailaddress" title="emailaddress" sortable="true" headerClass="sortable"  style="width: 20%" />
		<display:column property="role.name" title="role" sortable="true" headerClass="sortable"  style="width: 20%" />
	</display:table>
</div>
<div class="add">
  	<spring:url value="/users/add" var="adduser" />
	<input type="button" value="Add" onClick='location.href="${adduser}"'>
</div>
<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body></html>