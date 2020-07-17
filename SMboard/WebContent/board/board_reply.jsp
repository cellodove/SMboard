<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어깨동무 게시판</title>
<link rel="shortcut icon" href="./img/favicon.ico" type="image/x-icon">
<link rel="stylesheet" type="text/css" href="./css/jboard.css">
</head>
<body>
	<div id="contentsArea">
		<section id="titlename">
			<h1>답변 글 등록</h1>
			<p class="formSign">
				<strong class="require">필수</strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<form action="./BoardReply.do" method="post" id="joinForm" name="boardform" enctype="multipart/form-data">
				<fieldset>
					<%--hidden 값으로 원본 글의 정보를 참조하여 답변 글의 내용을 설정한다.--%>
						<input type="hidden" name="num"	value="<c:out value='${boardDTO.num}'/>" /> 
						<input type="hidden" name="answer_num" value="<c:out value='${boardDTO.answer_num}'/>" /> 
						<input type="hidden" name="answer_lev" value="<c:out value='${boardDTO.answer_lev}'/>" /> 
						<input type="hidden" name="answer_seq" value="<c:out value='${boardDTO.answer_seq}'/>" />
					<legend>답변 글 </legend>
					<p>
						<label for="name">글쓴이 <strong class="require">필수</strong></label>
						<input type="text" id="name" name="name" required placeholder="홍길동">
					</p>
					<p>
						<label for="subject">제목</label>
						<%--원본 글의 제목을 호출한다.--%>
						<input type="text" id="subject" name="subject" value="[답변]<c:out value="${boardDTO.subject}"/>">
					</p>
					<p>
						<label for="content">내용</label>
						<textarea name="content" cols="74" rows="15"></textarea>
					</p>
					<p>
						<label for="attached_file">파일 수정</label> 
						<input type="file" id="attached_file" name="attached_file" />
					</p>
					<p>
						<label for="pass">비밀번호 <strong class="require">필수</strong></label>
						<input type="password" id="pass" name="pass" required placeholder="비밀번호입력" size="12" />
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">글 등록</button>
						<button type="reset" class="btnCancel">취소</button>
						<button type="button" value="button" onclick="location.href='./BoardList.do'" class="btnOk">목록</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>