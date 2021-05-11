<%@page import="june.project.book.domain.Bookmark" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

  <h1>북마크</h1>
<a href='add'>북마크 추가</a><br>
     <table border='1'>
      <tr>
     <th>번호</th>
     <th>제목</th>
     <th>도서명</th>
     <th>지은이</th>
     <th>등록일</th>
     </tr>
<jsp:useBean id="list" type="java.util.List<Bookmark>" class="java.util.ArrayList" scope="request"/>
<%
  for(Bookmark item : list) {
    pageContext.setAttribute("item", item);
%>
     <tr>
     <td>${item.no}</td>
     <td><a href='detail?no=${item.no}'>${item.title}</a></td>
     <td>${item.bookTitle}</td>
     <td>${item.author}</td>
     <td>${item.date}</td>
     </tr>
<%
  }
%>
     </table>
<hr>
<form action='search' method='get'>
제목: <input name='title' type='text'><br>
도서명: <input name='bookTitle' type='text'><br>
지은이: <input name='author' type='text'><br>
등록일: <input name='date' type='date'><br>
<button>검색</button>
</form>

<jsp:include page="/footer.jsp"/>

