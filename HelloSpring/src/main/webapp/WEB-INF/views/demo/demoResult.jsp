<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="demoResult"/>
</jsp:include>

<style>
	table#tbl-dev{
		margin : 0 auto;
		width : 50%;
	}
</style>


<section id="container">
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td><c:out value="${ requestScope.dev.devName }"/></td>
		</tr>
		<tr>
			<th scope="col">나이</th>
			<td><c:out value="${ requestScope.dev.devAge }"/></td>
		</tr>
		<tr>
			<th scope="col">이메일</th>
			<td><c:out value="${ requestScope.dev.devEmail }"/></td>
		</tr>
		<tr>
			<th scope="col">성별</th>
			<td><c:out value="${ requestScope.dev.devGender }"/></td>
		</tr>
		<tr>
			<th scope="col">개발가능언어</th>
			<td>
				<c:if test="${ not empty requestScope.dev.devLang }">
					<c:forEach items="${ requestScope.dev.devLang }" var="value" varStatus="vs">
						<c:out value="${ value }"/>
						<c:if test="${ not vs.last }">
							<c:out value=","/>
						</c:if>
					</c:forEach>

				</c:if>
			</td>
		</tr>	
	
	</table>
</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>