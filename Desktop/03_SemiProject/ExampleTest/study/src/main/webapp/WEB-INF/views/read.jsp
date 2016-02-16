<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시물 읽기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script>
       function write_ok(){
             writeform.submit();
       }
       function update_ok(){
    	   document.viewform.action = 'updatePage.html';
    	   document.viewform.submit();
       }
      function delete_ok(){
    	  document.viewform.action = "boardDelete";
    	  document.viewform.submit();
      }
</script>
</head>
<body>
	<div style="width: 600px;">
		<div style="float: enter;">
			<table width="600" height="420" border="1" align="center">
				<tr height="20">
					<h2>상세보기 게시판</h2>
				</tr>

				<form name="viewform" method="POST">
					<input type="hidden" name="seq" value="${read.seq}"> <input
						type="hidden" name="reply" value="${read.reply}"> <input
						type="hidden" name="reply_step" value="${read.reply_step} ">
					<input type="hidden" name="reply_level" value="${read.reply_level}">
				<tr>
					<td width="100">* 이름</td>
					<td width="500">: <input name="name" type="text"
						value="${read.name}" size="50" readonly>
					</td>
				</tr>
				<tr>
					<td>* 제목</td>
					<td>: <input name="title" type="text" value="${read.title}"
						size="50" readonly></td>
				</tr>
				<tr align="center">
					<td colspan="2"><textarea name="content" cols="80" rows="12"
							readonly>${read.content}</textarea></td>
				</tr>
				<tr>
					<td>* 파일</td>
					<td>: <c:choose>
							<c:when test="${read.fileName != null}">
                                 ${read.fileName}                                     
                           </c:when>
							<c:when test="${read.fileName == null}">
                                 파일 없음
                           </c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
					<%-- <input name="button" type="button"
						onClick="location.href='./reply.html?seq=${read.seq}'" value="답변">
						| --%>
						 <input name="button" type="button" onClick="update_ok()"
						value="수정"> | <input name="button" type="button"
						onClick="delete_ok()" value="삭제">
						| <input name="button" type="button"
						onClick="location.href='./list.html'" value="목록"></td>
				</tr>
				</form>
				<tr>
					<td height="99" colspan="2">
						<!-- BoardMultiController의 comment() 메소드 호출 -->
						<form method="post" action="comment.html">
							<table border="1">
								<tr>
									<td>이름 :</td>
									<td><input type="text" name="name"></td>
									<td>코멘트:</td>
									<td><input type="text" name="comment"></td>
									<td><input type="submit" name="Button" value="쓰기"></td>
								</tr>
							</table>
							<input type="hidden" name="seq" value="${read.seq}">
						</form> <!--  달려있는 커멘트 보기 -->
						<table width="789" border="1">
							<c:forEach var="comment" items="${comments}">
								<tr>
									<td width="42" align="center">*</td>
									<td width="86">${comment.name}</td>
									<td width="639">${comment.comment}</td>
								</tr>
							</c:forEach>
						</table>


					</td>
				</tr>

			</table>
			<br> <br> <input type="hidden" name="seq"
				value="${read.seq}"> <input type="hidden" name="reply"
				value="${read.reply}"> <input type="hidden"
				name="reply_step" value="${read.reply_step}"> <input
				type="hidden" name="reply_level" value="${read.reply_level}">
		</div>
	</div>
</body>
</html>