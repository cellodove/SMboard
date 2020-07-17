package min.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import min.board.command.ActionCommand;

public interface Action {
	// 비즈니스 요청을 수행하고 결과값을 반환하는 공통기능인 execute 메소드를 설정한다.
	public ActionCommand execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}