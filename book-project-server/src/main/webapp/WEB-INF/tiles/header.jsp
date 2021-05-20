<%@page import="june.project.book.domain.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<nav class='navbar navbar-expand-lg navbar-light'>
  <a class='navbar-brand' href='../'>책갈피</a>
  <button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
    <span class='navbar-toggler-icon'></span>
  </button>
<div class='collapse navbar-collapse' id='navbarNav'>
  <ul class='navbar-nav'>
    <li class='nav-item'>
     <a class='nav-link' href='../bookboard/list'>읽고 싶은 책</span></a>
    </li>
    <li class='nav-item'>
      <a class='nav-link' href='../bookmark/list'>간직 하고 싶은 말</a>
    </li>
    <li class='nav-item'>
      <a class='nav-link' href='../member/list'>회원</a>
    </li>
  </ul>
<%
Member loginUser = (Member) request.getSession().getAttribute("loginUser");
if (loginUser != null) {
%>
 <span class= 'navbar-text'><%=loginUser.getName()%></span>
 <a href='../auth/logout' class= 'btn btn-primary btn-sm'>로그아웃</a>
<%
} else{
%>
 <a href='../auth/form' class='btn btn-primary btn-sm'>로그인</a>
<%
}
%>
</div>
</nav>
<div class='container'>