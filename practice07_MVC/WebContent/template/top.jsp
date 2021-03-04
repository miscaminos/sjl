<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    String root = request.getContextPath();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- bootstrap으로 예쁘게 styling -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style type="text/css">
  #grade{
  
   color : orange;
  }  
  </style>

</head>
<body>

<!-- top menu -->
<div class="container">
  <div class="page-header row">
   <div class="col-sm-4">
    <img src="<%=root %>/images/Camino_Arrow.png" class="img-responsive img-thumbnail" alt="Cinque Terre" >
   </div>
   <div class="col-sm-8"><h1>Sojung's Lyrics Container</h1><br>
    
   <p id="grade">Keep going!</p> 
   
   </div>     
  </div>
  <ul class="nav nav-tabs">
    <li class="active"><a href="<%=root%>/index.jsp">Home</a></li>
    
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Lyrics <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root %>/bbs/list.do">Lyrics Board</a></li>
        <li><a href="<%=root %>/bbs/create.do">Post New Lyrics</a></li>
      </ul>
    </li> 
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Poems & Quotes <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="<%=root %>/bbs/list.do">Board</a></li>
        <li><a href="<%=root %>/bbs/create.do">Post New</a></li>                       
      </ul>
    </li>  
  </ul>
</div>

</body>
</html>