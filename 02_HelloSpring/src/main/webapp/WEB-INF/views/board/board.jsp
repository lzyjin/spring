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

<c:set var="totalContents" value="${ requestScope.list.size() }"/>

	<p>총 ${totalContents }건의 게시물이 있습니다.</p>
        
    <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
           <c:if test="${ not empty requestScope.list }">
           		<c:forEach var="b" items="${ requestScope.list }">
           			<tr onclick="fn_boardView();">
           				<td id="boardNo"><c:out value="${ b.boardNo }"/></td>
           				<td id="boardTitle"><c:out value="${ b.boardTitle }"/></td>
           				<td id="boardWriter"><c:out value="${ b.boardWriter }"/></td>
           				<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${ b.boardDate }"/></td>
           				<td id="boardContent"><c:out value="${ b.boardContent }"/></td>
           				<td id="boardReadCount"><c:out value="${ b.boardReadCount }"/></td>
           				<%-- <td style="display:none;" id="boardDate"><c:out value="${ b.boardDate }"/></td> --%>
           			</tr>
           		</c:forEach>
           </c:if>
    </table> 
    
    <script>
    	const fn_boardView = () => {
    		
    		let boardNo = $("#boardNo").text();
    		let boardTitle = $("#boardTitle").text();
    		let boardWriter = $("#boardWriter").text();
    		let boardContent = $("#boardContent").text();
    		let boardDate = $("#boardDate").text();
    		let boardReadCount = $("#boardReadCount").text();
    		
    		console.log(boardNo + "," + boardTitle +"," + boardWriter + "," + boardContent + "," + boardDate + "," + boardReadCount);
    		
    		location.assign("${ path }/board/boardView.do?boardNo=" + boardNo 
    												  + "&boardTitle=" + boardTitle
    												  + "&boardWriter=" + boardWriter
    												  + "&boardContent=" + boardContent
    												  + "&boardDate=" + boardDate
    												  + "&boardReadCount=" + boardReadCount);
    		 
    	}
    </script>
    
</section>

		
		
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>