<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Sign-Up</title>
<style type="text/css">
body{
	text-align: center;
}
</style>
</head>
<body>

<div><b>TIC TAC TOE</b></div><br>

<div><b>____________________________________</b></div><br>


<form id="form1" method="post" action="SignUp" onsubmit="return validate();" >  
      <div class="form-row"><span class="label">UserId </span>
      <input type="text" name="user" id="user" /></div>  
      <input type="hidden" name="pass" /></div> 
      
      <div class="form-row"><input class="submit" type="submit" value="Enter your User id"></div> 
</form> 
<script type="text/javascript">
function validate()
{
	if(document.getElementById('user').value=='')
		{
		  alert("Enter Username");
		  return false;
		}
	else
		{
		  return true;
		}
}
	
</script>

</body>
</html>