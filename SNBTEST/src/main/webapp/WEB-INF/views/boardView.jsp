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
		var insuser = "${row.insuser }";
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
			$(".comment_view").css("display","none");
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
			$(".comment_view").css("display","");
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
				data : {boardseq : $("#boardseq").val()},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					$("#commentlist").html("")
					$("#commentlistTmpl").tmpl(result).appendTo("#commentlist");
					$("."+$("#username").text()).css("display","");
				},
				error : function(request, error) {
					alert("code:"+request.status+"\n message:"+request.responseText+"\n error:"+error);
				}
			});
		}
		function saveComment() {
			$.ajax({
				url : '/saveComment.do',
				type : 'POST',
				data : {"boardseq" : $("#boardseq").val(), "content" : $("#comment").val()},
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					$("#comment").val("");
					findAllComment();
				},
				error : function(request, error) {
					alert("code:"+request.status+"\n message:"+request.responseText+"\n error:"+error);
				}
			});
		}
		function deleteComment(commentseq) {
			$.ajax({
				url : '/deleteComment.do',
				type : 'POST',
				data : {"commentseq" : commentseq},
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
			if(insuser == $("#username").text())
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
			<input type="hidden" id="boardseq" name="boardseq" value="${row.boardseq }"/>
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
			<div class="fence">
				<div class="right">
					<input type="button" onclick="writeMode();" class="btn" id="change" value="수정하기" style="display:none;"/>
					<input type="button" onclick="deleteBoard();"class="btn" id="delete" value="삭제하기" style="display:none;"/>
					<input type="button" onclick="location.href='/list'"class="btn" id="list" value="목록으로"/>
					<input type="button" onclick="saveBoard();" class="btn" id="save" value="저장하기" style="display:none;"/>
					<input type="button" onclick="viewMode();" class="btn" id="cancel" value="취소하기" style="display:none;"/>
				</div>
			</div>
			<br/>
			<table class="comment_view">
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
				<tbody id="commentlist">
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>
<script id="commentlistTmpl" type="text/x-jquery-tmpl">
	<tr>
		<td>${'${'}content}</td>
		<td>${'${'}insuser}</td>
		<td>${'${'}insdate}</td>
		<td class="right2">
			<input type="button" onclick='deleteComment("${'${'}commentseq}");'class="btn ${'${'}insuser}" value="삭제하기" style="display:none;"/>
		</td>
	<tr>
</script>