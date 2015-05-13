package uk.ac.dmu.studentprogress.view;

import uk.ac.dmu.studentprogress.view.menu.item.ExitMenuItem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents a {@link WindowAdapter} that simulates the effect of clicking the 'exit' menu item.
 */
public final class ClosingWindowAdapter extends WindowAdapter {
	/**
	 * The exit menu item.
	 */
	private final ExitMenuItem exitMenuItem;

	/**
	 * Creates a new {@link ClosingWindowAdapter}.
	 * @param exitMenuItem The exit menu item.
	 */
	public ClosingWindowAdapter(ExitMenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		exitMenuItem.doClick();
	}
}
