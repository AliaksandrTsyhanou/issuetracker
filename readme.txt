DESCRIPTION:
It's just my training project "Issue Tracker".
To learn how to work with spring and hibernate.

USED:

 -- JDK 1.7
 -- Spring MVC 4.1.0.RELEASE
 -- Spring Security 3.2.5.RELEASE
 -- hibernate 4.1.7.Final
 -- hibernate validator 5.1.3.Final
 -- JSTL
 -- DisplayTag 1.2
 -- XMLHttpRequest
 -- MySql


DESIGN FEATURES:

 -- Validation of the correctness of filling the input forms made custom validators or annotations.

 --	Authorization and access rights are implemented using Spring Security. 
	Security Configuration is carried out in application-security.hml and using annotations.
	
 --	To update the build, depending on the selected project on the form editing issue, 
	using XMLHttpRequest in updateselect.js.
	
 --	To display a table with the ability to sort by any field 
	and partition table on the page with a certain number of records used DisplayTag.
	
 --	Search is implemented on jquery.
	
 --	Transfer data from MySQL database using hibernate. 
	Communication with the base set up in bins using annotations. 
	However, for the user setting is placed on a User.hbm.xml
	

POSSIBILITY TO INCREASE:

 --	reducing the number of classes DAO  and SERVICE layers by using patterns of strategy and the factory method.
 --	remove duplicate code in jsp-pages for adding and editing forms.
 --	removal of hardcoding in the script, changing the background color depending on the specified priority, 
	use XMLHttpRequest to retrieve the value of the priority and its background color.