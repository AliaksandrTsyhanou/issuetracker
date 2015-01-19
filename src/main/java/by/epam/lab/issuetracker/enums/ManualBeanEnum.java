package by.epam.lab.issuetracker.enums;

import by.epam.lab.issuetracker.entity.Priority;
import by.epam.lab.issuetracker.entity.Resolution;

public enum ManualBeanEnum {
	PRIORITY(Priority.class),
	RESOLUTION(Resolution.class);
	
	private Class<?> clazz;

	private ManualBeanEnum(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}
	
	
	
	
}
