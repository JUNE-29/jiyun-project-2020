<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>책갈피</title>
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6' crossorigin='anonymous'>
<style>
body {
    background-color: white;
}

div.container {
  background-color: white;
  width: 500px;
}

.navbar {
    background-color: #fcfcce;
}

.navbar .navbar-brand {
    color: #48a0ed;
}

.navbar .navbar-brand:hover,
.navbar .navbar-brand:focus {
 color: #acd7fe;
}

.navbar .navbar-nav .nav-link {
    color: #48a0ed;
    border-radius: .25rem;
    margin: 0 0.25em;
}

.navbar .navbar-nav .nav-link:not(.disabled):hover,
.navbar .navbar-nav .nav-link:not(.disabled):focus {
    color: #acd7fe;
}

.navbar .navbar-nav .nav-item.active .nav-link,
.navbar .navbar-nav .nav-item.active .nav-link:hover,
.navbar .navbar-nav .nav-item.active .nav-link:focus,
.navbar .navbar-nav .nav-item.show .nav-link,
.navbar .navbar-nav .nav-item.show .nav-link:hover,
.navbar .navbar-nav .nav-item.show .nav-link:focus {
    color: #acd7fe;
    background-color: #baf7ff;
}
</style>
</head>
<body>

<tiles:insertAttribute name="header"/>
<!--  tiles.xml에서 header로 저장했으니 header이름으로 꺼낸다.
      원래는 name="/WEB-INF/tiles/header.jsp -->
<div class='container'>
<tiles:insertAttribute name="body"/>
</div>

<tiles:insertAttribute name="footer"/>

</body>
</html>