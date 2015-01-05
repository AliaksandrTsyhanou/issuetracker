package enums;

public enum RoleEnum {
	
	USER(1, "USER", "ROLE_USER"),
	ADMINISTRATOR(2, "ADMINISTRATOR", "ROLE_ADMIN");
	

	private final int id;
	private final String value;
	private final String name;
	
	private RoleEnum(int id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public int getId() {
		return id;
	}
	
	

}
