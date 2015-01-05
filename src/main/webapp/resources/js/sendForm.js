function sendForm(nameForm, sCommand, sDownloadFileName) {
		document.getElementById(nameForm).command.value=sCommand;
		if(sDownloadFileName != undefined) document.getElementById(nameForm).downloadFileName.value=sDownloadFileName;
		document.getElementById(nameForm).submit();
}
function sendTaskId(nameForm, sTaskID) {
	document.getElementById(nameForm).taskID.value=sTaskID;
	document.getElementById(nameForm).submit();
}	
function checkAll(obj) {
	  'use strict';
	  var inputs = obj.form.getElementsByTagName("input");
	  for ( var i = 0; i < inputs.length; i++ )
	    if ( (inputs.item(i).type) && (inputs.item(i).type==="checkbox" ) )
	      inputs.item(i).checked = obj.checked;
	}