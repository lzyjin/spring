<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<!-- jsp액션태그는 java 내에서 접근하는거라서 jsp에 접속 가능한 것 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="Board상세"/>
</jsp:include>
	<style>
   	 	div#board-container{width:400px; margin:0 auto; text-align:center;}
    	div#board-container input,div#board-container button{margin-bottom:15px;}
    	div#board-container label.custom-file-label{text-align:left;}
    </style>

<section id="container">


	<h1 style="text-align:center;">게시글 상세화면</h1>
    
	<div id="board-container">
        <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required value="${ b.boardTitle }">
        <input type="text" class="form-control" name="boardWriter"  readonly required value="${ b.boardWriter }">

        <c:if test="${ b.attachments.size() > 0 }">
        	<c:forEach var="a" items="${ b.attachments }" varStatus="vs">
        		<button type="button" class="btn btn-outline-success btn-block" 
        				onclick="location.assign('${ path }/board/fileDownload.do?oriname=${a.originalFileName }&rename=${ a.renameFileName }')">
        				<c:out value="첨부파일 ${ vs.count } - ${ a.originalFileName }"></c:out>
        		</button>
        	</c:forEach>
        </c:if>
        
        <textarea class="form-control" name="boardContent" placeholder="내용" required><c:out value="${ b.boardContent }"/></textarea>
    </div>

    
</section>			
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>