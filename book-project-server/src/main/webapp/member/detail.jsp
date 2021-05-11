<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<h1>회원 상세정보</h1>
<form action='update' method='post' enctype='multipart/form-data'>

<img src='../upload/member/${member.photo}' height='80'><br>
번호: 
<input name='no' type='text' readonly value='${member.no}'><br>
이름: 
<input name='name' type='text' value='${member.name}'><br>
이메일: 
<input name='email' type='email' value='${member.email}'><br>
비밀번호: 
<input name='password' type='password'><br>
사진: 
<input name='photo' type='file' value='${member.photo}'><br>
가입일: 
<input name='date' type='date' readonly value='${member.registeredDate}'><br>
<p>
<button>변경</button> <a href='delete?no=${member.no}'>삭제</a>
</p>

</form>

<jsp:include page="/footer.jsp"/>