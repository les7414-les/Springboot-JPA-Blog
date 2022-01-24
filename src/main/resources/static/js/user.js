/**
 * 
 */
 
 let index = {
	
	init : function() {
		$("#btn-save").on("click	", () =>{	// this를 바인딩하기 위함
			this.save();
		});
		$("#btn-update").on("click	", () =>{	// this를 바인딩하기 위함
			this.update();
		});
	},
	save : function() {
		//alert("user에 save 함수 호출");
		let data = {
			username : $("#username").val(),
			email : $("#email").val(),
			password : $("#password").val()
		}
		
		//console.log(data);
		// ajax통신으로 data insert를 한다. 
		$.ajax({
			type : "POST"
			, url : "/auth/api/joinProc"
			, data : JSON.stringify(data)
			, contentType : "application/json; charset=utf-8"	// MIME type
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			if(resp.state === 500) {
				alert("회원가입이 실패하였습니다.");
			} else {
				alert("회원가입이 완료되었습니다.");
				location.href = "/";
			}
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	},
	update : function() {
		//alert("user에 save 함수 호출");
		let data = {
			id : $("#id").val(),
			username : $("#username").val(),
			email : $("#email").val(),
			password : $("#password").val()
		}
		
		//console.log(data);
		// ajax통신으로 data insert를 한다. 
		$.ajax({
			type : "PUT"
			, url : "/user"
			, data : JSON.stringify(data)
			, contentType : "application/json; charset=utf-8"	// MIME type
			, dataType : "json"	// string 형태를 json으로 파싱하여 object로 변환
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		
	}
		
	
};

index.init();