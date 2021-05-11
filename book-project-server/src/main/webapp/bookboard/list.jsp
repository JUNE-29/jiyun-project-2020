<%@page import="june.project.book.domain.BookBoard" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
    
<jsp:include page="/header.jsp"></jsp:include>

  <h1>Book List</h1>
  <a href='add'>새 글</a>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>도서명</th>
    <th>책에 대한 점수</th>
    <th>등록일</th>
    <th>상태</th>
  </tr>
  <jsp:useBean id="list" type="java.util.List<BookBoard>" class="java.util.ArrayList" scope="request"/>
  <%
  for(BookBoard item : list) {
  %>
  <tr>
  <td><%=item.getNo()%></td>
  <td><a href='detail?no=<%=item.getNo()%>'><%=item.getBookTitle()%></a></td>
  <td><%=item.getScore()%></td>
  <td><%=item.getDate()%></td>
  <td><%=item.getBookStatus()%></td>
  </tr>
  <%
  }
  %>
  </table>
  <jsp:include page="/footer.jsp"/>
  