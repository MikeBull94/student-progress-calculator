package uk.ac.dmu.studentprogress.view.menu.item;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The 'Save Student Data' menu item that appears under the 'Help' menu.
 */
public final class SaveStudentMenuItem extends JMenuItem {
	/**
	 * Creates a new {@link SaveStudentMenuItem}.
	 */
	public SaveStudentMenuItem() {
		super("Save Student Data");
		setToolTipText("Save the student profile data to a file");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
	}
}
