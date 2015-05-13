package uk.ac.dmu.studentprogress.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.controller.io.CodecRegistry;
import uk.ac.dmu.studentprogress.model.Model;
import uk.ac.dmu.studentprogress.model.StudentProfile;
import uk.ac.dmu.studentprogress.view.SwingView;

import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The {@link ActionListener} for the 'save student data' menu item.
 */
public final class SaveStudentDataListener implements ActionListener {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SaveStudentDataListener.class);

	/**
	 * The model we are saving.
	 */
	private final Model model;

	/**
	 * The view to display any errors/information in.
	 */
	private final SwingView view;

	/**
	 * The file chooser.
	 */
	private final JFileChooser fileChooser;

	/**
	 * Creates a new {@link SaveStudentDataListener}.
	 * @param model The model we are saving.
	 * @param view The view to display any errors/information in.
	 * @param fileChooser The file chooser.
	 */
	public SaveStudentDataListener(Model model, SwingView view, JFileChooser fileChooser) {
		this.model = model;
		this.view = view;
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		logger.info("Saving student file...");
		StudentProfile profile = model.getProfile();

		if (profile == null) {
			logger.info("No profile found - aborting.");
			view.displayError("You must load an existing profile or create a new profile before saving.", "Save Error");
			return;
		}

		int returnVal = fileChooser.showSaveDialog(view.getContentPanel());
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			logger.info("Save cancelled.");
			return;
		}

		String path = fileChooser.getSelectedFile().getPath();
		if (!path.endsWith(".stud")) {
			path += ".stud";
		}

		logger.info("Saving student data to file \"" + path + "\"...");

		try {
			CodecRegistry.get(StudentProfile.class).encode(profile, path);
		} catch (IOException e) {
			view.displayError(e);
			return;
		}

		model.setLastLoadedProfile(new StudentProfile(profile));
		logger.info("Successfully saved student data to file \"" + path + "\"");
		view.displayInformationDialog("Successfully saved student profile data.", "Saved");
	}
}
