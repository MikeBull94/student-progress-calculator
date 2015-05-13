package uk.ac.dmu.studentprogress.controller.io;

import java.io.IOException;

/**
 * Represents the structure of a codec which can both encode and decode a data structure.
 * @param <E> The data structure to read/write.
 */
public interface Codec<E> {
	/**
	 * Reads a file and decodes it into a specified data structure.
	 * @param path The file path.
	 * @return The data structure.
	 * @throws IOException If an I/O error occurs.
	 */
	E decode(String path) throws IOException;

	/**
	 * Encodes a data structure into a data stream.
	 * @param e The data structure.
	 * @throws IOException If an I/O error occurs.
	 */
	void encode(E e, String path) throws IOException;
}
