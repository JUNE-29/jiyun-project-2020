<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
 .hidden {
  display: none;
  }
</style>

<h2>검색</h2>
<input id="bookName" value="" type="text">
<button id="search">검색</button>
  
  <p>
  
  <ul id="bookList">
  </ul>
  
  <form id="searchResult" action='searchDetail' method='post' class="hidden">
   hi
  </form>