package by.epam.lab.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import by.epam.lab.issuetracker.interfaces.IManual;

@Entity
@Table(name="build")
@XmlRootElement(name = "build")
public class Build implements IManual{

	private int id;
	private long idproject;
	private String name;
	
	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public long getIdproject() {
		return idproject;
	}
	@XmlElement
	public void setIdproject(long idproject) {
		this.idproject = idproject;
	}
	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}		
}
