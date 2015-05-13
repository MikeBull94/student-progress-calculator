package uk.ac.dmu.studentprogress.model;

/**
 * Represents a module that may be taught on a {@link Course}.
 */
public enum Module {
	CTEC2121("CTEC2121", "Organisations, Project Management and Research"),
	CTEC2122("CTEC2122", "Forensics and Security"),
	CTEC2602("CTEC2602", "OO Software Design and Development"),
	CTEC2701("CTEC2701", "Multi-tier Web Applications"),
	CTEC2901("CTEC2901", "Data Structures and Algorithms"),
	LAWG2003("LAWG2003", "Issues in Criminal Justice");

	public static Module fromCode(String code) {
		for (Module module : values()) {
			if (module.code.equals(code)) {
				return module;
			}
		}
		throw new IllegalArgumentException("No module found for code \"" + code + "\".");
	}

	/**
	 * The module's code.
	 */
	private final String code;

	/**
	 * The module's name.
	 */
	private final String name;

	/**
	 * Creates a new {@link Module}.
	 * @param code The module's code.
	 * @param name The module's name.
	 */
	Module(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * Gets the module's code.
	 * @return The module's code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the module's name.
	 * @return The module's name.
	 */
	public String getName() {
		return name;
	}
}
