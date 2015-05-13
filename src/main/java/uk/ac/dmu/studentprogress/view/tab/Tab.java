package uk.ac.dmu.studentprogress.view.tab;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 * Represents a tab that appears in the {@link JTabbedPane} on the main frame.
 */
public abstract class Tab extends JPanel {
	/**
	 * The grid bag layout constraints.
	 */
	protected final GridBagConstraints constraints = new GridBagConstraints();

	/**
	 * The title of this tab.
	 */
	private final String title;

	/**
	 * The tooltip text of this tab.
	 */
	private final String tooltip;

	/**
	 * Creates a new {@link Tab}.
	 * @param title The title of this tab.
	 * @param tooltip The tooltip text of this tab.
	 */
	protected Tab(String title, String tooltip) {
		this.title = title;
		this.tooltip = tooltip;

		setLayout(new GridBagLayout());
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridx = 0;
		constraints.gridy = 0;
	}

	/**
	 * Gets the grid bag layout constraints.
	 * @return The grid bag layout constraints.
	 */
	public GridBagConstraints getConstraints() {
		return constraints;
	}

	/**
	 * Gets the tab's title.
	 * @return The tab's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the tab's tooltip text.
	 * @return The tab's tooltip text.
	 */
	public String getTooltip() {
		return tooltip;
	}
}
