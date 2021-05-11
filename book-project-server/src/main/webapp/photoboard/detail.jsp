<%@page import="june.project.book.domain.PhotoBoard" %>
<%@page import="june.project.book.domain.PhotoFile" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="/header.jsp"/>

<h1>사진 상세정보</h1>
<form action='update' method='post' enctype='multipart/form-data'>
번호: <input name='no' type='text' readonly value='${photoBoard.no}'><br>
제목: <textarea name='title' rows='5' cols='60'>${photoBoard.title}</textarea><br>
등록일: ${photoBoard.creadtedDate}<br> 
조회수: ${photoBoard.viewCount}<br> 
책: ${photoBoard.bookmark.bookTitle}<br>
<hr>
사진 파일: <br>
<p>
<%
PhotoBoard photoBoard = (PhotoBoard) request.getAttribute("photoBoard");
for (PhotoFile photoFile : photoBoard.getFiles()) {
  pageContext.setAttribute("photoFile", photoFile);
%>
<img src='../upload/photoboard/${photoFile.filePath}' height = '80'>
<%
}
%>
</p>
사진: <input name='photo' type='file'><br>
사진: <input name='photo' type='file'><br>
사진: <input name='photo' type='file'><br>
사진: <input name='photo' type='file'><br>
사진: <input name='photo' type='file'><br>
<p> 
<button>변경</button>
<a href='delete?no=${photoBoard.no}&bookmarkNo=${photoBoard.bookmark.no}'>삭제</a>
</p>
</form>

<jsp:include page="/footer.jsp"/>