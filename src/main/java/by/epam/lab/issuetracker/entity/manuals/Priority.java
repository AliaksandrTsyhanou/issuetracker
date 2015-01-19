package by.epam.lab.issuetracker.entity.manuals;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import by.epam.lab.issuetracker.interfaces.IManual;

@Entity
@Table(name="priority")
public class Priority implements IManual{

	private int id;
	private String name;
	
	public Priority() {
		super();
	}

	public Priority(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Column(name="name")
	@NotEmpty(message = "{priority.label}" + "{spase}" + "{notempty}")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Resolution [id=" + id + ", name=" + name + "]";
	}	
}
