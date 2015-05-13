package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'about' menu item.
 */
public final class AboutDialogListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(AboutDialogListener.class);

	/**
	 * The view to display the about dialog in.
	 */
	private final View view;

	/**
	 * Creates a new {@link AboutDialogListener}.
	 * @param view The view to display the about dialog in.
	 */
	public AboutDialogListener(View view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		view.displayInformationDialog("Student Progress Calculator" + "\n" + "Written by Michael Bull", "About");
		logger.info("Opened about dialog.");
	}
}
