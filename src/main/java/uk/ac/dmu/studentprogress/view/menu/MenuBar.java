package uk.ac.dmu.studentprogress.view.menu;

import uk.ac.dmu.studentprogress.view.menu.item.AboutMenuItem;
import uk.ac.dmu.studentprogress.view.menu.item.ExitMenuItem;
import uk.ac.dmu.studentprogress.view.menu.item.LoadStudentMenuItem;
import uk.ac.dmu.studentprogress.view.menu.item.SaveStudentMenuItem;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.event.KeyEvent;

/**
 * The menu bar that is added to the view.
 */
public final class MenuBar extends JMenuBar {
	/**
	 * The load student menu item.
	 */
	private final LoadStudentMenuItem loadStudentMenuItem = new LoadStudentMenuItem();

	/**
	 * The save student menu item.
	 */
	private final SaveStudentMenuItem saveStudentMenuItem = new SaveStudentMenuItem();

	/**
	 * The exit menu item.
	 */
	private final ExitMenuItem exitMenuItem = new ExitMenuItem();

	/**
	 * The about dialog menu item.
	 */
	private final AboutMenuItem aboutMenuItem = new AboutMenuItem();

	/**
	 * Creates a new {@link MenuBar}.
	 */
	public MenuBar() {
		// file menu options
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		fileMenu.add(loadStudentMenuItem);
		fileMenu.add(saveStudentMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);

		add(fileMenu);

		// help menu options
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.add(aboutMenuItem);

		add(helpMenu);
	}

	/**
	 * Gets the load student menu item.
	 * @return The load student menu item.
	 */
	public LoadStudentMenuItem getLoadStudentMenuItem() {
		return loadStudentMenuItem;
	}

	/**
	 * Gets the save student menu item.
	 * @return The save student menu item.
	 */
	public SaveStudentMenuItem getSaveStudentMenuItem() {
		return saveStudentMenuItem;
	}

	/**
	 * Gets the exit menu item.
	 * @return The exit menu item.
	 */
	public ExitMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	 * Gets the about dialog menu item.
	 * @return The about dialog menu item.
	 */
	public AboutMenuItem getAboutMenuItem() {
		return aboutMenuItem;
	}
}
