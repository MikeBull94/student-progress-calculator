package uk.ac.dmu.studentprogress.view.menu.item;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The 'Load Student Data' menu item that appears under the 'Help' menu.
 */
public final class LoadStudentMenuItem extends JMenuItem {
	/**
	 * Creates a new {@link LoadStudentMenuItem}.
	 */
	public LoadStudentMenuItem() {
		super("Load Student Data");
		setToolTipText("Load the student profile data from a file");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
	}
}
