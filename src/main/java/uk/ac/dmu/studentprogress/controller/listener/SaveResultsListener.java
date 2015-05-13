package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.io.CodecRegistry;
import uk.ac.dmu.studentprogress.view.SwingView;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The {@link ActionListener} for the 'save results' button.
 */
public final class SaveResultsListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SaveResultsListener.class);

	/**
	 * The view to display any errors/information in.
	 */
	private final SwingView view;

	/**
	 * The file chooser.
	 */
	private final JFileChooser fileChooser;

	/**
	 * The overview text area.
	 */
	private final JTextArea overviewTextArea;

	/**
	 * Creates a new {@link SaveResultsListener}.
	 * @param view The view to display any errors/information in.
	 * @param fileChooser The file chooser.
	 * @param overviewTextArea The overview text area.
	 */
	public SaveResultsListener(SwingView view, JFileChooser fileChooser, JTextArea overviewTextArea) {
		this.view = view;
		this.fileChooser = fileChooser;
		this.overviewTextArea = overviewTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		logger.info("Saving results to text file...");

		if (overviewTextArea.getText().length() < 1) {
			view.displayError("There are no results to save.", "Save Error");
			logger.info("No results found - aborting.");
			return;
		}

		int returnVal = fileChooser.showSaveDialog(view.getContentPanel());
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			logger.info("Save cancelled.");
			return;
		}

		String path = fileChooser.getSelectedFile().getPath();
		if (!path.endsWith(".txt")) {
			path += ".txt";
		}

		logger.info("Saving results to file \"" + path + "\"...");

		try {
			CodecRegistry.get(JTextArea.class).encode(overviewTextArea, path);
		} catch (IOException e) {
			view.displayError(e);
			return;
		}

		logger.info("Successfully saved results to file \"" + path + "\"");
		view.displayInformationDialog("Successfully saved results to text file.", "Saved");
	}
}
