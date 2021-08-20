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
  
    <h2>읽고 싶은 책</h2>
  <table border='1'>
  <tr>
  <th>번호</th>
  <th>도서명</th>
  <th>썸네일</th>
  <th>기대점수</th>
  </tr>
  
  <c:forEach items="${bookBasketList}" var="basket">
  <tr>
    <td>${basket.no}</td>
    <td>${basket.bookTitle}</td>
    <td>${basket.thumbnail}</td>
    <td>${basket.expectedScore}</td>
  </tr>
  </c:forEach>
  </table>
  
  
  
   <h2>읽은 책</h2>
  <table border='1'>
  <tr>
  <th>번호</th>
  <th>도서명</th>
  <th>썸네일</th>
  <th>별점</th>
  </tr>
  
  <c:forEach items="${bookcaseList}" var="bookcase">
  <tr>
    <td>${bookcase.no}</td>
    <td>${bookcase.bookTitle}</td>
    <td>${bookcase.thumbnail}</td>
    <td>${bookcase.score}</td>
  </tr>
  </c:forEach>
  </table>

