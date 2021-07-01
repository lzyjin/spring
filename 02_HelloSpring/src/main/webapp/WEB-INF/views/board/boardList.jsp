<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<!-- jsp액션태그는 java 내에서 접근하는거라서 jsp에 접속 가능한 것 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="Board"/>
</jsp:include>
		
		
<section id="board-container" class="container">

<c:set var="totalData" value="${ requestScope.totalData }"/>

	<br><br>
	
	<p>총 ${totalContents }건의 게시물이 있습니다.</p>
	
	<button type="button" class="btn btn-outline-success btn-block" onclick="location.assign('${ path }/board/boardForm.do')">게시글 작성</button>
	<br><br>
        
    <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:choose>
           <c:when test="${ not empty requestScope.list }">
           		<c:forEach var="b" items="${ requestScope.list }">
           			<!-- <tr onclick="fn_boardView(event);"> -->
           			<tr>
           				<td class="boardNo"><c:out value="${ b.boardNo }"/></td>
           				<td><a href="${ path }/board/boardView.do?no=${b.boardNo}"><c:out value="${ b.boardTitle }"/></a></td>
           				<td><c:out value="${ b.boardWriter }"/></td>
           				<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${ b.boardDate }"/></td>
           				<td>
           					<c:choose>
           					<c:when test="${ b.attachments.size() > 0 && b.attachments[0].originalFileName!=null}">
           						<c:out value="${ b.attachments.size() }"/>
           					</c:when>
           					<c:otherwise>
           						<c:out value="첨부 파일이 없습니다."/>
           					</c:otherwise>
           					</c:choose>
           				</td>
           				<td><c:out value="${ b.boardReadCount }"/></td>
           			</tr>
           		</c:forEach>
           		</c:when>
           		<c:otherwise>
           			<tr colspan="6">
           				<td>조회된 자료가 없습니다</td>
           			</tr>
           		</c:otherwise>
           </c:choose>
           
    </table> 
    
    <div id="pagebar-container">
    	${ pagebar }
    </div>
    
    <script>
    	const fn_boardView = (e) => {
    		
    		//console.log($(e.target));
    		
    		let boardNo = $(e.target).parent().find("td.boardNo").text();
    		
    		console.log(boardNo);
    		
    		location.assign("${ path }/board/boardView.do?no=" + boardNo);
    		 
    	}
    </script>
    
</section>

		
		
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>