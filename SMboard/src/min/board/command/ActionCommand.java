package min.board.command;

public class ActionCommand {
	// 특정 페이지에 대한 이동에 대한 응답으로 forward로 페이지 이동한다.
	private boolean redirect = false;
	// 경로를 지정하여 저장하고자 하는 파일을 저장한다.
	private String path = null;

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}