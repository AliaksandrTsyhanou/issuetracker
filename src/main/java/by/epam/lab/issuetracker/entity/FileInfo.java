package by.epam.lab.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class FileInfo {
	
	private long id;
	private String name;
	private String description;
	private long size;
	private long idissue;
	
	public long getIdissue() {
		return idissue;
	}
	public void setIdissue(long idissue) {
		this.idissue = idissue;
	}
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
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}	

}
