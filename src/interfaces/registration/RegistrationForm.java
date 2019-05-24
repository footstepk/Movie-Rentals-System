package interfaces.registration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;

public interface RegistrationForm {

	/** Declare method header for implementation. */

	/*
	 * Create the registration page label.
	 * @return the label describing the registration page
	 */
	public JLabel setRegistrationPageLabel();

	/*
	 * Create the panel to hold the components.
	 * @return the panel of the components.
	 */
	public JComponent setRegistrationContentPane(); 

	/*
	 * Create the registration button
	 * @return the submission button of the registration.
	 */
	public JButton setRegistrationButton();
	
	/*
	 * Create go back button
	 * @return go back button the go back to the home page
	 */
		public JButton setGoBackButton();

		/*
		 * Create and setup the GUI. For thread safety, the method
		 * should be invoke in event-dispatching thread.
		 */
		public void createRegistrationUI();
}
