<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp"> 
	<jsp:param  name="str" value="Board글쓰기"/>
</jsp:include>

<style>
	    div#board-container{width:400px; margin:0 auto; text-align:center;}
	    div#board-container input{margin-bottom:15px;}
</style>

<section id="container">

	<div id="board-container">
		<!-- 파일을 업로드하기 때문에  enctype이 multipart/form-data임 -->
        <form name="boardFrm" action="${path }/board/insertBoard.do" method="post" enctype="multipart/form-data"  >
        
            <input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required>
            <input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="boardWriter" value="${loginMember.userId}" readonly required>
            
            
<!-- 1  -->   
            <div class="input-group mb-3" style="padding:0px;">
                <!-- 
                	단순히 cos.jar를 설치하는 것만으로는 requestdispatcher를 넘어가서 handlermapper를 넘어가는 구조라 새로운 라이브러리가 필요
                	commons-io > 2.6
                	commons-fileupload > 1.3.3
                 -->
                <div class="input-group-prepend" style="padding:0px;">
                    <span class="input-group-text">첨부파일1</span>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="upFile" id="upFile1">
                    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
                </div>
            </div>
                    <!-- 여러 개의 파일 업로드를 위해서 위의 것을 복사해서 붙여넣기 할게요-->
            
            
            
<!-- 2  -->         
            <div class="input-group mb-3" style="padding:0px;">
                <div class="input-group-prepend" style="padding:0px;">
                    <span class="input-group-text">첨부파일2</span>
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="upFile" id="upFile1">
                    <label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
                </div>
            </div>
            
            <textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
            <br />
            <input type="submit" class="btn btn-outline-success" value="저장" >
        </form>
    </div>
    
    
    <script>
		$(function(){
			$("[name=upFile]").change(e=>{
				const fileName = $(e.target).prop("files")[0].name;
				
						console.log($(e.target).prop("files"));
						
				$(e.target).next(".custom-file-label").html(fileName);
			});
		})
	</script>
    

</section>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>