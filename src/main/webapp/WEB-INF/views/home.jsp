<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="true"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Home</title>
</head>
<body>
 <jsp:include page="/WEB-INF/views/header.jsp"/>

	<h1>Issue Tracker!</h1>
		
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<br>
		<spring:url value="/users/" var="users" />
		<a href="${users}" title="users">users</a>
		
		
	</sec:authorize>	

		
	<br>
		<spring:url value="/statuses/" var="statuses" />
		<a href="${statuses}" title="statuses">statuses</a>
		
	<br>
		<spring:url value="/resolutions/" var="resolutions" />
		<a href="${resolutions}" title="resolutions">resolutions</a>		
		
		
	<br>
		<spring:url value="/manuals/priority" var="priority" />
		<a href="${priority}" title="priority">priority</a>				
		
	<br>
		<spring:url value="/manuals/resolution" var="resolution" />
		<a href="${resolution}" title="resolution">resolution</a>		

	
</body>
</html>
