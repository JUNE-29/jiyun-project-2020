<%@page import="june.project.book.domain.Bookmark" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>
<%
  Bookmark bookmark = (Bookmark) request.getAttribute("bookmark");
%>
  <h1>사진 입력</h1>
  <form action='add' method='post' enctype='multipart/form-data'>
북마크 번호: 
<input name='bookmarkNo' type='text' value='${bookmark.no}' readonly><br>
북마크 제목: ${bookmark.title}<br>
사진 제목:<br>
<textarea name='title' rows='5' cols='60'></textarea><br>
<hr>
사진: <input name='photoFiles' type='file'><br>
사진: <input name='photoFiles' type='file'><br>
사진: <input name='photoFiles' type='file'><br>
사진: <input name='photoFiles' type='file'><br>
사진: <input name='photoFiles' type='file'><br>
<button>제출</button>
</form>

<jsp:include page="/footer.jsp"/>