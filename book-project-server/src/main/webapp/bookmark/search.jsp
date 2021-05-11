<%@page import="june.project.book.domain.Bookmark" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="/header.jsp"></jsp:include>

  <h1>북마크 검색 결과</h1>
  <table border='1'>
    <tr>
     <th>번호</th>
     <th>제목</th>
     <th>도서명</th>
     <th>지은이</th>
     <th>등록일</th>
    </tr>
<jsp:useBean id="list" type="java.util.List<Bookmark>" class="java.util.ArrayList" scope="request"/>
<% 
  for(Bookmark item : list) {
%>
  <tr>
    <td><%=item.getNo() %></td>
    <td><a href='detail?no=<%=item.getNo()%>'><%=item.getTitle()%></a></td>
    <td><%=item.getBookTitle()%></td>
    <td><%=item.getAuthor()%></td>
    <td><%=item.getDate()%></td>
  </tr>
<%
  }
%>
  </table>

<jsp:include page="/footer.jsp"/>