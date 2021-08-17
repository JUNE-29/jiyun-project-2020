<%@page import="june.project.book.domain.BookBoard" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <h1>검색</h1>
  <input id="bookName" value="" type="text">
  <button id="search">검색</button>
  
  <hr>
  <p></p>
  
<c:forEach items="${list}" var="item">
  <h2>${item.title}(${item.count})</h2>
  
    <c:choose>
   <c:when test="${item.books.bookboardNo eq 1}">
    <c:forEach items="${BookBoardNo1}" var="bookboard">
     ${bookboard.no}
     ${bookboard.thumbnail}
     ${bookboard.bookTitle}
     </c:forEach>
   </c:when>
   <c:when test="${item.no eq 2}">
     ${BookBoardNo2.no}
     ${BookBoardNo2.thumbnail}
     ${BookBoardNo2.bookTitle}
   </c:when>
  </c:choose>
</c:forEach>

