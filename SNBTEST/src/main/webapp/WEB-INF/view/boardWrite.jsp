<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>SNB TEST</title>
	<link href="/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
	</script>
</head>
<body>
	<form id="frm" action="wirte.do" method="post">
		<table class="board_view">
			<colgroup>
				<col width="15%">
				<col width="*" />
			</colgroup>
			<caption>게시글 작성</caption>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td><input type="text" id="title" name="title" class="wdp_90"></input></td>
				</tr>
				<tr>
					<td colspan="2" class="view_text"><textarea rows="20"
							cols="100" title="내용" id="content" name="content"></textarea></td>
				</tr>
			</tbody>
		</table>
		<button type="submit" class="btn" id="write">작성하기</button>
		<button onclick="location.href='/list'" class="btn" id="list">목록으로</button>
	</form>
</body>
</html>
