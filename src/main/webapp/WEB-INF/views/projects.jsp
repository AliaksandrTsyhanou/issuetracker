<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>projects</title></head>
<style type="text/css"> <%@include file="/resources/css/displaytag.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div>
	<spring:url value="/projects/" var="editproject" />
	<display:table name="projects" sort="list" pagesize="10" id="table" export="true" requestURI="">
        	<display:column title="Name" sortable="true" headerClass="sortable" style="width: 25%">
			   <a href="${editproject}${table.id}" title="Edit Issue">${table.name}</a>
			</display:column>
			<display:column property="manager.emailaddress" title="Manager" sortable="true" headerClass="sortable"  style="width: 25%" />
			<display:column title="Description" sortable="true" headerClass="sortable"  style="width: 25%" >
				<c:out value="${fn:substring(table.description, 0, 100)}"></c:out>
				<c:if test="${fn:length(table.description) gt 100}">...</c:if> 
			</display:column>
			<display:column property="build.name" title="Build" sortable="true" headerClass="sortable"  style="width: 25%" />
				
	</display:table>
</div>
<div class="add">
  	<spring:url value="/projects/add" var="addprojects" />
	<input type="button" value="Add" onClick='location.href="${addprojects}"'>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>

<script type="text/javascript"> <%@include file="/resources/js/jquery-1.7.1.min.js"%> </script>
<script type="text/javascript"> <%@include file="/resources/js/jquery.dataTables.min.js"%> </script>
</body></html>