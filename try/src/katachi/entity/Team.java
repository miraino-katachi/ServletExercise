package katachi.entity;

public class Team {

	private int teamId;
	private String name;

	public Team(int teamId, String name) {
		this.teamId = teamId;
		this.name = name;
	}

	public int getTeamId() {
		return teamId;
	}

	public String getName() {
		return name;
	}
}
