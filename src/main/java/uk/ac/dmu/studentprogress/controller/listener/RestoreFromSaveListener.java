package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.Controller;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@link ActionListener} for the 'restore from save' button.
 */
public final class RestoreFromSaveListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(RestoreFromSaveListener.class);

	/**
	 * The model to restore from.
	 */
	private final Model model;

	/**
	 * The view to display any errors/information in.
	 */
	private final SwingView view;

	/**
	 * The controller to update the tabs.
	 */
	private final Controller controller;

	/**
	 * Creates a new {@link RestoreFromSaveListener}.
	 * @param model The model to restore from.
	 * @param view The view to display any errors/information in.
	 * @param controller The controller to update the tabs.
	 */
	public RestoreFromSaveListener(Model model, SwingView view, Controller controller) {
		this.model = model;
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!model.unsavedChanges()) {
			view.displayError("There are no changes to restore.");
			return;
		}

		model.setProfile(new StudentProfile(model.getLastLoadedProfile()));
		controller.updateTabs(view, model.getProfile());
		logger.info("Restored from previous save.");
		view.displayInformationDialog("Successfully restored changes from previous save.", "Changes Restored");
	}
}
