package uk.ac.dmu.studentprogress.view;

public interface View {
	/**
	 * Displays an error in the view using a pop up box with it's stack trace and prints it's stack trace.
	 * @param t A throwable error.
	 */
	void displayError(Throwable t);

	/**
	 * Displays an error in the view using a pop up box and prints it's stack trace.
	 * @param t A throwable error.
	 * @param message The error message.
	 */
	void displayError(Throwable t, String message);

	/**
	 * Displays an error in the view using a pop up box.
	 * @param message The error message.
	 */
	void displayError(String message);

	/**
	 * Displays an error in the view using a pop up box.
	 * @param message The error message.
	 * @param title The title message.
	 */
	void displayError(String message, String title);

	/**
	 * Displays a piece of information in the view using a pop up box.
	 * @param message The information message.
	 * @param title The title message.
	 */
	void displayInformationDialog(String message, String title);

	/**
	 * Displays an option dialog using a pop up box.
	 * @param message The message.
	 * @param title The title.
	 * @param options The options.
	 * @param initialOption The initially selected option.
	 * @return The option chosen.
	 */
	int displayOptionDialog(String message, String title, Object[] options, Object initialOption);
}
