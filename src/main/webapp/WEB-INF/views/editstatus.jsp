<%@ page import="by.epam.lab.issuetracker.constants.ConstantsJSP" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit Status</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="editStatus" id="editStatusForm" method="POST"  modelAttribute="status">
	<form:input path="id" type="hidden"/>
<table>
	<tr>
		<td> <form:label path="name">Name:</form:label> </td>
		<td> <form:input path="name"/> </td>
		<td> <form:errors path="name" id="errmsg"/> </td>
	<tr>
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>	
</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>