/**
 * 
 */
 
 let index = {
	
	init : function() {
		$("#btn-save").on("click	", () =>{	// this를 바인딩하기 위함
			this.save();
		});
		$("#btn-delete").on("click	", () =>{	// this를 바인딩하기 위함
			this.deleteById();
		});
		
		$("#btn-update").on("click	", () =>{	// this를 바인딩하기 위함
			this.update();
		});
		
		$("#btn-reply-save").on("click	", () =>{	// this를 바인딩하기 위함
			this.replySave();
		});
		
		
		
	},
	save : function() {
		//alert("user에 save 함수 호출");
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		}
		
		//console.log(data);
		// ajax통신으로 data insert를 한다. 
		$.ajax({
			type : "POST"
			, url : "/api/board"
			, data : JSON.stringify(data)
			, contentType : "application/json; charset=utf-8"	// MIME type
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	},
	deleteById : function() {
		let id = $("#id").text();
		
		$.ajax({
			type : "DELETE"
			, url : "/api/board/"+id
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	},
	update : function() {
		let id = $("#id").val();
		
		let data = {
			title : $("#title").val(),
			content : $("#content").val()
		}
		
		$.ajax({
			type : "PUT"
			, url : "/api/board/"+id
			, data : JSON.stringify(data)
			, contentType : "application/json; charset=utf-8"	// MIME type
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	},
	replySave : function()	 {
		let data = {
			userId : $("#userId").val()
			,boardId : $("#boardId").val()
			,content : $("#replyContent").val()
		};
		
		
		
		$.ajax({
			type : "POST"
			, url : "/api/board/" + data.boardId + "/reply"
			, data : JSON.stringify(data)
			, contentType : "application/json; charset=utf-8"	// MIME type
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("댓글 등록 완료 되었습니다.");
			location.href = "/board/"+data.boardId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replyDelete : function(boardId, replyId) {
		//alert("user에 save 함수 호출");
		
		//console.log(data);
		// ajax통신으로 data insert를 한다. 
		let url = "/api/board/" +boardId + "/reply/"+replyId ;
		
		$.ajax({
			type : "DELETE"
			, url : url
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("댓글 삭제가 완료되었습니다.");
			location.href = "/board/"+boardId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	}
		
	
};

index.init();