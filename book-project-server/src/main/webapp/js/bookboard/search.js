console.log("연결");

$(document).ready(function() {
	$("#search").click(function() {
		
		$("#bookList").empty();
		
	  $.ajax({
	      method: "GET",
	      url: "https://dapi.kakao.com/v3/search/book?target=title",
	      data: { query: $("#bookName").val() },
	      headers: {Authorization: "KakaoAK ac32230dde9a15c09e232334d8fa83b7"}
	    })
	      .done(function( msg ) {
	    	console.log("ok");
	    	for(var i = 0; i < msg.documents.length; i++){
	    	console.log(msg.documents[i].thumbnail);
	        console.log(msg.documents[i].title);
	        console.log(msg.documents[i].authors);
	        $("#bookList").append(
	        		"<img src='"+ msg.documents[i].thumbnail + "'/>"
	        		+ "<strong>"+ msg.documents[i].title + "</strong>"
	        		+ msg.documents[i].authors
	        );
	    	}
	      });
	});
 });




