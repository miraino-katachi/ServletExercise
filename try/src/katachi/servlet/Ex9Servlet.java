package katachi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import katachi.dao.TeamDao;
import katachi.dao.UserDao;
import katachi.entity.Team;
import katachi.entity.User;

@WebServlet("/useradd")
public class Ex9Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeamDao teamDao;
	private UserDao userDao;

	@Override
	public void init() throws ServletException {
		super.init();
		teamDao = new TeamDao();
		userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Team> teamList = teamDao.selectAll();

		request.setAttribute("teamList", teamList);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/add.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		ArrayList<String> errorList = new ArrayList<>();

		String name = request.getParameter("name");

		if (isBlank(name)) {
			errorList.add("名前が入力されていません");
		} else if (isRegistered(name)) {
			errorList.add("同じ名前が登録されています");
		}

		String value = request.getParameter("team_id");
		int teamId = 0;
		if (isBlank(value)) {
			errorList.add("チームが選択されていません");
		} else {
			teamId = Integer.parseInt(value);
		}

		if (errorList.size() == 0) {
			User user = new User(teamId, name);
			if (!userDao.insert(user)) {
				errorList.add("登録に失敗しました");
			}
		}
		if (errorList.size() > 0) {
			request.setAttribute("name", name);
			request.setAttribute("team_id", teamId);
			request.setAttribute("errors", errorList);

			doGet(request, response);
		} else {
			response.sendRedirect("/try/users");
		}
	}

	private boolean isBlank(String value) {
		if (value == null || value.isBlank()) {
			return true;
		}
		return false;
	}

	private boolean isRegistered(String name) {
		if (userDao.selectByName(name) == null) {
			return false;
		}
		return true;
	}
}
