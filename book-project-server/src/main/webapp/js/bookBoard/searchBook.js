!(function($) {
	$.ajax({
		  method: "GET",
		  url: "https://dapi.kakao.com/v3/search/book?target=title",
		  data: { query = "미움받을 용기" },
		  headers: {"Authorization: KakaoAK ac32230dde9a15c09e232334d8fa83b7"}
		})
		  .done(function( msg ) {
		    alert( "Data Saved: " + msg );
		  });
}