package uk.ac.dmu.studentprogress.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Optional;

/**
 * Represents the course work and exam marks for the various modules a student
 * is enrolled on.
 */
public final class ModuleMarks {
	/**
	 * An {@link ImmutableMap} of {@link Module}s to an {@link ImmutableMap} of {@link MarkType} and mark.
	 */
	private final ImmutableMap<Module, ImmutableMap<MarkType, Integer>> marks;

	/**
	 * Creates a new {@link ModuleMarks}.
	 * @param marks The marks the student has.
	 */
	public ModuleMarks(ImmutableMap<Module, ImmutableMap<MarkType, Integer>> marks) {
		this.marks = marks;
	}

	/**
	 * Gets the marks this student has.
	 * @return The marks this student has.
	 */
	public ImmutableMap<Module, ImmutableMap<MarkType, Integer>> getMarks() {
		return marks;
	}

	/**
	 * Gets an {@link ImmutableMap} of {@link Module}s to marks.
	 * @param markType The type of mark.
	 * @return An {@link ImmutableMap} of {@link Module}s to marks.
	 */
	private ImmutableMap<Module, Integer> getMarks(MarkType markType) {
		ImmutableMap.Builder<Module, Integer> builder = ImmutableMap.builder();

		for (Module module : marks.keySet()) {
			ImmutableMap<MarkType, Integer> mark = marks.get(module);
			if (mark.containsKey(markType)) {
				builder.put(module, mark.get(markType));
			}
		}

		return builder.build();
	}

	/**
	 * Gets an {@link ImmutableMap} of {@link Module}s to exam marks.
	 * @return An {@link ImmutableMap} of {@link Module}s to exam marks.
	 */
	public ImmutableMap<Module, Integer> getExamMarks() {
		return getMarks(MarkType.EXAM);
	}

	/**
	 * Gets an {@link ImmutableMap} of {@link Module}s to course work marks.
	 * @return An {@link ImmutableMap} of {@link Module}s to course work marks.
	 */
	public ImmutableMap<Module, Integer> getCourseWorkMarks() {
		return getMarks(MarkType.COURSEWORK);
	}

	/**
	 * Gets a mark based upon the module and type of mark.
	 * @param module The {@link Module}.
	 * @param markType The type of mark.
	 * @return The mark, or {@link Optional#empty()} if there is no mark associated with the {@link Module}.
	 */
	public Optional<Integer> getMark(Module module, MarkType markType) {
		Map<Module, Integer> marks = getMarks(markType);
		return Optional.ofNullable(marks.get(module));
	}

	/**
	 * Gets the course work mark for a {@link Module}.
	 * @param module The module.
	 * @return The course work mark, or {@link Optional#empty()} if there is no mark associated with the {@link Module}.
	 */
	public Optional<Integer> getCourseWorkMark(Module module) {
		return getMark(module, MarkType.COURSEWORK);
	}

	/**
	 * Gets the exam mark for a {@link Module}.
	 * @param module The module.
	 * @return The exam mark, or {@link Optional#empty()} if there is no mark associated with the {@link Module}.
	 */
	public Optional<Integer> getExamMark(Module module) {
		return getMark(module, MarkType.EXAM);
	}
	/**
	 * Gets the exam mark for a {@link Module}.
	 * @param module The module.
	 * @return The exam mark, or {@link Optional#empty()} if there is no mark associated with the {@link Module}.
	 */
	public int getOverallMark(Module module) {
		return (int) Math.round((getExamMark(module).orElse(0) + getCourseWorkMark(module).orElse(0)) / 2.0);
	}

	/**
	 * Checks if a {@link Module} requires a resit based on the overall mark achieved within it.
	 * @param module The {@link Module}
	 * @return {@code true} if so, {@code false} otherwise.
	 */
	private boolean requireResit(Module module) {
		return getOverallMark(module) < 40;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("marks", marks)
			.toString();
	}
}
