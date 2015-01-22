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
<style type="text/css"> <%@include file="/resources/css/form.css" %> </style>

<script>  
	function updateSelect(linkstart, linkend, selectFilter, selectFiltredID) {  
	  var filterId = selectFilter.options[selectFilter.selectedIndex].value;
	  var link = linkstart + filterId + linkend;
	  showContent(link, selectFiltredID);
	}
		
    function showContent(link, selectFiltredID) {  
        var http = createRequestObject();  
        if( http )   
        {    	
        	http.open('get', link);  
        	http.onreadystatechange = function ()   
            {  
                if(http.readyState == 4)   
                {  
                   var xmlSelect = http.responseXML.getElementsByTagName('build');
                   result = http.responseXML.getElementsByTagName('id')[0].firstChild.nodeValue;
//                   alert(result);
//                   alert("xmlSelect[0].firstChild.firstChild.nodeValue=" + xmlSelect[0].firstChild.firstChild.nodeValue);
                   Filter(selectFiltredID, xmlSelect);
                }  
            }  
            http.send(null);      
        }  
        else   
        {  
            document.location = link;  
        }  
    }  
  
    // creat ajax object  
    function createRequestObject()   
    {  
        try { return new XMLHttpRequest() }  
        catch(e)   
        {  
            try { return new ActiveXObject('Msxml2.XMLHTTP') }  
            catch(e)   
            {  
                try { return new ActiveXObject('Microsoft.XMLHTTP') }  
                catch(e) { return null; }  
            }  
        }  
    }  
    
    function Filter(selectFiltredID, xmlSelect) {
    	var FiltredSelect = document.getElementById(selectFiltredID);
    	var i = 0;
    	var k = FiltredSelect.length;
    	for (i=0; i<k; i++){
    		FiltredSelect.remove(0);
    	}
//    	alert(xmlSelect[0].firstChild.data);
    	for (i = 0; i < xmlSelect.length; i++) {
     	   var oOption = document.createElement('OPTION');
     	   FiltredSelect.options.add(oOption);
     	   oOption.text = xmlSelect[i].childNodes[2].firstChild.nodeValue;
     	   alert("oOption.text =" + oOption.text)
     	   oOption.value = xmlSelect[i].childNodes[0].firstChild.nodeValue;
     	  alert("oOption.value=" + oOption.value)
     	}  	
    	
    }
</script>  


<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<form:form name="editIssue" id="editIssueForm" method="POST"  modelAttribute="issue">
	<form:input path="id" type="hidden"/>
<table>
	<tr>
		<td> <form:label path="id">Id</form:label> </td>
		<td> <form:input path="id" readonly="readonly "/> </td>
	</tr>
	<tr>
		<td> <form:label path="createdate">Create Date</form:label> </td>
		<td><fmt:formatDate value="${issue.createdate}" pattern="yyyy-MM-dd"/> </td>
	</tr>
	<tr>
		<td> <form:label path="creator.emailaddress">Create By</form:label> </td>
		<td> <c:out value="${issue.creator.emailaddress}"/> </td>
	</tr>
	<tr>
		<td> <form:label path="modifydate">Modify Date</form:label> </td>
		<td><fmt:formatDate value="${issue.modifydate}" pattern="yyyy-MM-dd"/> </td>
	</tr>
	<tr>
		<td> <form:label path="modifier">Modify By</form:label> </td>
		<td> <c:out value="${issue.modifier.emailaddress}"/> </td>
	</tr>
	<tr>
		<td> <form:label path="summary">Summary</form:label> </td>
		<td> <form:input path="summary"/> </td>
		<td> <form:errors path="summary" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="description">Description</form:label> </td>
		<td> <form:input path="description"/> </td>
		<td> <form:errors path="description" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="status.id">Status</form:label> </td>
		<td> <form:select path="status.id">
			 <form:options items="${statuses}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="status.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="resolution.id">Resolution</form:label> </td>
		<td> <form:select path="resolution.id">
			 <form:options items="${resolutions}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="resolution.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="type.id">Type</form:label> </td>
		<td> <form:select path="type.id">
			 <form:options items="${types}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="type.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="priority.id">Priority</form:label> </td>
		<td> <form:select path="priority.id">
			 <form:options items="${priorities}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="priority.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="project.id">Project</form:label> </td>
		<td> <spring:url value="/projects/" var="projectbuilds" />
			 <form:select path="project.id" onchange="updateSelect('${projectbuilds}', '/builds/', this, 'selectFiltred')">
			 <form:options items="${projects}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="project.id" id="errmsg"/> </td>
	</tr>
	<tr>
		<td> <form:label path="build.id">Builds</form:label> </td>
		<td> <form:select id="selectFiltred" path="build.id">
			 <form:options items="${builds}" itemValue="id" itemLabel="name"/>
			 </form:select> </td>
		<td> <form:errors path="build.id" id="errmsg"/> </td>
	</tr>	
	<tr>
		<td> <form:label path="assignee.id">Manager</form:label> </td>
		<td> <form:select path="assignee.id">
			 <form:options items="${assignees}" itemValue="id" itemLabel="emailaddress"/>
			 </form:select> </td>
		<td> <form:errors path="assignee.id" id="errmsg"/> </td>
	</tr>
	
	<tr>
		<td colspan="3"> <spring:url value="/issues/" var="issues" />
		<a href="${issues}" title="issues">issues</a> <br><br> </td>
	</tr>
			
	
	<tr>
		<td colspan="3"> <input type="submit" value="Save Changes"/> </td>
	</tr>	
</table>


<div id="contentBody">  
    </div>  
  
    <div id="loading" style="display: none">  
    loading...
    </div>  
    
    
</form:form>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body></html>