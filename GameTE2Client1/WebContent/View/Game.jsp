<%@page import="Servlets.Game"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page language="java" import="java.sql.*"%>
<%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %> 
<%! Connection con=null; %> 
<%! Statement stmt= null; %> 
<%! ResultSet rst= null; %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<META HTTP-EQUIV="Refresh" CONTENT="3">

<title>TIC TAC TOE</title>
</head>
<body>
Your User Id : <%=  session.getAttribute( "userName" ) %>,
<br/> 
<% // session.getAttribute( "userID" ) %>
<br/>
<% // session.getAttribute( "userGAME" ) %>
<% Game g = new Game(); String current=(String)session.getAttribute( "userID" ); String message=(String)session.getAttribute( "gameMessage" );  session.removeAttribute("gameMessage") ; if(message==null){message="";}%>
<br/>
<% String opp = g.Opponent(current);
String[] qd = opp.split(":");
String versus = "";
%>
<br/> 
You are playing with : <% if(opp!=null){  versus = qd[0]; %><%=qd[0]%><% }else{ %><%="NO user found"%><% } %>
<br/> 

<p style="color:red"><%=message%></p>
<% if(! versus.equals("No user found (Wait for some time)")){ %>
<form id="form1" method="post" action="Game" >  
      <div class="form-row">
      <% if(qd[1].equals("0") || qd[1].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[1]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_1" id="game_1" class="submit" type="submit" value="1">
      <% } %>   
      <% if(qd[2].equals("0") || qd[2].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[2]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_2" id="game_2" class="submit" type="submit" value="2">
      <% } %>   
      <% if(qd[3].equals("0") || qd[3].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[3]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_3" id="game_3" class="submit" type="submit" value="3">
      <% } %>   
      </div> 
      <br/> 
      <div class="form-row">
      <% if(qd[4].equals("0") || qd[4].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[4]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_4" id="game_4" class="submit" type="submit" value="4">
      <% } %>   
      <% if(qd[5].equals("0") || qd[5].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[5]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_5" id="game_5" class="submit" type="submit" value="5">
      <% } %>   
      <% if(qd[6].equals("0") || qd[6].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[6]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_6" id="game_6" class="submit" type="submit" value="6">
      <% } %>   
      </div> 
      <br/> 
      <div class="form-row">
      <% if(qd[7].equals("0") || qd[7].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[7]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_7" id="game_7" class="submit" type="submit" value="7">
      <% } %>   
      <% if(qd[8].equals("0") || qd[8].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[8]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_8" id="game_8" class="submit" type="submit" value="8">
      <% } %>   
      <% if(qd[9].equals("0") || qd[9].equals("X")){ %><span style="width:10px;">&nbsp;&nbsp;&nbsp;<%=qd[9]%>&nbsp;&nbsp;&nbsp;</span>
      <% }else{ %>  
         <input name="game_9" id="game_9" class="submit" type="submit" value="9">
      <% } %>   
      </div> 
</form> 
<% } %>

</body>
</html>