<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h1> 책 </h1>

<table border='1'>
 <tr>
  <th>썸네일</th>
  <th>도서명</th>
  <th>작가</th>
  <th>출판사</th>
  <th>출간일자</th>
  <th>isbn</th>
  </tr>
  
  <tr>
  <td><img src = '${bookInfo["thumbnail"]}'> </td>
  <td>${bookInfo["title"]} </td>
  <td>${bookInfo["authors"]}</td>
  <td>${bookInfo["publisher"]}</td>
  <td>${bookInfo["datetime"]} </td>
  <td>${bookInfo["isbn"]}</td>
  </tr>
</table>
  
