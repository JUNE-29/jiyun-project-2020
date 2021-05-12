<%@page import="june.project.book.domain.BookBoard" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp"/>

  <h1>Book List</h1>
  <a href='add'>새 글</a>
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
  <jsp:include page="/footer.jsp"/>
  
