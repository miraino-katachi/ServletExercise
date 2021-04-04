package katachi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import katachi.entity.Team;

public class TeamDao {

	private final String SELECT_ALL = "SELECT team_id, name FROM team";

	public List<Team> selectAll() {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;

		List<Team> teamList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb1?serverTimezone=JST&sslMode=DISABLED",
					"root", "");

			pStmt = conn.prepareStatement(SELECT_ALL);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				int teamId = Integer.parseInt(rs.getString(1));
				String name = rs.getString(2);
				Team team = new Team(teamId, name);
				teamList.add(team);
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

		return teamList;
	}

	public static void main(String[] args) {
		TeamDao dao = new TeamDao();
		List<Team> teamList = dao.selectAll();
		for(Team team : teamList) {
			System.out.println(team.getTeamId() + " " + team.getName());
		}

	}
}
