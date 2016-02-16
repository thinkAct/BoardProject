<%@ page contentType="text/html; charset=UTF-8" language="java"
	errorPage=""%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시물 쓰기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script language="javascript" type="text/javascript">
       function form_check(){
             if(document.form.name.value == ""){
                           alert("이름을 입력하세요.");
                           document.form.name.focus();
                           return false;
             }
             if(document.form.title.value == ""){
                           alert("제목을 입력하세요.");
                           document.form.title.focus();
                           return false;
             }
             if(document.form.content.value == ""){
                           alert("내용을 입력하세요.");
                           document.form.content.focus();
                           return false;
             }           
             if(document.form.password.value == ""){
                           alert("비밀번호를  입력하세요.");
                           document.form.password.focus();
                           return false;
             }
       document.form.submit();
       }
 
function addFileForm(){
 
  var tb1 = document.getElementById("file_table");
  if(9 >= tb1.rows.length) {
   var idx = getObj().parentElement.rowIndex + 1;
   var trow= tb1.insertRow(idx);
   var uploadOBJ="<input name='attatch' type='file' class='TEXT_FORM' id='f_id'><a onClick='javascript:addFileForm();'> 추가</a>  <a onClick='javascript:deleteRow();'>삭제</a> ";
   trow.insertCell(0).innerHTML = uploadOBJ;
  } else {
   alert("문서파일은 10개 이상 접수할 수  없습니다.");
   return;
  }
 }
 
 function getObj()
 {
     var obj = event.srcElement
     while (obj.tagName !='TD') //TD가 나올때까지의 Object추출
     {
         obj = obj.parentElement
     }
     return obj
 }
 
 function deleteRow(){
  var tb1 = document.getElementById("file_table");
 
  var idx = getObj().parentElement.rowIndex;
 
  if(tb1.rows.length-1 !=0){
   var tRow = tb1.deleteRow(idx);
  }else{
    document.getElementById('f_id').select();
       document.selection.clear();
  }
 }
</script>

</head>

<body>
	<H3>게시판 글쓰기</H3>
	<div style="width: 600px;">
		<div style="float: right;">
			<!--  /writeok.html 요청의 경우 컨트롤로의 write 메소드가 실행되도록 매핑되어있다 -->
			<form name="form" method="post" action="writeok.html">
				<table width="580" height="277" border="1" align="center">
					<tr>
						<td width="100">* 이 름</td>
						<td width="580">: <input name="name" type="text" size="50">
						</td>
					</tr>
					<tr>
						<td>* 제 목</td>
						<td>: <input name="title" type="text" size="50"></td>
					</tr>
					<tr align="center">
						<td colspan="2"><textarea name="content" cols="80" rows="10"></textarea></td>
					</tr>
					<tr>
						<td>* 파 일 :</td>
						<td><table id="file_table">
								<tr>
									<td><input name="attatch" type="file" class="TEXT_FORM"
										id="f_id"><a OnClick="javascript:addFileForm();">
											추가</a> <a OnClick="javascript:deleteRow();">삭제</a></td>
								</tr>
							</table></td>
					</tr>
					<tr>
						<td>* 비밀번호</td>
						<td>: <input type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="button" name="Submit" value="쓰기" onclick="form_check();"> 
							<input type="button" name="Submit2" value="취소" onclick="history.back();"></td>
					</tr>
				</table>
		</div>
	</div>
	</form>
</body>
</html>