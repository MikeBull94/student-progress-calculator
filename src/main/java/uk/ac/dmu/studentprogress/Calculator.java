package uk.ac.dmu.studentprogress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.Controller;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.view.SwingView;

/**
 * The Student Progress Calculator application that allows a user to enter a student's profile information
 * and manipulate the information in order to provide an easy to read overview that can be saved and
 * loaded at will. It is designed using the 'model-view-controller' pattern.
 */
public final class Calculator {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

	/**
	 * The initial entry point for this program.
	 * @param args The arguments passed to the program.
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("apple.awt.fileDialogForDirectories", "true");
			new Calculator();
		} catch (Throwable t) {
			logger.error("Failed to start student progress calculator:", t);
		}
	}

	/**
	 * Creates a new {@link Calculator}.
	 */
	public Calculator() {
		logger.info("Starting Student Progress Calculator...");

		Model model = new Model();
		SwingView view = new SwingView();
		new Controller(model, view);
	}
}
