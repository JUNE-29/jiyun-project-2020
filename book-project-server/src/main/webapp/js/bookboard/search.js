console.log("연결");

const searchList = [];
let bookDetailInfo = 0;

function bookDetail(bookInfo) {
	const searchResultForm = document.getElementById("searchResult");
	
	const authors = bookInfo.authors.toString();
	
	const bookInfoInput = "" 
		+ "<input name='authors' type='text' value='"+ authors +"'> <br>" 
		+ "<input name='contents' type='text' value='"+ bookInfo.contents +"'> <br>"
		+ "<input name='datetime' type='text' value='"+ bookInfo.datetime +"'> <br>" 
		+ "<input name='isbn' type='text' value='"+ bookInfo.isbn +"'> <br>"
		+ "<input name='publisher' type='text' value='"+ bookInfo.publisher +"'> <br>"
		+ "<input name='thumbnail' type='text' value='"+ bookInfo.thumbnail +"'> <br>"
		+ "<input name='title' type='text' value='"+ bookInfo.title +"'>";
	
	searchResultForm.innerHTML = bookInfoInput;
	searchResultForm.submit();
}

function bookInfo(bookNo) {
	
	console.log(bookNo);
	
	switch (bookNo) {
	case 0 :
		console.log(searchList[0]);
		bookDetailInfo = searchList[0];
		bookDetail(bookDetailInfo) 
		break;
	case 1 :
		console.log(searchList[1]);
		bookDetailInfo = searchList[1];
		bookDetail(bookDetailInfo) 
		break;
	case 2 :
		console.log(searchList[2]);
		break;
	case 3 :
		console.log(searchList[3]);
		break;
	case 4 :
		console.log(searchList[4]);
		break;
	case 5 :
		console.log(searchList[5]);
		break;
	case 6 :
		console.log(searchList[6]);
		break;
	case 7 :
		console.log(searchList[7]);
		break;
	case 8 :
		console.log(searchList[8]);
		break;
	case 9 :
		console.log(searchList[9]);
		break;
	}
}

$(document).ready(function() {
	$("#search").click(function() {
		
		searchList.length = 0;
		$("#bookList").empty();
		
	  $.ajax({
	      method: "GET",
	      url: "https://dapi.kakao.com/v3/search/book?target=title",
	      data: { query: $("#bookName").val() },
	      headers: {Authorization: "KakaoAK ac32230dde9a15c09e232334d8fa83b7"}
	    })
	      .done(function( msg ) {
	    	console.log("ok");
	    	
	    	for(var i = 0; i < msg.documents.length; i++) {
	    		
	    	const searchResultObj = {
	    		authors: msg.documents[i].authors,
	    		contents: msg.documents[i].contents,
	    		datetime: msg.documents[i].datetime,
	    		isbn: msg.documents[i].isbn,
	    		price: msg.documents[i].price,
	    		publisher: msg.documents[i].publisher,
	    		thumbnail: msg.documents[i].thumbnail,
	    		title: msg.documents[i].title
	    	};
	    	searchList.push(searchResultObj);
	    	}
	    	
	    	console.log(searchList);
	    	
	    	for(var i =  0; i < searchList.length; i++) {
	    		//console.log(searchList[i]);
	    		
		    	$("#bookList").append(
		        "<li>"
		    		+ "<img src='"+ searchList[i].thumbnail + "'/>"
		        	+ "<button type='button' onclick='bookInfo("+ i +")'>"
		        	+ searchList[i].title + "</button>"
		        	+ searchList[i].authors + "</li> <hr>"
		        );
	    	}
	    });
	});
 });





