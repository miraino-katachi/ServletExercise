package katachi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import katachi.entity.User;

public class UserDao {

	private final String SELECT_ALL = "SELECT user.name, team.name FROM user"
			+ " JOIN team ON user.team_id = team.team_id";
	private final String SELECT_BY_NAME = "SELECT * FROM user"
			+ " WHERE name = ?";
	private final String INSERT = "INSERT INTO user(team_id, name) VALUES(?, ?)";

	public List<User> selectAll() {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		List<User> userList = new ArrayList<User>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb1?serverTimezone=JST&sslMode=DISABLED",
					"root", "");

			pStmt = conn.prepareStatement(SELECT_ALL);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				String name = rs.getString(1);
				String teamName = rs.getString(2);
				User user = new User(name, teamName);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pStmt != null) {
					pStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return userList;
	}

	public User selectByName(String name) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		User user = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb1?serverTimezone=JST&sslMode=DISABLED",
					"root", "root");

			pStmt = conn.prepareStatement(SELECT_BY_NAME);
			pStmt.setString(1, name);
			rs = pStmt.executeQuery();

			if (rs.first()) {
				user = new User(rs.getInt(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pStmt != null) {
					pStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public boolean insert(User user) {
		Connection conn = null;
		PreparedStatement pStmt = null;

		boolean ret = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb1?serverTimezone=JST&sslMode=DISABLED",
					"root", "root");

			pStmt = conn.prepareStatement(INSERT);
			pStmt.setInt(1, user.getTeamId());
			pStmt.setString(2, user.getName());
			if (pStmt.executeUpdate() > 0) {
				ret = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null) {
					pStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		List<User> userList = dao.selectAll();
		for(User user : userList) {
			System.out.println(user.getName() + " " + user.getTeamName());
		}

		User user = dao.selectByName("高橋");
		System.out.println(user.getName() + " " + user.getTeamId());
	}
}
