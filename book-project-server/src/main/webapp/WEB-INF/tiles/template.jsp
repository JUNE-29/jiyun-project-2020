<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>책갈피</title>

<!-- bookmark -->
 
<link rel='stylesheet' href='${pageContext.getServletContext().getContextPath()}/node_modules/bootstrap/dist/css/bootstrap.min.css'>
<link rel='stylesheet' href='<tiles:getAsString name="css.common"/>'>

</head>
<body>

<tiles:insertAttribute name="header"/>
<!--  tiles.xml에서 header로 저장했으니 header이름으로 꺼낸다.
      원래는 name="/WEB-INF/tiles/header.jsp -->
<div class='container'>
<tiles:insertAttribute name="body"/>
</div>
<tiles:insertAttribute name="footer"/>

<script src='${pageContext.getServletContext().getContextPath()}/node_modules/jquery/dist/jquery.min.js'></script>
<script src='${pageContext.getServletContext().getContextPath()}/node_modules/@popperjs/core/dist/umd/popper.min.js'></script>
<script src='${pageContext.getServletContext().getContextPath()}/node_modules/bootstrap/dist/js/bootstrap.min.js'></script>
<script src='${pageContext.getServletContext().getContextPath()}/node_modules/sweetalert/dist/sweetalert.min.js'></script>

</body>
</html>