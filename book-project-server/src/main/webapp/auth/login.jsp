<%@page import="june.project.book.domain.Member" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<h1>로그인 결과</h1>
<jsp:useBean id="loginUser"  class="june.project.book.domain.Member" scope="session"/>
<%
      if (loginUser.getName() != null) {
%>
<p>'<%=loginUser.getName()%>'님 환영합니다.</p>
<%
      } else {
%>
<p>사용자 정보가 유효하지 않습니다.</p>
<%
}
%>
<jsp:include page="/footer.jsp"/>