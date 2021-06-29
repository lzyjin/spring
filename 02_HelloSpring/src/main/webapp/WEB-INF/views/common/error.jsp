<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<!-- jsp액션태그는 java 내에서 접근하는거라서 jsp에 접속 가능한 것 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="Hello Spring"/>
</jsp:include>
		
		
		
	<section id="content">
		<h1 style="color : red;"><%= exception.getMessage() %></h1>
		
		<p>3초 후 메인화면으로 이동합니다</p>
		
		
		<script>
		setTimeout(() => {
			location.replace("${ path}");
		}, 3000);
	</script>
		
		
	</section>
		
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>