package by.epam.lab.issuetracker.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import by.epam.lab.issuetracker.entity.Build;

@XmlRootElement(name = "builds")  
public class BuildsDto {
	
	private List<Build> builds;

	public BuildsDto() {
		super();
	}

	public BuildsDto(List<Build> builds) {
		this.builds = builds;
	}

	@javax.xml.bind.annotation.XmlElement(name = "build")  
	public List<Build> getBuilds() {
		return builds;
	}

	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}
} 