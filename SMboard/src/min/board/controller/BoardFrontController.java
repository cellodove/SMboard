package min.board.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.action.Action;
import min.board.command.ActionCommand;
import min.board.service.BoardAddService;
import min.board.service.BoardDeleteService;
import min.board.service.BoardDetailService;
import min.board.service.BoardDownloadService;
import min.board.service.BoardListSevice;
import min.board.service.BoardModifyService;
import min.board.service.BoardModifyDetailService;
import min.board.service.BoardReplyService;
import min.board.service.BoardReplyMoveService;
import min.board.service.BoardSearchListService;

public class BoardFrontController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	// HTTP 요청을 파악하여 커맨드 핸들러에 전송한다.
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서블릿 맵핑명을 설정한다.
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String pathURL = requestURI.substring(contextPath.length());
		// 포워딩 정보를 저장한다.
		ActionCommand actionCommand = null;
		// 메소드를 규격화한다.
		Action action = null;
		// 맵핑명을 지정하고 서블릿 클래스를 설정한다.
		if (pathURL.equals("/BoardList.do")) {
			action = new BoardListSevice();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardWrite.do")) {
			actionCommand = new ActionCommand();
			//데이터 전달 방식을 포워드로 지정한다.
			actionCommand.setRedirect(false);
			//글 등록 페이지로 이동한다.
			actionCommand.setPath("./board/board_write.jsp");
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardAdd.do")) {
			action = new BoardAddService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardDetail.do")) {
			action = new BoardDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardDownload.do")) {
			action = new BoardDownloadService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardReply.do")) {
			action = new BoardReplyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardReplyMove.do")) {
			action = new BoardReplyMoveService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardModify.do")) {
			action = new BoardModifyDetailService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardModifyService.do")) {
			action = new BoardModifyService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardDelete.do")) {
			actionCommand = new ActionCommand();
			//데이터 전달 방식을 포워드로 지정한다.
			actionCommand.setRedirect(false);
			//글 삭제 페이지로 이동한다.
			actionCommand.setPath("./board/board_delete.jsp");
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardDeleteService.do")) {
			action = new BoardDeleteService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//맵핑명을 지정하고 서블릿 클래스를 설정한다.
		} else if (pathURL.equals("/BoardSearchList.do")) {
			action = new BoardSearchListService();
			try {
				actionCommand = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//전송된 방식에 따라 forward 방식과 redirect 방식을 처리한다.
		if (actionCommand != null) {
			//isRedirect 메소드 값이 false이면 forward 방식이고 true이면 redirect 방식이 된다.
			if (actionCommand.isRedirect()) {
				response.sendRedirect(actionCommand.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(actionCommand.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	//HTTP 요청이 get 메소드 방식인지를 확인하고 service 메소드를 호출한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	//HTTP 요청이 post 메소드 방식인지를 확인하고 service 메소드를 호출한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}
}