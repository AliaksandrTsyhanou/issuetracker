<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/xml">
<title></title></head>

<body>
  <select id="buildSelect">
 	 <c:forEach items="${builds}" var="build">
 	 	<option value="build.id"><c:out value="${build.name}"/></option> 	 	
 	 </c:forEach>
  </select>
</body></html>