package min.board.service;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.dao.BoardDAO;

public class BoardSearchListService implements Action {
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyword = null;
// 태그의 속성값을 호출한다.
		keyword = (String) request.getParameter("keyword");
		String keyfield = null;
// 태그의 속성값을 호출한다.
		keyfield = (String) request.getParameter("keyfield");
		BoardDAO boardDAO = new BoardDAO();
		List<?> searchBoardlist = new ArrayList<Object>();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int searchlistcount = boardDAO.getSearchListCount(keyword, keyfield);
		searchBoardlist = boardDAO.getSearchList(keyword, keyfield, page, limit);
		int maxpage = (int) ((double) searchlistcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("searchlistcount", searchlistcount);
		request.setAttribute("searchBoardlist", searchBoardlist);
		request.setAttribute("keyword", keyword);
		request.setAttribute("keyfield", keyfield);
		ActionCommand actionCommand = new ActionCommand();
		actionCommand.setRedirect(false);
		actionCommand.setPath("./board/board_search_list.jsp");
		return actionCommand;
	}
}