<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SNB TEST</title>
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
	<script type="text/javascript">
		var insUser ="${row.insUser }"
		function saveBoard() {
			var params = $("#form01").serialize();
			$.ajax({
				url : '/saveBoard.do',
				type : 'POST',
				data : params,
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					window.location.reload();
				},
				error : function(xhr, status, error) {
					alert("xhr:"+xhr.status+"\n"+"status:"+status+"\n"+"error:"+error);
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
				error : function(xhr, status, error) {
					alert("xhr:"+xhr.status+"\n"+"status:"+status+"\n"+"error:"+error);
				}
			});
		}
		function writeMode() {
			$("#title").removeAttr("readonly");
			$("#content").removeAttr("readonly");
			$("#change").css("display","none");
			$("#delete").css("display","none");
			$("#list").css("display","none");
			$("#comment_list").css("display","none");
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
			$("#comment_list").css("display","");
			$("#save").css("display","none");
			$("#cancel").css("display","none");
			$(".wirte").attr("class","view");
			findAllComment();
		}
		function findAllComment() {
			var params = $("#form01").serialize();
			$.ajax({
				url : '/findAllComment.do',
				type : 'POST',
				data : {boardSeq : $("#boardSeq").val()},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					$("#commentList").html("")
					$("#commentListTmpl").tmpl(result).appendTo("#commentList");
					$("."+$("#username").text()).css("display","");
				},
				error : function(xhr, status, error) {
					alert("xhr:"+xhr.status+"\n"+"status:"+status+"\n"+"error:"+error);
				}
			});
		}
		function saveComment() {
			$.ajax({
				url : '/saveComment.do',
				type : 'POST',
				data : {"boardSeq" : $("#boardSeq").val(), "comment" : $("#comment").val()},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					$("#comment").val("");
					findAllComment();
				},
				error : function(xhr, status, error) {
					alert("xhr:"+xhr.status+"\n"+"status:"+status+"\n"+"error:"+error);
				}
			});
		}
		function deleteComment(commentSeq) {
			$.ajax({
				url : '/deleteComment.do',
				type : 'POST',
				data : {"commentSeq" : commentSeq},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					findAllComment();
				},
				error : function(request, error) {
					alert("code:"+request.status+"\n message:"+request.responseText+"\n error:"+error);
				}
			});
		}
		$(document).ready(function() {
			if(insUser == $("#username").text())
			{
				$("#change").css("display","");
				$("#delete").css("display","");
			}
			findAllComment();
		});
	</script>
</head>
<body>
	<div id="wrap">
	<jsp:include page="/WEB-INF/views/header.jsp" />
		<form id="form01">
			<input type="hidden" id="boardSeq" name="boardSeq" value="${row.boardSeq }"/>
			<table class="board_view">
				<colgroup>
					<col width="15%">
					<col width="*" />
				</colgroup>
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
			<div>
				<input type="button" onclick="writeMode();" class="btn" id="change" value="수정하기" style="display:none;"/>
				<input type="button" onclick="deleteBoard();"class="btn" id="delete" value="삭제하기" style="display:none;"/>
				<input type="button" onclick="location.href='/list'"class="btn" id="list" value="목록으로"/>
				<input type="button" onclick="saveBoard();" class="btn" id="save" value="저장하기" style="display:none;"/>
				<input type="button" onclick="viewMode();" class="btn" id="cancel" value="취소하기" style="display:none;"/>
			</div>
			<br/>
			<table class="board_list" id="comment_list">
				<colgroup>
					<col width="*" />
					<col width="10%" />
					<col width="20%" />
					<col width="10%" />
				</colgroup>
				<thead>
					<tr>
						<td>
							<input type="text" id="comment"/>	
						</td>
						<td></td>
						<td></td>
						<td class="right2">
							<input type="button" onclick="saveComment();" class="btn" value="등록하기"/>
						</td>
					</tr>
				</thead>
				<tbody id="commentList">
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
<script id="commentListTmpl" type="text/x-jquery-tmpl">
	<tr>
		<td>${'${'}comment}</td>
		<td>${'${'}insUser}</td>
		<td>${'${'}insDate}</td>
		<td>
			<input type="button" onclick='deleteComment("${'${'}commentSeq}");'class="btn ${'${'}insUser}" value="삭제하기" style="display:none;"/>
		</td>
	<tr>
</script>