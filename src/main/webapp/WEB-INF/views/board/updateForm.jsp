<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/" method="post">
		<input type="hidden" id="id" name="name" value="${board.id }" />
		<div class="form-group">
			<label for="id">글번호 : <span >${board.id }</span></label>
			<label for="username">작성자 : <span>${board.user.username }</span></label>
		</div>
		<div class="form-group">
			<label for="title">Title</label> 
			<input type="text" class="form-control" placeholder="Enter title" id="title" name="title" value="${board.title }">
		</div>
		
		<div class="form-group">
		  <label for="content">Content</label>
		  <textarea class="form-control summernote" rows="5" id="content"  >${board.content}</textarea>
		</div>
		
	</form>
	<button id="btn-update" class="btn btn-primary">글쓰기완료</button>

</div>
<script>
	$('.summernote').summernote({
	  placeholder: '',
	  tabsize: 2,
	  height: 300
	});
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>