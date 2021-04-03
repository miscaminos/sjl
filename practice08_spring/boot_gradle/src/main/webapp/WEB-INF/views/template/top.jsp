<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<c:choose>
	<c:when test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
		<c:set var="str">관리자 페이지 입니다.</c:set>
	</c:when>
	<c:when test="${not empty sessionScope.id && sessionScope.grade != 'A'}">
		<c:set var='str'>안녕하세요  ${sessionScope.id } 님 !</c:set>
	</c:when>
	<c:otherwise>
		<c:set var="str">Let's Study Python</c:set>
	</c:otherwise>
</c:choose>
<!DOCTYPE html> 
<html> 
<head>
  <title>memo</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style type="text/css">
  #grade{
  
   color : orange;
  }  
  </style>
</head>
<body> 
<!--상단메뉴-->
<div class="container">

  <div class="page-header row">
   <div class="col-sm-4">
    <img src="${root}/images/arrow.jpg" class="img-responsive img-thumbnail" alt="forwardWeGo" >
   </div>
   <div class="col-sm-8"><h1>Let's Study Python</h1><br>
    
   <p id="grade">${str}</p> 
   
   </div>     
  </div>
  <ul class="nav nav-tabs">
    <li class="active"><a href="${root}/">Home</a></li>
    
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Python <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="${root}/list">Chapters</a></li>
        <li><a href="${root}/create">Add New</a></li>
      </ul>
    </li> 
    <li class="dropdown">
      <a class="dropdown-toggle" data-toggle="dropdown" href="#">Python 실습 <span class="caret"></span></a>
      <ul class="dropdown-menu">
        <li><a href="#">Basic Problems</a></li>
        <li><a href="#">Advanced Problems</a></li>                       
      </ul>
    </li>
    <c:choose>
    <c:when test="${empty sessionScope.id }">
    <li><a href="${root}/member/agree">회원가입</a></li>
    <li><a href="${root}/member/login">로그인</a></li>
    </c:when>
    <c:otherwise>
    <li><a href="${root}/member/read">나의정보</a></li>
    <li><a href="${root}/member/update">회원수정</a></li>
    <li><a href="${root}/member/logout">로그아웃</a></li>
    </c:otherwise>
    </c:choose> 
    <c:if test="${not empty sessionScope.id && sessionScope.grade == 'A'}">
    <li><a href="${root}/admin/list">회원목록</a></li>
    </c:if>
  </ul>
</div>
</body>
</html>