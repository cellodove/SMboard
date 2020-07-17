<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
			<h1>게시판 글쓰기</h1>
			<p class="formSign">
				<strong class="require"> 필수 </strong> 는 반드시 입력하여야 하는 항목입니다.
			</p>
			<%--파일 업로드 할 수 있도록 설정한다.--%>
			<form action="./BoardAdd.do" method="post" id="joinForm" name="boardform" enctype="multipart/form-data">
				<fieldset>
					<legend> 게시판 글쓰기 </legend>
					<p>
						<label for="name">글쓴이 <strong class="require">필수</strong></label>
						<input type="text" id="name" name="name" required placeholder="홍길동" />
					</p>
					<p>
						<label for="password">암호 <strong class="require">필수</strong></label>
						<input type="password" id="password" name="pass" required placeholder="영문/숫자 4~8자 이내" />
					</p>
					<p>
						<label for="subject">제목 <strong class="require">필수</strong></label>
						<input type="text" id="subject" name="subject" required placeholder="글의 제목을 입력하세요." />
					</p>
					<p>
						<label for="content">내용 <strong class="require">필수</strong></label>
						<textarea id="content" name="content" cols="74" rows="15" required placeholder="글의 내용을 입력하세요."></textarea>
					</p>
					<p>
						<label for="attached_file">파일 첨부 </label> 
						<input type="file"id="attached_file" name="attached_file" placeholder="파일 첨부" />
					</p>
					<div class="btnJoinArea">
						<button type="submit" class="btnOk">글 등록</button>
						<button type="reset" class="btnCancel">글 취소</button>
						<button type="button" value="button"onclick="location.href='./BoardList.do'" class="btnOk">목록</button>
					</div>
				</fieldset>
			</form>
		</section>
	</div>
</body>
</html>