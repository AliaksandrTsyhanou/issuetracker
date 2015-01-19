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

<form:form name="Manuals" id="ManualsForm" method="get"  modelAttribute="manuals">
<table>
	 <tr>
	  <td width="50">Id</td>
	  <td width="150">name ${manualname}</td>
	 </tr>
	 <c:forEach items="${manuals}" var="manual">
	  <tr>
	   <td><c:out value="${manual.id}" /></td>
	   <td><spring:url value="/manuals/${manualname}/${manual.id}" var="editmanual" />
	  	   <a href="${editmanual}" title="Edit Manual">${manual.name}</a></td>
	  </tr>
	 </c:forEach>
	 <c:if test="${isAllowAdditions}">
		 <tr>
		   <td colspan="2">
		 	<spring:url value="/manuals/${manualname}/add" var="addmanual" />
		 	<a href="${addmanual}" title="Add Manual">Add</a>
		   </td>
		  </tr>
	 </c:if>	 
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>