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

<form:form name="Resolution" id="ResolutionForm" method="get"  modelAttribute="resolutions">
<table>
	 <tr>
	  <td width="50">Id</td>
	  <td width="150">name</td>
	 </tr>
	 <c:forEach items="${resolutions}" var="resolution">
	  <tr>
	   <td><c:out value="${resolution.id}" /></td>
	   <td><spring:url value="/resolutions/${resolution.id}" var="editresolution" />
	  	   <a href="${editresolution}" title="Edit Resolution">${resolution.name}</a></td>
	  </tr>
	 </c:forEach>
	 <tr>
	   <td colspan="2">
	 	<spring:url value="/resolutions/add" var="addresolution" />
	 	<a href="${addresolution}" title="Add Resolution">Add Resolution</a>
	   </td>
	  </tr>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>