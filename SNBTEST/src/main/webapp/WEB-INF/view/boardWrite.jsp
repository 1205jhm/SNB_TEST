<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SNB TEST</title>
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
	<script type="text/javascript">
		function saveBoard() {
			var params = $("#form01").serialize();
			$.ajax({
				url : '/saveBoard.do',
				type : 'POST',
				data : params,
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					location.href="/list";
				},
				error : function(request, error) {
					alert("code:"+request.status+"\n message:"+request.responseText+"\n error:"+error);
				}
				
			});
		}
	</script>
</head>
<body>
	<form id="form01">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>게시글 작성</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td>
						<input type="text" id="title" name="title" class="wdp_90 write"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="view_text">
						<textarea rows="20" cols="100" title="내용" id="content" name="content" class="write"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="button" onclick="saveBoard();" class="btn" id="write" value="작성하기"/>
		<input type="button" onclick="location.href='/list'"class="btn" id="list" value="목록으로"/>
	</form>
</body>
</html>
