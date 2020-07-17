package min.board.service;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;

public class BoardDeleteService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionCommand actionCommand = new ActionCommand();
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO boardDAO = new BoardDAO();
		usercheck = boardDAO.isBoardWriter(num, request.getParameter("pass"));
		if (usercheck == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제할 권한이 없습니다.');");
			out.println("location.href='./BoardList.do';");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제하였습니다.');");
			out.println("location.href='./BoardList.do';");
			out.println("</script>");
			out.close();
		}
// 게시판 글 삭제를 처리한다.
		result = boardDAO.boardDelete(num);
		if (result == false) {
			System.out.println("게시판 삭제 실패");
			return null;
		}
		System.out.println("게시판 삭제 성공");
		actionCommand.setRedirect(true);
		actionCommand.setPath("./BoardList.do");
		return actionCommand;
	}
}