<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<jsp:useBean id="bookmark"  class="june.project.book.domain.Bookmark" scope="request"/>

<h1>북마크 상세정보(JSP)</h1>
<form action='update' method='post'>
번호: <input name='no' readonly type='text' value='<%=bookmark.getNo()%>'><br>
제목: <input name='title' type='text' value='<%=bookmark.getTitle()%>'><br>
도서명: <input name='bookTitle' type='text' value='<%=bookmark.getBookTitle()%>'><br>
지은이: <input name='author' type='text' value='<%=bookmark.getAuthor()%>'><br>
출판사: <input name='publisher' type='text' value='<%=bookmark.getPublisher()%>'><br>
필사 내용: <textarea name='content' rows='5' cols='60'><%=bookmark.getContent()%></textarea><br>
이미지: <input name='photo' type='text' value='<%=bookmark.getPhoto()%>'><br>
등록일: <%=bookmark.getDate()%>
<p>
<button>변경</button>
<a href='delete?no=<%=bookmark.getNo()%>'>삭제</a>
<a href='../photoboard/list?bookmarkNo=<%=bookmark.getNo()%>'>사진게시판</a>
</p>
</form>

<jsp:include page="/footer.jsp"/>