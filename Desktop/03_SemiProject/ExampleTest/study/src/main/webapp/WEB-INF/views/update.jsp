<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" href="/board/css/boardCss.css">
<script type="text/javascript" src="/board/js/boardActionJs.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Board Read</title>

<script>
function updateButton(){
	
	   document.boardUpdateForm.action = 'updateComplete';
	   document.boardUpdateForm.submit();
	   
}
</script>
</head>  
<body> 
<center> 
<h1>게시판 업데이트 화면 </h1><hr>
	<form name="boardUpdateForm" action="update.html" method="post">
 		<input type="hidden" name="seq" value="${board.seq}"> 
		<table id="readTable" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td>제목:</td>   
				<td class="readTD">
					<input type="text" id="title" name="title" value="${board.title}">
				</td>
			</tr>  
			<tr>
				<td>작성자:</td>
				<td class="readTD">
					<input type="text" id="name" name="name" value="${board.name}">
				</td>
			</tr> 
			<tr>  
				<td>내용:</td> 
				<td><textarea rows="10" cols="77%" id="content" name="content">${board.content}</textarea></td>
			</tr>
		</table>

		<table class="readTable02">
			<tr>
				<td colspan="2" id="readButtonTD">
					<input type="button" value="수정" onclick="updateButton();">
					<input type="button" value="목록" onclick="location.href='/board/read.html?seq=${board.seq}'">
				</td> 
			</tr>
		</table>

	</form>
</center>
</body>
</html>
<!-- 
------------------------------------------------------------------------------------------
  
// 검색 
function boardListSearchGo(){

	document.listForm.action ="/board/list.html"; 
	document.listForm.submit();
} 
  
// 검색Text입력 후 바로 엔터 가능하게 하는 이벤트
function enterEvent(){
	
	if(window.event.keyCode == 13){
		boardListSearchGo();
	}
}

// 미리보기(Ajax)
  
var xmlHttp = null;
var xmlDoc  = null;
var message = null;

function createXMLHttpRequest(){

	// 익스플로러이면 if 그외 브라우저이면 else
	if(window.ActiveXObject){
		try{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // IE 5.0 이하 버전
		}catch(e){
			try{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // IE 5.0 이상 버전
			}catch(el){xmlHttp = null;}
		}
	}else if(window.XMLHttpRequest){
		try{
			xmlHttp = new XMLHttpRequest();
		}catch(e){
			xmlHttp = null;
		}
	}
	
	if(xmlHttp == null){errorMessage();}
  
	return xmlHttp;
	
}
 
function errorMessage(){alert("지원할 수 없는 브라우저 입니다.");}


function contentprev(seq){ 
	  
	var url = "ContentPreview?seq="+seq;
	xmlHttp = createXMLHttpRequest();
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.open("get",url,true);
	xmlHttp.send(null);
}

function handleStateChange(){ 
	
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){ 
			xmlDoc = xmlHttp.responseText; 
			document.getElementById("layer1").innerHTML = xmlDoc;
			//화면에서 마우스가 움직일때 movetip()함수 발생  
			document.onmousemove = movetip;
			showlayer();
		}
	} 
}  
 
// 게시판 글제목에서 마우스가 올라갔을 경우 발생
function showlayer(){
    document.getElementById("layer1").style.visibility="visible";
}
  
// 게시판 글제목에 마우스가 떨어져 있을 경우 발생
function hidelayer(){
    document.getElementById("layer1").style.visibility="hidden";
}

// 미리보기 위치 
function movetip(){
	document.getElementById("layer1").style.pixelTop  = event.y+document.body.scrollTop+20;
	document.getElementById("layer1").style.pixelLeft = event.x+document.body.scrollLeft+20;
}   


// 코멘트 저장
function commentInput(){
	var comment_name = document.getElementById("comment_name");
	var comment_comm = document.getElementById("comment_comm");

	if(comment_name.value == ""){
		alert("코멘트 이름을 적으세요.");
		comment_name.focus();
		return false;
	}	
	if(comment_comm.value == ""){
		alert("코멘트 내용을 적으세요.");
		comment_comm.focus();
		return false;
	} 

	boardReadForm.action="/board/comment.html";
	boardReadForm.submit();
	
}

// 글입력
function writeGoGo(){
	if(document.writeForm.title.value == ""){
		alert("글 제목을 입력하세요.");
		document.writeForm.title.focus();
		return false;
	}
	if(document.writeForm.name.value == ""){
		alert("작성자를 입력하세요.");
		document.writeForm.name.focus();
		return false;
	}
	if(document.writeForm.passwd.value == ""){
		alert("비밀번호를 입력하세요.");
		document.writeForm.passwd.focus();
		return false;
	}
	if(document.writeForm.content.value == ""){
		alert("글 내용을 입력하세요.");
		document.writeForm.content.focus();
		return false;
	}
	
	writeForm.action="/board/writeOk.html";
	writeForm.submit();
	
}

// 수정
function updateButton(){
	if(document.boardUpdateForm.title == ""){
		alert("글 제목을 입력하세요.");
		document.boardUpdateForm.title.focus();
		return false;
	}
	if(document.boardUpdateForm.name == ""){
		alert("작성자를 입력하세요.");
		document.boardUpdateForm.name.focus();
		return false;
	}
	if(document.boardUpdateForm.content == ""){
		alert("글 내용을 입력하세요.");
		document.boardUpdateForm.content.focus();
		return false;
	}
	
	boardUpdateForm.action = "/board/update.html";
	boardUpdateForm.submit();
} -->