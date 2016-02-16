<%@ page contentType="text/html; charset=euc-kr" language="java" import="java.sql.*" errorPage="" pageEncoding="UTF-8"%>
<%@ page import = "javax.rmi.* , javax.naming.* , java.util.* " %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>

<html>
<head>
<title>rptlvks</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<style type="text/css">
	#wrapper{
		width:500px;
		margin:0 auto;
		border:1px solid #000;
	}
</style>
</head>

<body>
	<div style="width:700px;">
	<div style="float:right;">
	<div align="center">
	<H3>데모 게시판</H3>
	<h5>총 ${list.size()}건</h5>
	<table width="700" border="1" align="center">
	  	<tr align="center">
    		<td width="10%" align="center">번호</td>
    		<td width="40%" align="center">제목</td>
    		<td width="20%" align="center">이름</td>
    		<td width="20%" align="center">날짜</td>
    		<td width="10%" align="center">조회</td>
  		</tr>
<!—컨트롤러에서 돌려주는 게시판 리스트를 담은 list를 가져와서 board라 명명 후 개 수 만큼 반복 -->
             <c:forEach var="board" items="${list}">      
  <tr>
    <td align="center">
                    <c:if test="${board.reply_step == 0}">
                                 ${board.seq}
                    </c:if>
                    <c:if test="${board.reply_step != 0}">
                                  
                    </c:if>                  
    </td>
    <td>      <!-- 게시물은 덧글에 따른 번호와 덧글 존재 유무로 정렬됨 -->
                    <c:choose>                
                           <c:when test="${board.reply_step != 0}"><!-- 게시글이 덧글일 경우 -->
                                 <c:forEach var="i" begin="1" end="${board.reply_level}" step="1"><!-- 레벨의 수만큼 글을 뒤로 민다 -->
                                          
                                 </c:forEach>
                                        <%-- <a href="read.html?seq=${board.seq}" onmouseover="contentprev('${board.seq}');showlayer('layer1');" onmouseout="hidelayer('layer1');">${board.title}</a> --%>
                                        <!-- 마우스를 올리면 게시물 번호에 따른 showlayer(게시물 미리보기 창)가 실행됨 -->
                           </c:when>
                           <c:when test="${board.reply_step == 0}">
                                        <a href="read.html?seq=${board.seq}"  onmouseover="contentprev('${board.seq}');showlayer('layer1');" onmouseout="hidelayer('layer1');">${board.title}</a>                    
                           </c:when>
                    </c:choose>
   </td>
    <td align="center">${board.name}</td>
    <td align="center">${board.regdate}</td>
    <td align="center">${board.readCount}</td>
  </tr>
             </c:forEach>
 
  <tr>
    <td align="center"><input type="button" value="쓰기" onclick="location.href='write.html'"></td>
    <td> </td>
    <td> </td>
    <td> </td>
    <td> </td>
  </tr>
</table>
</div>
</div>
</div>
</body>
</html>