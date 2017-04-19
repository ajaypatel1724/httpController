<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
 
 <table>
  <tr>
  	<td >An error has occured!. Please check the details below</td>
  </tr>
  <tr>
 	 <td >Please contact technical support with the following information:</td>
  </tr>	 	    
 </table>

<div id="exception-info">
<table>
    <tr>
        <td><strong>Messages</strong>:</td>
        <td>
            <s:property value="exception"/> 
        </td>
    </tr>
</table>
</div>


<div id="stacktraces">
<hr />
<h3>Stacktraces</h3>
<div class="stacktrace" style="padding-left: 0em">
    <strong><s:property value="exceptionStack" /></strong>
</div>
</div>