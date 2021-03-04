<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String CONTENT_PAGE = (String) request.getAttribute("CONTENT_PAGE");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- top menu begin-->
<jsp:include page="./top.jsp"></jsp:include>
<!-- top menu end -->

<!-- content begin -->
<div style="width: 100%; padding-top: 30px;">
	<jsp:include page="<%=CONTENT_PAGE%>" flush="false">
</div>
<!-- content end -->


</body>
</html>