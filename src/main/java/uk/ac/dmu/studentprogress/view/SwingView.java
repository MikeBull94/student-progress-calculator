package uk.ac.dmu.studentprogress.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.dmu.studentprogress.view.menu.MenuBar;
import uk.ac.dmu.studentprogress.view.tab.CreateProfileTab;
import uk.ac.dmu.studentprogress.view.tab.InputMarksTab;
import uk.ac.dmu.studentprogress.view.tab.OverviewResultsTab;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Represents the view in the 'model-view-controller' pattern. The view is a
 * visual representation of the program in the form of a graphical user
 * interface. It is manipulated by the controller and uses data from the  model.
 * It primarily generates the user interface and does not deal with the logic or
 * functions of the program itself but instead invokes such behaviour from the
 * controller.
 */
public final class SwingView implements View {
	/**
	 * The logger for this class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SwingView.class);

	/**
	 * The main frame.
	 */
	private final JFrame mainFrame = new JFrame("Student Progress Calculator");

	/**
	 * The content panel.
	 */
	private final JPanel contentPanel = new JPanel();

	/**
	 * The menu bar.
	 */
	private final MenuBar menuBar = new MenuBar();

	/**
	 * The tabbed pane.
	 */
	private final JTabbedPane tabbedPane = new JTabbedPane();

	/**
	 * The 'create profile' tab.
	 */
	private final CreateProfileTab createProfileTab = new CreateProfileTab();

	/**
	 * The 'input marks' tab.
	 */
	private final InputMarksTab inputMarksTab = new InputMarksTab();

	/**
	 * The 'overview results' tab.
	 */
	private final OverviewResultsTab overviewResultsTab = new OverviewResultsTab();

	/**
	 * Creates a new {@link SwingView}.
	 */
	public SwingView() {
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.addWindowListener(new ClosingWindowAdapter(menuBar.getExitMenuItem()));

		contentPanel.setLayout(new BorderLayout());
		tabbedPane.addTab(createProfileTab.getTitle(), null, createProfileTab, createProfileTab.getTooltip());
		tabbedPane.addTab(inputMarksTab.getTitle(), null, inputMarksTab, inputMarksTab.getTooltip());
		tabbedPane.addTab(overviewResultsTab.getTitle(), null, overviewResultsTab, overviewResultsTab.getTooltip());
		contentPanel.add(BorderLayout.CENTER, tabbedPane);

		mainFrame.setContentPane(contentPanel);
		mainFrame.pack();

		mainFrame.setMinimumSize(mainFrame.getPreferredSize());
		mainFrame.setLocationRelativeTo(null); // centers the application in the middle of the screen
		mainFrame.setVisible(true);
	}

	/**
	 * Displays an error in the view using a pop up box with it's stack trace and prints it's stack trace.
	 * @param t A throwable error.
	 */
	@Override
	public void displayError(Throwable t) {
		StringBuilder message = new StringBuilder(/*"An exception occurred: "*/);

		try (StringWriter writer = new StringWriter(); PrintWriter printWriter = new PrintWriter(writer)) {
			t.printStackTrace(printWriter);
			message.append(writer.toString());
		} catch (IOException ex) {
			/* cannot occur */
		}

		logger.error("An exception occurred:", t);
		displayError(message.toString(), "An exception occurred: ");
	}

	/**
	 * Displays an error in the view using a pop up box and prints it's stack trace.
	 * @param t A throwable error.
	 * @param message The error message.
	 */
	@Override
	public void displayError(Throwable t, String message) {
		logger.error("An exception occurred:", t);
		displayError(message);
	}

	/**
	 * Displays an error in the view using a pop up box.
	 * @param message The error message.
	 */
	@Override
	public void displayError(String message) {
		displayError(message, "Error");
	}

	/**
	 * Displays an error in the view using a pop up box.
	 * @param message The error message.
	 * @param title The title message.
	 */
	@Override
	public void displayError(String message, String title) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Displays a piece of information in the view using a pop up box.
	 * @param message The information message.
	 * @param title The title message.
	 */
	@Override
	public void displayInformationDialog(String message, String title) {
		JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Displays an option dialog using a pop up box.
	 * @param message The message.
	 * @param title The title.
	 * @param options The options.
	 * @param initialOption The initially selected option.
	 * @return The option chosen.
	 */
	@Override
	public int displayOptionDialog(String message, String title, Object[] options, Object initialOption) {
		return JOptionPane.showOptionDialog(mainFrame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, initialOption);
	}

	/**
	 * Gets the content panel.
	 * @return The content panel.
	 */
	public JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Gets the tabbed pane.
	 * @return The tabbed pane.
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * Gets the menu bar.
	 * @return The menu bar.
	 */
	public MenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * Gets the 'create profile' tab.
	 * @return The 'create profile' tab.
	 */
	public CreateProfileTab getCreateProfileTab() {
		return createProfileTab;
	}

	/**
	 * Gets the 'input marks' tab.
	 * @return The 'input marks' tab.
	 */
	public InputMarksTab getInputMarksTab() {
		return inputMarksTab;
	}

	/**
	 * Gets the 'overview results' tab.
	 * @return The 'overview results' tab.
	 */
	public OverviewResultsTab getOverviewResultsTab() {
		return overviewResultsTab;
	}
}
