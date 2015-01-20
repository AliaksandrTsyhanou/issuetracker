package by.epam.lab.issuetracker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import by.epam.lab.issuetracker.entity.manuals.Priority;
import by.epam.lab.issuetracker.entity.manuals.Status;
import by.epam.lab.issuetracker.entity.manuals.Type;

@Entity
@Table(name="issue")
public class Issue {

	private long id;
	private String summary;
	private String description;
	private Status status;
	private Type type;
	private Priority priority;
	private Project project;
	private Build build;
	private User user;
	
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idstatus", unique = true, nullable = false)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idtype", unique = true, nullable = false)
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idpriority", unique = true, nullable = false)
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproject", unique = true, nullable = false)
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idbuildfound", unique = true, nullable = false)
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idassignee", unique = true)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
