<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp"/>

<h1>상세정보(JSP)</h1>

<c:if test="${not empty bookBoard}">
<form action='update' method='post'>
번호: <input name='no' readonly type='text' value='${bookBoard.no}'><br>
도서명: <input name='bookTitle' type='text' value='${bookBoard.bookTitle}'><br>
지은이: <input name='author' type='text' value='${bookBoard.author}'><br>
출판사: <input name='publisher' type='text' value='${bookBoard.publisher}'><br>
출판 연도: <input name='publishedDate' type='text' value='${bookBoard.publishedDate}'><br>
카테고리: <input name='categories' type='text' value='${bookBoard.categories}'><br>
내용: <textarea name='content' rows='5' cols='60'>${bookBoard.content}</textarea><br>
이미지: <input name='photo' type='text' value='${bookBoard.photo}'><br>
평가: <input name='score' type='number' value='${bookBoard.score}'>점<br>
진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): 
<input name='bookStatus' type='number' value='${bookBoard.bookStatus}'><br>
등록일: ${bookBoard.date}<br>

<p>
<button>변경</button>
<a href='delete?no=${bookBoard.no}'>삭제</a>
</p>
</form>
</c:if>

<c:if test="${empty bookBoard}">
<p>해당 번호의 게시물이 없습니다.</p>
</c:if>

<jsp:include page="../footer.jsp"/>