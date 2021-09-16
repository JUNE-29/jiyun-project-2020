<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h1> 책 </h1>
  
  <c:forEach items="${bookInfo}" var="book">
    ${book}
  </c:forEach>
