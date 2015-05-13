package uk.ac.dmu.studentprogress.controller.io;

import com.google.common.base.Preconditions;
import uk.ac.dmu.studentprogress.controller.io.Codec;

import javax.swing.JTextArea;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represents a {@link Codec} that can encode but not decode {@link JTextArea} components.
 */
public final class OverviewResultsCodec implements Codec<JTextArea> {
	@Override
	public JTextArea decode(String path) throws IOException {
		throw new UnsupportedOperationException("This codec does not support decoding.");
	}

	@Override
	public void encode(JTextArea textArea, String path) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8")))) {
			bw.write(textArea.getText());
		}
	}
}
