<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>builds</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="builds" id="buildsForm" method="get"  modelAttribute="builds">
<table>
	 <tr>
	  <td width="50">Id</td>
	  <td width="150">id project</td>
	  <td width="150">name </td>
	 </tr>
	 <c:forEach items="${builds.builds}" var="build">
	  <tr>
	   <td><c:out value="${build.id}" /></td>
	   <td><c:out value="${build.idproject}" /></td>
	   <td><spring:url value="/builds/${build.id}" var="editbuild" />
	  	   <a href="${editbuild}" title="Edit Build">${build.name}</a></td>
	  </tr>
	 </c:forEach>
		 <tr>
		   <td colspan="2">
		   	<a href="${requestScope['javax.servlet.forward.request_uri']}/add" title="add">Add</a>
		   </td>
		  </tr>
	</table>
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>