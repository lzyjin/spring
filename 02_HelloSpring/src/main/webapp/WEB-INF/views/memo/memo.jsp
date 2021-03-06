<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="Memo Page"/>
</jsp:include>
		

<style>
    div#memo-container{width:60%; margin:0 auto;}
</style>

<section>

    <div id="memo-container">
        <form action="${ path }/memo/memoInsert.do" class="form-inline">
            <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
            <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
            <button class="btn btn-outline-success" type="submit" >저장</button>
        </form>
    </div>
    
     <br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
            
            <c:if test="${ not empty list }">
            	<c:forEach var="m" items="${ list }">
            		<tr>
            			<td><c:out value="${ m.memoNo }"/></td>
            			<td><c:out value="${ m.memo }"/></td>
            			<td><fmt:formatDate type="both" dateStyle="long" timeStyle="short" value="${ m.memoDate }"/></td>
            			<td><button onclick="fn_del_memo(event);">메모삭제</button></td>
            		</tr>
            	</c:forEach>
            </c:if>

        </table>

</section>

<script>
	const fn_del_memo = (e) => {
		
		// console.log($(e.target));
		
		let memoNo = $(e.target).parent().siblings().first().html();
		
		// console.log(memoNo);
		
		location.assign("${ path }/memo/memoDelete.do?no="+memoNo);
		
		
	}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
