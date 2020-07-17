package min.board.service;

import java.io.FileInputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;

public class BoardDownloadService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("attached_file");
		String savePath = "./boardUpload";
// Servlet의 실행환경 정보를 반환한다.
		ServletContext context = request.getServletContext();
// 파일이 저장된 서버의 경로를 반환한다.
		String downPath = context.getRealPath(savePath);
// 다운로드 시에 사용할 경로를 지정한다.
		String fielPath = downPath + "\\" + fileName;
// 임시기억 장소로 한 번에 보낼 수 있는 4kb의 크기를 지정한다.
		byte b[] = new byte[4096];
		FileInputStream fileInputStream = new FileInputStream(fielPath);
		String sEncoding = null;
		try {
// 익스플로러의 버전을 확인한다.
			boolean MSIE = (request.getHeader("user-agent").indexOf("MSIE") != -1)
					|| (request.getHeader("user-agent").indexOf("Trident") != -1);
// 서버에 존재하는 파일의 MIME 타입을 문자열로 반환한다.
			String downType = request.getServletContext().getMimeType(fielPath);
			if (downType == null)
// 8비트로 된 일련의 데이터로 지정되지 않은 MIME 타입을 지정한다.
				downType = "application/octet-stream";
			response.setContentType(downType);
			if (MSIE) {
// 익스플로러의 저장될 파일 이름에 대한 공백을 처리한다.
				sEncoding = new String(fileName.getBytes("EUC-KR"), "ISO-8859-1").replaceAll("\\+", "%20");
			} else {
// 한글 인코딩 처리를 한다.
				sEncoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}
//익스플로러의 다운로드 창이 뜨도록 설정한다.
			response.setHeader("Content-Disposition", "attachment;filename=\"" + sEncoding + "\"");
//출력 스트림으로부터 데이터를 byte 단위로 클라이언트로 출력한다.
			ServletOutputStream servletOutputStream = response.getOutputStream();
			int nunRead;
//0번부터 배열 객체의 크기로 웹브라우저로 출력한다.
			while ((nunRead = fileInputStream.read(b, 0, b.length)) != -1) {
//버퍼의 길이만큼 데이터를 채운다.
				servletOutputStream.write(b, 0, nunRead);
			}
			servletOutputStream.flush();
			servletOutputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}