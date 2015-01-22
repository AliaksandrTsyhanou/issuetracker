package by.epam.lab.issuetracker.service.dto;

import by.epam.lab.issuetracker.entity.Build;
import by.epam.lab.issuetracker.entity.Project;
import by.epam.lab.issuetracker.entity.User;
import by.epam.lab.issuetracker.entity.manuals.Priority;
import by.epam.lab.issuetracker.entity.manuals.Resolution;
import by.epam.lab.issuetracker.entity.manuals.Status;
import by.epam.lab.issuetracker.entity.manuals.Type;

public class IssueDto {

	private long id;
	private String summary;
	private String description;
	private Status status;
	private Resolution resolution;
	private Type type;
	private Priority priority;
	private Project project;
	private Build build;
	private User assignee;
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}	
}
