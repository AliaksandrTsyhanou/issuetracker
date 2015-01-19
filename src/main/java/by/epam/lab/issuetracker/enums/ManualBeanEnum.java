package by.epam.lab.issuetracker.enums;

import by.epam.lab.issuetracker.entity.Priority;
import by.epam.lab.issuetracker.entity.Resolution;
import by.epam.lab.issuetracker.entity.Status;
import by.epam.lab.issuetracker.entity.Type;

public enum ManualBeanEnum {
	PRIORITY(Priority.class, true),
	RESOLUTION(Resolution.class, true),
	TYPE(Type.class, true),
	STATUS(Status.class, false);
	
	private final Class<?> clazz;
	private final boolean isAllowAdditions;

	private ManualBeanEnum(Class<?> clazz, boolean isAllowAdditions) {
		this.clazz = clazz;
		this.isAllowAdditions = isAllowAdditions;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public boolean isAllowAdditions() {
		return isAllowAdditions;
	}	
}
