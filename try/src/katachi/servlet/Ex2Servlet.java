package katachi.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex1Servlet
 */
@WebServlet("/ex2")
public class Ex2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		ArrayList<String> errorList = new ArrayList<>();

		// 名前
		String name = req.getParameter("name");
		req.setAttribute("name", name);

		// 誕生日
		String value = req.getParameter("birthday");
		if (!isNotBlank(value)) {
			errorList.add("誕生日が入力されていません");
		} else {
			Date birthday = strToDate(value);
			if (birthday == null) {
				errorList.add("誕生日が不正です");
			}
			req.setAttribute("birthday", birthday);
		}

		// 性別
		String gender = req.getParameter("gender");
		req.setAttribute("gender", gender);

		// 血液型
		String bloodType = req.getParameter("bloodtype");
		req.setAttribute("bloodtype", bloodType);

		// 学習内容
		String[] subjects = req.getParameterValues("subjects");
		req.setAttribute("subjects", subjects);

		// 備考
		String note = req.getParameter("note");
		if (!isNotBlank(note)) {
			errorList.add("備考が入力されていません");
		} else if (!isLength(note, 0, 100)) {
			errorList.add("備考は100文字以内にしてください");
		}
		req.setAttribute("note", note);

		RequestDispatcher dispatcher;
		if (errorList.size() > 0) {
			req.setAttribute("errors", errorList);
			dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/input.jsp");
		} else {
			dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/display.jsp");
		}
		dispatcher.forward(req, resp);
	}

	private boolean isNotBlank(String value) {
		if (value == null || value.isBlank()) {
			return false;
		}
		return true;
	}

	private Date strToDate(String value) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean isLength(String value, int min, int max) {
		if (min <= value.length() && value.length() <= max) {
			return true;
		}
		return false;
	}
}
