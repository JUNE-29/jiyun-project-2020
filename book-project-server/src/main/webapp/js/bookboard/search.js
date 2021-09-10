console.log("연결");

const searchList = [];

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
	    	//console.log(searchResultObj);
	    	
//	    	$("#bookList").append(
//	        "<li>"
//	    		+ "<img src='"+ msg.documents[i].thumbnail + "'/>"
//	        	+ "<strong>"+ msg.documents[i].title + "</strong>"
//	        	+ msg.documents[i].authors + "</li> <hr>"
//	        );
	    	}
	    	console.log(searchList);
	    	
	    	for(var i =  0; i < searchList.length; i++) {
		    	$("#bookList").append(
		        "<li>"
		    		+ "<img src='"+ searchList[i].thumbnail + "'/>"
		        	+ "<strong>"+ searchList[i].title + "</strong>"
		        	+ searchList[i].authors + "</li> <hr>"
		        );
	    	}
	    });
	});
 });




