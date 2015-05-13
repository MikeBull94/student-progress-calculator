package uk.ac.dmu.studentprogress.model;

import com.google.common.collect.ImmutableList;

/**
 * Represents a course that a student may be enrolled on.
 */
public enum Course {
	COMPUTER_SCIENCE(
		"Computer Science",
		Module.CTEC2121,
		Module.CTEC2602,
		Module.CTEC2701,
		Module.CTEC2901
	),

	SOFTWARE_ENGINEERING(
		"Software Engineering",
		Module.CTEC2121,
		Module.CTEC2602,
		Module.CTEC2701,
		Module.CTEC2901
	),

	COMPUTER_SECURITY(
		"Computer Security",
		Module.CTEC2121,
		Module.CTEC2122,
		Module.CTEC2701,
		Module.CTEC2901
	),

	FORENSIC_COMPUTING(
		"Forensic Computing",
		Module.CTEC2121,
		Module.CTEC2122,
		Module.CTEC2701,
		Module.LAWG2003
	);

	/**
	 * The course's name.
	 */
	private final String name;

	/**
	 * The {@link Module}s on the course.
	 */
	private final ImmutableList<Module> modules;

	/**
	 * Creates a new {@link Course}.
	 * @param name The course's name.
	 * @param modules The {@link Module}s on the course.
	 */
	Course(String name, Module... modules) {
		this.name = name;
		this.modules = ImmutableList.copyOf(modules);
	}

	/**
	 * Gets the course's name.
	 * @return The course's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the {@link Module}s on the course.
	 * @return The {@link Module}s on the course.
	 */
	public ImmutableList<Module> getModules() {
		return modules;
	}
}
