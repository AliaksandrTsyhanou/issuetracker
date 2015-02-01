<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<%-- <style type="text/css"> <%@include file="/resources/css/form.css" %> </style> --%>
<style type="text/css"> <%@include file="/resources/css/displaytag.css" %> </style>
<!-- <link rel="stylesheet" type="text/css" href="/resources/css/displaytag.css"/> -->
<title>issues</title></head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<div>
	<spring:url value="/issues/" var="editissue" />
	<display:table name="issues" sort="list" pagesize="10" id="table" export="true" requestURI="">
        	<display:column title="id" sortable="true" group="1" headerClass="sortable">
			   <a href="${editissue}${table.id}" title="Edit Issue">${table.id}</a>
			</display:column>
			<display:column property="summary" title="Summary" group="1" sortable="true" headerClass="sortable"/>
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

<script type="text/javascript"> <%@include file="/resources/js/jquery-1.7.1.min.js"%> </script>
<script type="text/javascript"> <%@include file="/resources/js/jquery.dataTables.min.js"%> </script>
<script type="text/javascript">
  $(function() {
   /* #txt is display table id & employee_search_ class is field id which you want to filter */
   var oTable = $('#table').dataTable({
    "bPaginate" : false,
    "bLengthChange" : false,
    "bFilter" : true,
    "bSort" : false,
    "bInfo" : false,
    "bAutoWidth" : false,
    "bStateSave" : false
   });
   $("thead input").keyup(function() {
    oTable.fnFilter(this.value, $("thead input").index(this));
   });
 
   $("thead input").focus(function() {
    if (this.className == "employee_search_") {
     this.className = "";
     this.value = "";
    }
   });
 
   $("thead input").blur(function(i) {
    if (this.value == "") {
     this.className = "employee_search_";
     this.value = asInitVals[$("thead input").index(this)];
    }
   });
  });
 </script>

</body></html>