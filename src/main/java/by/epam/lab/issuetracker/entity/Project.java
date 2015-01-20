package by.epam.lab.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	
	private long id;
	private String name;
	private String description;
//	private long idbuild;
	private Build build;
	private User manager;
//	private long idmanager;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idbuild", unique = true, nullable = false, updatable = true)
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmanager", unique = true, nullable = false, updatable = true)
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
}
