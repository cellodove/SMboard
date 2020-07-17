package min.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;
import min.board.model.BoardDTO;

public class BoardDetailService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
// 글 번호 파라미터값을 숫자로 강제 변경하여 저장한다.
		int num = Integer.parseInt(request.getParameter("num"));
// 글 내용을 확인하면 글의 조회수를 증가시킨다.
		boardDAO.setReadCountUpdate(num);
// 글의 내용을 읽은 후 얻은 결과를 저장한다.
		boardDTO = boardDAO.getDetail(num);
		if (boardDTO == null) {
			System.out.println("상세보기 실패");
			return null;
		}
		System.out.println("상세보기 성공");
// 저장된 글의 내용을 속성으로 다시 저장한다.
		request.setAttribute("boardDTO", boardDTO);
		ActionCommand actionCommand = new ActionCommand();
// 데이터 전달 방식을 포워드로 지정한다.
		actionCommand.setRedirect(false);
// 글 내용 페이지로 이동한다.
		actionCommand.setPath("./board/board_view.jsp");
		return actionCommand;
	}
}