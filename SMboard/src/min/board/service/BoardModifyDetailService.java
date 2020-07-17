package min.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardModifyDetailService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
// 수정할 글 번호를 숫자로 강제 변경하여 저장한다.
		int num = Integer.parseInt(request.getParameter("num"));
		boardDTO = boardDAO.getDetail(num);
		if (boardDTO == null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
// 수정 페이지로 이동할 때 원본 글 내용을 저장한다.
		System.out.println("(수정)상세보기 성공");
		request.setAttribute("boardDTO", boardDTO);
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_modify.jsp");
		return actionCommand;
	}
}