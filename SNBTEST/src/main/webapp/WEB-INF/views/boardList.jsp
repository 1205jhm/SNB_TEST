<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>SNB TEST</title>
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
	<script type="text/javascript">
	 	function findAllBoard() {
			var params = $("#form01").serialize();
			$.ajax({
				url : '/findAllBoard.do',
				type : 'POST',
				data : params,
				contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
				dataType : 'json',
				success : function(result) {
					var paging = "";
					for(var i=1; i<=result[0].totalpage; i++) {
						paging += "<li onclick='setpage("+i+")'>"+i+"</li>"
					}
				    $("#paging").html(paging);
				    $("#list").html("");
					$("#listTmpl").tmpl(result).appendTo("#list");
				}
			});
		}
		function setpage(i) {
			$("#pagenum").val(i);
			findAllBoard();
		}
		function view(boardseq) {
			location.href="/view?boardseq="+boardseq;
		}
		$(document).ready(function() {
			findAllBoard();
		});
	</script>
</head>
<body>
	<div id="wrap">
	<jsp:include page="/WEB-INF/views/header.jsp" />
		<h2>게시판 목록</h2>
		<div class="fence">
			<div class="right">
				<input type="button" onclick="location.href='/write'" class="btn" id="write" value="글쓰기"/>
			</div>
		</div>
		<form id="form01">
			<input type="hidden" id="limit" name="limit" value="10">
			<input type="hidden" id="pagenum" name="pagenum" value="1">
			<table class="board_list">
				<colgroup>
					<col width="10%" />
					<col width="*" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">글번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody id="list">
				</tbody>
			</table>
			<br />
			<ul id=paging>
			</ul>
		</form>
	</div>
</body>
</html>
<script id="listTmpl" type="text/x-jquery-tmpl">
	<tr onclick="view('${'${'}boardseq}')">
		<td>${'${'}boardseq}</td>
		<td>${'${'}title}</td>
		<td>${'${'}insuser}</td>
		<td>${'${'}insdate}</td>
	<tr>
</script>