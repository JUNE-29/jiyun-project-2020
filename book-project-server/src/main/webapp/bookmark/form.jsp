<%@page import="june.project.book.domain.Bookmark" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="/header.jsp"/>

<h1>북마크 등록(JSP)</h1>
<form action='add' method='post'>
제목: 
<input name='title' type='text'><br>
도서명: 
<input name='bookTitle' type='text' ><br>
지은이: 
<input name='author' type='text' ><br>
출판사: 
<input name='publisher' type='text' ><br>
내용:<br>
<textarea name='content' rows='5' cols='60'></textarea><br>
이미지: 
<input name='photo' type='file' ><br>
<button>제출</button>
</form>

<jsp:include page="/footer.jsp"/>