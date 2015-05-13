package uk.ac.dmu.studentprogress.view.menu.item;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The 'About' menu item that appears under the 'Help' menu.
 */
public final class AboutMenuItem extends JMenuItem {
	/**
	 * Creates a new {@link AboutMenuItem}.
	 */
	public AboutMenuItem() {
		super("About");
		setToolTipText("View details of this program");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
	}
}
