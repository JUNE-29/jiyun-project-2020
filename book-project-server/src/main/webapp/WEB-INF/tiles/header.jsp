<%@page import="june.project.book.domain.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
  
<c:if test="${not empty loginUser}">
  <span class='navbar-text'>${loginUser.name}</span>
  <a href='../auth/logout' class='btn btn-success btn-sm'>로그아웃</a>
</c:if>
<c:if test="${empty loginUser}">
  <a href='../auth/form' class='btn btn-success btn-sm'>로그인</a>
</c:if> 

</div>
</nav>
