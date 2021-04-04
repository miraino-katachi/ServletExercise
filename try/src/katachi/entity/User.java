package katachi.entity;

public class User {

	private int id;
	private int teamId;
	private String teamName;
	private String name;

	public User(String name, String teamName) {
		this.name = name;
		this.teamName = teamName;
	}

	public User(int teamId, String name) {
		this.teamId = teamId;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getTeamId() {
		return teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getName() {
		return name;
	}
}
