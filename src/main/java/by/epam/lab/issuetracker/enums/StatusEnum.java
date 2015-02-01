package by.epam.lab.issuetracker.enums;

public enum StatusEnum {
	NEW(1),
	ASSIGNED(2),
	IN_PROGRESS(3),
	RESOLVED(4),
	CLOSED(5),
	REOPENED(6);
	
	private final int id;

	private StatusEnum(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
