package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'exit' menu item.
 */
public final class ExitListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ExitListener.class);

	/**
	 * The model to check for changes in.
	 */
	private final Model model;

	/**
	 * The view to display a confirmation box in.
	 */
	private final View view;

	/**
	 * Creates a new {@link ExitListener}.
	 * @param model The model to check for changes in.
	 * @param view The view to display a confirmation box in.
	 */
	public ExitListener(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean canClose = !model.unsavedChanges();

		if (!canClose) {
			String message = "There are unsaved student profile changes, do you still wish to exit?";
			String title = "Unsaved Changes";
			String options[] = { "No", "Yes" };
			String initialChoice = "No";

			int choice = view.displayOptionDialog(message, title, options, initialChoice);

			if (choice == 1) {
				canClose = true;
			}
		}

		if (canClose) {
			logger.info("Exiting program...");
			System.exit(0);
		}
	}
}
