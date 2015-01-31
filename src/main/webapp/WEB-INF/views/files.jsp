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
<form:form name="uploadfile" id="uploadfileForm" method="POST" 
	action="${param.action}" enctype="multipart/form-data">
	Files:
	<table style="margin=0">
	 <tr>
	  	<td width="200">Name</td>
	  	<td width="150">Size</td>
	 	<td width="200">Description</td>
	 </tr>
	 <c:forEach items="${files}" var="file">
	  <tr>
	 	<td><spring:url value="/files/${file.id}" var="uploadfile" />
	     	<a href="${uploadfile}" title="uploadfile">${file.name}</a></td>
	   	<td><c:out value="${file.size}" /></td>	   
	   	<td><c:out value="${file.description}" /></td>
	  </tr>
	 </c:forEach>
	</table>
	 
 	<table>	 
	  <tr>
	    <td> <label>Description:</label> </td>
		<td> <input name="description"/> </td>
	    <td> <label> | select file: </label> </td>
	    <td>
	    	<input name="file" type="File" class="file" id="file">
	    	<spring:url value="/uploadfile" var="uploadfile" />
	  	    <input type="submit" value="Upload">
	    </td>
	  </tr>
	  </table>

</form:form>
</body></html>