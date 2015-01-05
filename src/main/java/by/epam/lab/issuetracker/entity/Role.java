package by.epam.lab.issuetracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="role")
public class Role {
	private int id;
	private String name;
	private String value;
	
	public Role() {
		super();
	}

	public Role(int id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Id
	@Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
	
}
