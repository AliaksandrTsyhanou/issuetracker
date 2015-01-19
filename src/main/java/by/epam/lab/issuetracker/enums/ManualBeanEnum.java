package by.epam.lab.issuetracker.enums;

import by.epam.lab.issuetracker.entity.Priority;
import by.epam.lab.issuetracker.entity.Resolution;
import by.epam.lab.issuetracker.entity.Status;
import by.epam.lab.issuetracker.entity.Type;

public enum ManualBeanEnum {
	PRIORITY(Priority.class),
	RESOLUTION(Resolution.class),
	TYPE(Type.class),
	STATUS(Status.class);
	
	private Class<?> clazz;

	private ManualBeanEnum(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	
	
	
	
}
