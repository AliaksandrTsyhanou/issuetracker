package by.epam.lab.issuetracker.service.dto;

import org.hibernate.validator.constraints.NotEmpty;

import by.epam.lab.issuetracker.interfaces.IManual;

public class ManualDto implements IManual{

	private int id;
	@NotEmpty
	private String name;
	
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
