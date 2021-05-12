<%@page import="june.project.book.domain.Bookmark" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header.jsp"/>

  <h1>북마크 검색 결과</h1>
  <table border='1'>
    <tr>
     <th>번호</th>
     <th>제목</th>
     <th>도서명</th>
     <th>지은이</th>
     <th>등록일</th>
    </tr>
<c:forEach items="${list}" var="item">
  <tr>
    <td>${item.no}</td>
    <td><a href='detail?no=${item.no}'>${item.title}</a></td>
    <td>${item.bookTitle}</td>
    <td>${item.author}</td>
    <td>${item.date}</td>
  </tr>
</c:forEach>
  </table>

<jsp:include page="/footer.jsp"/>
