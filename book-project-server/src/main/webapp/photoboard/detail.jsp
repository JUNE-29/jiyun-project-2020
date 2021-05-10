<%@page import="june.project.book.domain.PhotoFile" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="/header.jsp"/>
<jsp:useBean id="photoBoard" class="june.project.book.domain.PhotoBoard" scope="request"/>

<h1>사진 상세정보</h1>
<form action='update' method='post' enctype='multipart/form-data'>
번호: <input name='no' type='text' readonly value='<%=photoBoard.getNo()%>'><br>
제목: <textarea name='title' rows='5' cols='60'><%=photoBoard.getTitle()%></textarea><br>
등록일: <%=photoBoard.getCreadtedDate()%><br> 
조회수: <%=photoBoard.getViewCount()%><br> 
책: <%=photoBoard.getBookmark().getBookTitle()%><br>
<hr>
사진 파일: <br>
<p>
<%
for (PhotoFile photoFile : photoBoard.getFiles()) {
%>
<img src='../upload/photoboard/<%=photoFile.getFilePath()%>' height = '80'>
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
<a href='delete?no=<%=photoBoard.getNo()%>&bookmarkNo=<%=photoBoard.getBookmark().getNo()%>'>삭제</a>
</p>
</form>

<jsp:include page="/footer.jsp"/>