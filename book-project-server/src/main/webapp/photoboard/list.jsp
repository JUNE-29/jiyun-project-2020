<%@page import="june.project.book.domain.PhotoBoard" %>
<%@page import="june.project.book.domain.Bookmark" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

 <jsp:include page="/header.jsp"/>
 
 <h1>책과 함께한 사진 - <a href='../bookmark/detail?no=${bookmark.no}'>${bookmark.title}</a></h1>
<a href='add?bookmarkNo=${bookmark.no}'> 추가 </a><br>
  <table border='1'>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>등록일</th>
      <th>조회수</th>
    </tr>
   <jsp:useBean id="list" type="java.util.List<PhotoBoard>" class="java.util.ArrayList" scope="request"/>
<% 
  for(PhotoBoard item : list) {
    pageContext.setAttribute("item", item);
%>
  <tr>
    <td>${item.no}</td>
    <td><a href='detail?no=${item.no}'>${item.title}</a></td>
    <td>${item.creadtedDate}</td>
    <td>${item.viewCount}</td>
  </tr>
<%
}
%>
  </table>
  <jsp:include page="/footer.jsp"/>