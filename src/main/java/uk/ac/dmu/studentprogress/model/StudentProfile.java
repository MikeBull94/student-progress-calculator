package uk.ac.dmu.studentprogress.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a student's profile who is enrolled on a specific {@link Course}.
 */
public final class StudentProfile {
	/**
	 * The student's full name.
	 */
	private final String fullName;

	/**
	 * The student's p number.
	 */
	private final String pNumber;

	/**
	 * The {@link Course} the student is enrolled on.
	 */
	private final Course course;

	/**
	 * The marks the student has achieved within each {@link Module}.
	 */
	private ModuleMarks moduleMarks;

	/**
	 * Creates a new {@link StudentProfile} with no achieved {@link Module} marks.
	 * @param fullName The student's full name
	 * @param pNumber The student's p number.
	 * @param course The {@link Course} the student is enrolled on.
	 * @param moduleMarks The {@link ModuleMarks} the student has achieved.
	 */
	public StudentProfile(String fullName, String pNumber, Course course, ModuleMarks moduleMarks) {
		this.fullName = fullName;
		this.pNumber = pNumber;
		this.course = course;
		this.moduleMarks = moduleMarks;
	}

	/**
	 * Creates a new {@link StudentProfile} with no achieved {@link Module} marks.
	 * @param fullName The student's full name
	 * @param pNumber The student's p number.
	 * @param course The {@link Course} the student is enrolled on.
	 */
	public StudentProfile(String fullName, String pNumber, Course course) {
		this(fullName, pNumber, course, new ModuleMarks(ImmutableMap.of()));
	}

	/**
	 * Copy constructor.
	 * @param studentProfile The {@link StudentProfile} to copy from.
	 */
	public StudentProfile(StudentProfile studentProfile) {
		this.fullName = studentProfile.fullName;
		this.pNumber = studentProfile.pNumber;
		this.course = studentProfile.course;
		this.moduleMarks = studentProfile.moduleMarks;
	}

	/**
	 * Checks if a {@link Module} requires resitting.
	 * @param module The {@link Module}.
	 * @return {@code true} if so, {@code false} otherwise.
	 */
	public boolean moduleResitRequired(Module module) {
		return getOverallMark(module) < 40;
	}

	/**
	 * Calculates the amount of credits the student has passed with.
	 * @return The amount of credits the student has passed with.
	 */
	public int creditsPassed() {
		int credits = 0;
		for (Module module : course.getModules()) {
			if (!moduleResitRequired(module)) {
				credits += 30;
			}
		}

		return credits;
	}

	/**
	 * Calculates the studen'ts average mark achieved throughout the year.
	 * @return The studen'ts average mark achieved throughout the year.
	 */
	public int yearAverageMark() {
		int mark = 0;
		for (Module module : course.getModules()) {
			mark += getOverallMark(module);
		}

		return (int) Math.round(mark / 4.0);
	}

	/**
	 * Resets the student's achieved {@link ModuleMarks}.
	 */
	public void resetModuleMarks() {
		this.moduleMarks = new ModuleMarks(ImmutableMap.of());
	}

	/**
	 * Sets a mark for a specific {@link Module}.
	 * @param module The {@link Module}.
	 * @param markType The {@link MarkType}.
	 * @param mark The mark.
	 */
	public void setMark(Module module, MarkType markType, int mark) {
		Preconditions.checkState(course.getModules().contains(module), "Must be a valid module for the currently enrolled course to set a mark for it.");

		Map<Module, ImmutableMap<MarkType, Integer>> newMarks = new HashMap<>();
		newMarks.putAll(moduleMarks.getMarks());

		Map<MarkType, Integer> modifiedMarks = new HashMap<>();
		if (newMarks.containsKey(module)) {
			modifiedMarks.putAll(newMarks.get(module));
		}

		if (mark == 0) {
			modifiedMarks.remove(markType);
		} else {
			modifiedMarks.put(markType, mark);
		}

		newMarks.put(module, ImmutableMap.copyOf(modifiedMarks));

		if (newMarks.get(module).size() == 0) {
			newMarks.remove(module);
		}

		moduleMarks = new ModuleMarks(ImmutableMap.copyOf(newMarks));
	}

	/**
	 * Gets the student's full name.
	 * @return The student's full name.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Gets the student's p number.
	 * @return The student's p number.
	 */
	public String getPNumber() {
		return pNumber;
	}

	/**
	 * Gets the course the student is enrolled on.
	 * @return The course the student is enrolled on.
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Gets the {@link Module} marks the student has achieved.
	 * @return The {@link Module} marks the student has achieved.
	 */
	public ModuleMarks getModuleMarks() {
		return moduleMarks;
	}

	/**
	 * Gets an overall mark for a {@link Module}.
	 * @param module The {@link Module}
	 * @return The overall mark for a {@link Module}.
	 */
	public int getOverallMark(Module module) {
		return moduleMarks.getOverallMark(module);
	}

	/**
	 * Gets a specific {@link MarkType} for a {@link Module}.
	 * @param module The {@link Module}.
	 * @param markType The {@link MarkType}.
	 * @return The mark.
	 */
	public int getMark(Module module, MarkType markType) {
		return moduleMarks.getMark(module, markType).orElse(0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		StudentProfile that = (StudentProfile) o;

		if (!pNumber.equals(that.pNumber)) {
			return false;
		}
		if (!fullName.equals(that.fullName)) {
			return false;
		}
		return moduleMarks.equals(that.moduleMarks);
	}

	@Override
	public int hashCode() {
		int result = pNumber.hashCode();
		result = 31 * result + fullName.hashCode();
		result = 31 * result + moduleMarks.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("pNumber", pNumber)
			.add("fullName", fullName)
			.add("moduleMarks", moduleMarks.getMarks())
			.toString();
	}
}