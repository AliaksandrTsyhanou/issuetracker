<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>add user</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="addResolution" id="addResolutionForm" method="POST" modelAttribute="resolution">
<table>
	<tr>
		<td> <form:label path="name">Name:</form:label> </td>
		<td> <form:input path="name"/> </td>
		<td> <form:errors path="name" id="errmsg"/> </td>
	<tr>
	<tr>
		<td colspan="3"> <input type="submit" value="Add Resolution"/> </td>
	</tr>
	<tr>
		<td colspan="3"> <spring:url value="/resolutions/" var="resolutions" />
			<a href="${resolutions}" title="resolutions">resolutions</a> </td>
	</tr>
</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>