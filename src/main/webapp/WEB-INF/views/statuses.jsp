<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>statuses</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="Status" id="StatusForm" method="get"  modelAttribute="statuses">
	<table>
	 <tr>
	  <td width="50">Id</td>
	  <td width="150">First Name</td>
	 </tr>
	 <c:forEach items="${statuses}" var="status">
	  <tr>
	   <td><c:out value="${status.id}" /></td>
	   <td><spring:url value="/statuses/${status.id}" var="editstatus" />
	  	   <a href="${editstatus}" title="Edit Status">${status.name}</a></td>
	  </tr>
	 </c:forEach>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>