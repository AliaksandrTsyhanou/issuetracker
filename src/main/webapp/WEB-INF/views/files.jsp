<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Files</title></head>
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>
<body>
<form:form name="uploadfile" id="uploadfileForm" method="POST" enctype="multipart/form-data">
	<table>
	 <tr>
	  	<td width="200">Name</td>
	  	<td width="150">Size</td>
	 	<td width="200">Description</td>
	  	<td width="400">Link</td>
<!-- 	 </tr> -->
<%-- 	 <c:forEach items="${files}" var="file"> --%>
<!-- 	  <tr> -->
<%-- 	   	<td><c:out value="${file.name}" /></td> --%>
<%-- 	   	<td><c:out value="${file.size}" /></td>	    --%>
<%-- 	   	<td><c:out value="${file.description}" /></td> --%>
<%-- 	   	<td><c:out value="link" /></td> --%>
<!-- 	  </tr> -->
<%-- 	 </c:forEach> --%>
<!-- 	  <tr> -->
	   <td colspan="4">
	   	<input name="file" type="File" class="file" id="file">
	 	<spring:url value="/uploadfile" var="uploadfile" />
	 	<input type="submit" value="Upload"> Upload File
	   </td>
	  </tr>
	</table>
</form:form>
</body></html>