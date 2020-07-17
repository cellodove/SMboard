package min.board.service;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardModifyService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		boolean result = false;
		String realFolder = "";
		String saveFolder = "./boardUpload";
		int fileSize = 10 * 1024 * 1024;
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		try {
			MultipartRequest multiRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multiRequest.getParameter("num"));
// 글의 비밀번호와 수정 페이지에서 입력한 비밀번호가 일치하는지 확인한다.
			boolean usercheck = boardDAO.isBoardWriter(num, multiRequest.getParameter("pass"));
// 수정할 권한이 없으며 글 목록 페이지로 이동한다.
			if (usercheck == false) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('수정할 권한이 없습니다.');");
				out.println("location.href='./BoardList.do';");
				out.println("</script>");
				out.close();
				return null;
			}
			boardDTO.setNum(num);
			boardDTO.setName(multiRequest.getParameter("name"));
			boardDTO.setSubject(multiRequest.getParameter("subject"));
			boardDTO.setContent(multiRequest.getParameter("content"));
			boardDTO.setAttached_file(
					multiRequest.getFilesystemName((String) multiRequest.getFileNames().nextElement()));
//업로드한 파일에 이전 이름을 지정한다.
			boardDTO.setOld_file(multiRequest.getParameter("old_file"));
//내용을 수정 처리한다.
			result = boardDAO.boardModify(boardDTO);
			if (result == false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
//데이터 전달 방식을 리다이렉트로 지정한다.
			actionCommand.setRedirect(true);
//글 내용 상세 페이지로 이동한다.
			actionCommand.setPath("./BoardDetail.do?num=" + boardDTO.getNum());
			return actionCommand;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}