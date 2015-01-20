package by.epam.lab.issuetracker.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import by.epam.lab.issuetracker.entity.manuals.Priority;
import by.epam.lab.issuetracker.entity.manuals.Resolution;
import by.epam.lab.issuetracker.entity.manuals.Status;
import by.epam.lab.issuetracker.entity.manuals.Type;

@Entity
@Table(name="issue")
public class Issue {

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
	private Date createdate;
	private User creator;
	private Date modifydate;
	private User modifier;
		
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
	@JoinColumn(name = "idresolution", unique = true, nullable = false)
	public Resolution getResolution() {
		return resolution;
	}
	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
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
	public User getAssignee() {
		return assignee;
	}
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	@OneToOne()
	@JoinColumn(name = "idcreator", unique = true, nullable = false)
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getModifydate() {
		return modifydate;
	}
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	@OneToOne()
	@JoinColumn(name = "idmodifier", unique = true, nullable = false)
	public User getModifier() {
		return modifier;
	}
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}	
}
