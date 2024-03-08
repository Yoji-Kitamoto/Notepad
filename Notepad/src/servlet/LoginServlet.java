package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import object.Admin;
import object.Notepad;
import sql.Login;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ログイン画面で入力された値を取得
		String userId   = request.getParameter("id");
		String password = request.getParameter("password");

		var login = new Login();
		Admin admin = login.check(userId, password);

		if(admin.getLoginFlag()) {
			System.out.println("ログイン成功");

			List<Notepad> notepad = login.getNotepadInfo(userId);

			// セッションオブジェクトを作成し、管理者情報を格納
			request.getSession(true).setAttribute("admin", admin);

			request.setAttribute("notepad", notepad);

			request.getRequestDispatcher("WEB-INF/jsp/notepadList.jsp").forward(request, response);
		} else {
			System.out.println("ログイン失敗");
			request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}
}
