<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>projects</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>

<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div>
	<spring:url value="/issues/" var="editissue" />
	             <a href="${editissue}" title="Edit Issue">${issue.summary}</a>
	<display:table name="issues" sort="list" pagesize="10" id="row" export="true" requestURI="" >
        	<display:column title="id" sortable="true" group="1" headerClass="sortable">
			   <a href="${editissue}${row.id}" title="Edit Issue">${row.id}</a>
			</display:column>
			<display:column property="summary" title="Summary" group="1" sortable="true" headerClass="sortable" />
			<display:column property="description" title="Description" group="1" sortable="true" headerClass="sortable" />
			<display:column property="status.name" title="Status" group="1" sortable="true" headerClass="sortable" />
			<display:column property="type.name" title="Type" group="1" sortable="true" headerClass="sortable" />
			<display:column property="priority.name" title="Priority" group="1" sortable="true" headerClass="sortable" />
			<display:column property="project.name" title="Project" group="1" sortable="true" headerClass="sortable" />
			<display:column property="build.name" title="Build found" group="1" sortable="true" headerClass="sortable" />
			<display:column property="assignee.emailaddress" title="Assignee" group="1" sortable="true" headerClass="sortable" />
			<display:column property="createdate" title="created date" group="1" sortable="true" headerClass="sortable" />		
			<display:column property="modifydate" title="modify date" group="1" sortable="true" headerClass="sortable" />			
	</display:table>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body></html>