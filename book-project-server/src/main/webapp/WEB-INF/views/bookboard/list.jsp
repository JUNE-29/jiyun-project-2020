<%@page import="june.project.book.domain.BookBoard" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src='${pageContext.getServletContext().getContextPath()}/js/bookBoard/searchBook.js'></script>

  <h1>API가져오기 TEST</h1>
  
  <h1>Book List</h1>
  <a href='form'>새 글</a>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>도서명</th>
    <th>책에 대한 점수</th>
    <th>등록일</th>
    <th>상태</th>
  </tr>
  
<c:forEach items="${list}" var="item">
  <tr>
  <td>${item.no}</td>
  <td><a href='detail?no=${item.no}'>${item.bookTitle}</a></td>
  <td>${item.score}</td>
  <td>${item.date}</td>
  <td>${item.bookStatus}</td>
  </tr>
</c:forEach>
  </table>
