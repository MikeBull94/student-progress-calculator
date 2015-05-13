package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.Controller;
import uk.ac.dmu.studentprogress.controller.io.CodecRegistry;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The {@link ActionListener} for the 'load student data' menu item.
 */
public final class LoadStudentDataListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoadStudentDataListener.class);

	/**
	 * The model we are loading into.
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
	 * The file chooser.
	 */
	private final JFileChooser fileChooser;

	/**
	 * Creates a new {@link LoadStudentDataListener}.
	 * @param model The model we are loading into.
	 * @param view The view to display any errors/information in.
	 * @param controller The controller to update the tabs.
	 * @param fileChooser The file chooser.
	 */
	public LoadStudentDataListener(Model model, SwingView view, Controller controller, JFileChooser fileChooser) {
		this.model = model;
		this.view = view;
		this.controller = controller;
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int returnVal = fileChooser.showOpenDialog(view.getContentPanel());
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			logger.info("Load cancelled.");
			return;
		}

		String path = fileChooser.getSelectedFile().getPath();

		logger.info("Loading student profile \"" + path + "\"...");

		try {
			StudentProfile profile = CodecRegistry.get(StudentProfile.class).decode(path);
			model.setProfile(profile);

			StudentProfile copy = new StudentProfile(profile);
			model.setLastLoadedProfile(copy);
		} catch (IOException e) {
			view.displayError(e);
			return;
		}

		controller.updateTabs(view, model.getProfile());
		controller.selectTab(view, view.getOverviewResultsTab());
		logger.info("Successfully loaded student profile: " + model.getProfile());
		view.displayInformationDialog("Successfully loaded student profile data.", "Loaded");
	}
}
