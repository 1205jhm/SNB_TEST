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
		function deleteBoard() {
			var params = $("#form01").serialize();
			$.ajax({
				url : '/deleteBoard.do',
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
		function writeMode() {
			$("#title").removeAttr("readonly");
			$("#content").removeAttr("readonly");
			$("#change").css("display","none");
			$("#delete").css("display","none");
			$("#list").css("display","none");
			$("#save").css("display","");
			$("#cancel").css("display","");
			$(".view").attr("class","wirte");
		}
		function viewMode() {
			$("#title").attr("readonly", true);
			$("#content").attr("readonly", true);
			$("#change").css("display","");
			$("#delete").css("display","");
			$("#list").css("display","");
			$("#save").css("display","none");
			$("#cancel").css("display","none");
			$(".wirte").attr("class","view");
		}
	</script>
</head>
<body>
	<form id="form01">
		<input type="hidden" id="boardseq" name="boardseq" value="${row.boardseq }"/>
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
						<input type="text" id="title" name="title" class="wdp_90 view" value="${row.title }" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="view_text">
						<textarea rows="20" cols="100" title="내용" id="content" name="content" readonly="readonly" class="view">${row.content }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="button" onclick="writeMode();" class="btn" id="change" value="수정하기"/>
		<input type="button" onclick="deleteBoard();"class="btn" id="delete" value="삭제하기"/>
		<input type="button" onclick="location.href='/list'"class="btn" id="list" value="목록으로"/>
		<input type="button" onclick="saveBoard();" class="btn" id="save" value="저장하기" style="display:none;"/>
		<input type="button" onclick="viewMode();" class="btn" id="cancel" value="취소하기" style="display:none;"/>
	</form>
</body>
</html>
