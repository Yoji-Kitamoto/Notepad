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
import sql.Create;
import sql.Login;

/**
 * Servlet implementation class NotepadCreateServlet
 */
@WebServlet("/NotepadCreateServlet")
public class NotepadCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotepadCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/jsp/notepadCreate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字コードの設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String title   = request.getParameter("title");
		String content = request.getParameter("content");

		Admin admin = (Admin)request.getSession(true).getAttribute("admin");

		var create = new Create();
		create.NotepadCreate(admin.getId(), title, content);

		var login = new Login();
		List<Notepad> notepad = login.getNotepadInfo(String.valueOf(admin.getId()));

		request.setAttribute("notepad", notepad);

		request.getRequestDispatcher("WEB-INF/jsp/notepadList.jsp").forward(request, response);
	}
}
