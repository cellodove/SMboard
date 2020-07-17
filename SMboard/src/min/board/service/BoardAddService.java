package min.board.service;

import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardAddService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		ActionCommand actionCommand = new ActionCommand();
		String realFolder = "";
		String saveFolder = "./boardUpload";
		// 실제 서버의 업로드 경로를 지정한다.
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		int fileSize = 10 * 1024 * 1024;
		boolean result = false;
		try {
			MultipartRequest multiRequest = new MultipartRequest(request, realFolder, fileSize, "UTF-8",
					new DefaultFileRenamePolicy());
			boardDTO.setName(multiRequest.getParameter("name"));
			boardDTO.setPass(multiRequest.getParameter("pass"));
			boardDTO.setSubject(multiRequest.getParameter("subject"));
			boardDTO.setContent(multiRequest.getParameter("content"));
			boardDTO.setAttached_file(multiRequest.getFilesystemName((String) multiRequest.getFileNames().nextElement()));
			// 게시판 글 등록을 처리한다.
			result = boardDAO.boardInsert(boardDTO);
			if (result == false) {
				System.out.println("게시판 등록 실패");
				return null;
			}
			System.out.println("게시판 등록 완료");
			// 데이터 전달 방식을 리다이렉트로 지정한다.
			actionCommand.setRedirect(true);
			// 글 목록 페이지로 이동한다.
			actionCommand.setPath("./BoardList.do");
			return actionCommand;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}