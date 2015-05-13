package uk.ac.dmu.studentprogress.model;

/**
 * Represents the model in the 'model-view-controller' pattern. The model holds current data about the
 * student profile that we are editing.
 */
public final class Model {
	/**
	 * The current student profile.
	 */
	private StudentProfile profile;

	/**
	 * The last loaded student profile.
	 */
	private StudentProfile lastLoadedProfile;

	/**
	 * Gets the current student profile.
	 * @return The current student profile.
	 */
	public StudentProfile getProfile() {
		return profile;
	}

	/**
	 * Sets the current student profile.
	 * @param profile The student profile to set.
	 */
	public void setProfile(StudentProfile profile) {
		this.profile = profile;
	}

	/**
	 * Gets the last loaded student profile.
	 * @return The last loaded student profile.
	 */
	public StudentProfile getLastLoadedProfile() {
		return lastLoadedProfile;
	}

	/**
	 * Sets the last loaded student profile.
	 * @param lastLoadedProfile The student profile to set.
	 */
	public void setLastLoadedProfile(StudentProfile lastLoadedProfile) {
		this.lastLoadedProfile = lastLoadedProfile;
	}

	/**
	 * Checks if the last loaded profile has been edited since it was loaded.
	 * @return {@code true} if so, {@code false} otherwise.
	 */
	public boolean unsavedChanges() {
		return profile != null && lastLoadedProfile != null && !profile.equals(lastLoadedProfile);
	}
}
