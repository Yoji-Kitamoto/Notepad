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
import sql.SelectOneNotepad;
import sql.Update;

/**
 * Servlet implementation class NotepadUpdateServlet
 */
@WebServlet("/NotepadUpdateServlet")
public class NotepadUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotepadUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));

		Notepad notepad = (new SelectOneNotepad()).getOneNotepadInfo(id);

		request.setAttribute("notepad", notepad);

		request.getRequestDispatcher("WEB-INF/jsp/notepadUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		(new Update()).notepadUpdate(title, content, id);

		Admin admin = (Admin)request.getSession(true).getAttribute("admin");

		var login = new Login();

		List<Notepad> notepad = login.getNotepadInfo(String.valueOf(admin.getId()));

		request.setAttribute("notepad", notepad);

		request.getRequestDispatcher("WEB-INF/jsp/notepadList.jsp").forward(request, response);
	}
}
