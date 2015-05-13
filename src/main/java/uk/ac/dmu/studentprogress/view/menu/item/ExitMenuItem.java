package uk.ac.dmu.studentprogress.view.menu.item;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * The 'Exit' menu item that appears under the 'Help' menu.
 */
public final class ExitMenuItem extends JMenuItem {
	/**
	 * Creates a new {@link ExitMenuItem}.
	 */
	public ExitMenuItem() {
		super("Exit");
		setToolTipText("Exit the program");
		setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
	}
}
