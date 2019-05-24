package abstracts;

import utilities.Global;

/**
 * Genre is an abstract  class that represents a genre of a particular title.
 * @author Kok Heng
 *
 */

public abstract class Genre {

	/** Declare the global data field (Refer to utilities/global variables) */

	/** Construct an empty constructor for catering empty constructor. */
	public Genre() {
	}

	/*
	 * Construct a string representation of the genre type name.
	 * @param genre the genre type title name.
	 */
	public Genre(String genre) {
		Global.GENRE_NAME = genre;
	}

	/*
	 * Get the title name of the genre.
	 * @return a string representation of the genre type name.
	 */
	public String getGenreTypeName() {
		return Global.GENRE_NAME;
	}

	/** Declare abstract method header for whose class implementing its body. */

	/*
	 * Get the genre and its title name from
	 * input keyboard.
	 */
	public abstract void getTitleInputKeyboard();

	/*
	 * Set the genre type name info to standard screen
	 */
	public abstract void setGenreTypeName();

	/*
	 * Overriding the actual string to represent this genre type name.
	 * @return a string to represent this genre type name.
	 */
	public void showGenre() {
		System.out.printf("Genre type name: %-8s\n", Global.GENRE_NAME);
	}
}
