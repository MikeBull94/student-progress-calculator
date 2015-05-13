package uk.ac.dmu.studentprogress.controller.io;


import com.google.common.collect.ImmutableMap;
import uk.ac.dmu.studentprogress.model.StudentProfile;

import javax.swing.JTextArea;

/**
 * Represents a registry of {@link Codec}s that can be used to encode/decode
 * various data structures.
 */
public final class CodecRegistry {
	/**
	 * The map of class types to codecs.
	 */
	private static final ImmutableMap<Class<?>, Codec<?>> codecs = ImmutableMap.of(
		StudentProfile.class, new StudentProfileCodec(),
		JTextArea.class, new OverviewResultsCodec()
	);

	/**
	 * Gets a type of codec.
	 * @param type The type of class the codec encodes and decodes.
	 * @param <T> The type of class the codec encodes and decodes.
	 * @return The codec.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Codec<T> get(Class<T> type) {
		return (Codec<T>) codecs.get(type);
	}
}
