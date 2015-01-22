<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>Edit issue</title></head>

<body>
<form:form name="editIssue" id="editIssueForm" method="POST"  modelAttribute="project">
  <form:select path="id">
			 <form:options items="${builds}" itemValue="id" itemLabel="name"/>
			 </form:select>
</form:form>
</body></html>