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
	for (i = 0; i < xmlSelect.length; i++) {
 	   var oOption = document.createElement('OPTION');
 	   FiltredSelect.options.add(oOption);
	   oOption.text = xmlSelect[i].getElementsByTagName('name')[0].firstChild.nodeValue;
 	   oOption.value = xmlSelect[i].getElementsByTagName('id')[0].firstChild.nodeValue;
 	}  	
}