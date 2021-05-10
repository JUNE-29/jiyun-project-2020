<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<jsp:useBean id="member"  class="june.project.book.domain.Member" scope="request"/>

<h1>회원 상세정보</h1>
<form action='update' method='post' enctype='multipart/form-data'>

<img src='../upload/member/<%=member.getPhoto()%>' height='80'><br>
번호: 
<input name='no' type='text' readonly value='<%=member.getNo()%>'><br>
이름: 
<input name='name' type='text' value='<%=member.getName()%>'><br>
이메일: 
<input name='email' type='email' value='<%=member.getEmail()%>'><br>
비밀번호: 
<input name='password' type='password'><br>
사진: 
<input name='photo' type='file' value='<%=member.getPhoto()%>'><br>
가입일: 
<input name='date' type='date' readonly value='<%=member.getRegisteredDate()%>'><br>
<p>
<button>변경</button> <a href='delete?no=<%=member.getNo()%>'>삭제</a>
</p>

</form>

<jsp:include page="/footer.jsp"/>