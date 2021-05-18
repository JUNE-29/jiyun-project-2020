<%@page import="june.project.book.domain.BookBoard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="../header.jsp"></jsp:include>

<h1>등록(JSP)</h1>
<form action='add' method='post'>
도서명: <br>
 <input name='bookTitle' type='text'><br>
지은이: <br>
<input name='author' type='text'><br>
출판사: <br>
<input name='publisher' type='text'><br>
카테고리: <br>
<input name='categories' type='text' ><br>
출판 연도: <br>
<input name='publishedDate' type='text' ><br>
내용(메모): <textarea name='content' rows='5' cols='60'></textarea><br>
이미지: <br>
<input name='photo' type='text' ><br>
진행 상태(1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): <br>
<input name='bookStatus' type='number'><br>
책에 대한 점수(5점만점): <br>
<input name='score' type='number'><br>
<button>등록</button>
</form>

  <jsp:include page="../footer.jsp"/>