package edits.customer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import interfaces.registration.RegistrationForm;
import models.customer.CustomerQuery;

/**
 * RegisterNewCustomer is class that represents a registration of new customer entry form.
 * 
 * It implements the GUI component and display the form to the screen.
 * 
 * @author Kok Heng
 *
 */

public class RegisterNewCustomer implements RegistrationForm {

	/** Declare the GUI instance . */

	// The GUI container to hold the components.
	private JFrame frame;
	private JPanel panel;

	/** Declare the components. */
	private JButton registerBtn; // submit registration button.
	private JButton goBackBtn; // for go back to home page.
	private JLabel registrationLabel, firstNameLabel, lastNameLabel, phoneLabel, subscribeLabel, currentPointLabel, rewardPointLabel;
	private JTextField firstNameField, lastNameField, phoneField, subscribeField, currentPointField, rewardPointField;

	/** Construct the GUI component to create new registration form. */
	public RegisterNewCustomer() {
	}

	/*
	 * Create customer registration form label to the window frame.
	 * @return the label to represent the label text.
	 */
	@Override
	public JLabel setRegistrationPageLabel() {
		final String message = "Customer Registration Form\nEnter customer details with * is mandatory";
		// Setup the label.
		registrationLabel = new JLabel();
		registrationLabel.setText(message);
		registrationLabel.setOpaque(true);
		registrationLabel.setBackground(new Color(124, 165, 121));
		registrationLabel.setPreferredSize(new Dimension(180, 180));
		return registrationLabel;
	}

	/*
	 * Create the panel to hold the components.
	 * @return the panel of the components.
	 */
	@Override
	public JComponent setRegistrationContentPane() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2, 5, 5));

		// insert all the components into the panel.
		firstNameLabel = new JLabel("* Enter first name: ");
		lastNameLabel = new JLabel("* Enter last name: ");
		phoneLabel = new JLabel("* Enter phone number: ");
		subscribeLabel = new JLabel("* Enter subscription plan: ");
		currentPointLabel = new JLabel("* Enter current point: ");
		rewardPointLabel = new JLabel("* Enter reward point: ");

		firstNameField = new JTextField(80);
		firstNameField.setEditable(true);
		firstNameField.setOpaque(true);

		lastNameField = new JTextField(80);
		lastNameField.setEditable(true);
		lastNameField.setOpaque(true);

		phoneField = new JTextField(80);
		phoneField.setEditable(true);
		phoneField.setOpaque(true);

		subscribeField = new JTextField(80);
		subscribeField.setEditable(true);
		subscribeField.setOpaque(true);

		currentPointField = new JTextField(80);
		currentPointField.setEditable(true);
		currentPointField.setOpaque(true);

		rewardPointField = new JTextField(80);
		rewardPointField.setEditable(true);
		rewardPointField.setOpaque(true);

		firstNameLabel.setLabelFor(firstNameLabel);
		lastNameLabel.setLabelFor(lastNameLabel);
		phoneLabel.setLabelFor(phoneLabel);
		subscribeLabel.setLabelFor(subscribeLabel);
		currentPointLabel.setLabelFor(currentPointLabel);
		rewardPointLabel.setLabelFor(rewardPointLabel);

		panel.add(firstNameLabel);
		panel.add(firstNameField);
		panel.add(lastNameLabel);
		panel.add(lastNameField);
		panel.add(phoneLabel);
		panel.add(phoneField);
		panel.add(subscribeLabel);
		panel.add(subscribeField);
		panel.add(currentPointLabel);
		panel.add(currentPointField);
		panel.add(rewardPointLabel);
		panel.add(rewardPointField);
		panel.add(setRegistrationButton());
		panel.add(setGoBackButton());
		return panel;
	}

	/*
	 * Create the registration button
	 * @return the submission button of the registration.
	 */
	@Override
	public JButton setRegistrationButton() {
		registerBtn = new JButton("Register");
		registerBtn.setOpaque(true);
		registerBtn.setBackground(new Color(224, 224, 163));
		registerBtn.setPreferredSize(new Dimension(25, 25));

		// Allocate an instance of this inner class when click on fire.
		CustomerRegistrationButtonListener listener = new CustomerRegistrationButtonListener();
		registerBtn.addActionListener(listener);

		return registerBtn;
	}

	/*
	 * Create go back button
	 * @return done button the go back to the home page
	 */
	@Override
	public JButton setGoBackButton() {
		goBackBtn = new JButton("Go Back");
		goBackBtn.setOpaque(true);
		goBackBtn.setBackground(new Color(224, 224, 163));
		goBackBtn.setPreferredSize(new Dimension(25, 25));

		// Allocate an instance of this inner class when click on fire.
		CustomerRegistrationButtonListener listener = new CustomerRegistrationButtonListener();
		goBackBtn.addActionListener(listener);

		return goBackBtn;
	}

	/*
	 * Create and setup the GUI. For thread safety, the method
	 * should be invoke in event-dispatching thread.
	 */
	@Override
	public void createRegistrationUI() {
		// Setup the main window frame.
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// Show the label and buttons here.
		frame.add(setRegistrationPageLabel(), BorderLayout.PAGE_START);

		// Setup the panel components.
		frame.add(setRegistrationContentPane(), BorderLayout.CENTER);

		frame.setTitle("Movie Rental System -> Customer Registration Form");
		frame.setSize(350000, 300000);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * CustomerRegistrationButtonListener is an instance of the inner class of CustomerRegistration.
	 * It verifies the entries of customer registration details.
	 * It implements the ActionEvent. 
	 */
	private class CustomerRegistrationButtonListener implements ActionListener {

		/** Instantiate the customer query */
		private CustomerQuery customerQuery = new CustomerQuery();

		/*
		 * Implements the action listener.
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// get input value from keyboard.
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phone = phoneField.getText();
			String subscribe = subscribeField.getText();
			String current = currentPointField.getText();
			String reward = rewardPointField.getText();

			// Convert these variables to integer.
			int currentPoint = Integer.parseInt(current);
			int rewardPoint = Integer.parseInt(reward);

			/** Create the source button upon which button caused fire. */
			JButton source = (JButton) event.getSource();
			if(source == registerBtn) {
				if(customerQuery.getCustomerRegister(firstName, lastName, phone, subscribe, currentPoint, rewardPoint)) {
					JOptionPane.showMessageDialog(frame, "Success!A new customer has been created.", "SUCCESS_MESSAGE_INFORMATION", JOptionPane.OK_OPTION);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Errored! Customer cannot be created this time, check entrie are valid and try again", "ERROR_MESSAGE_INFORMATION", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			} else if(source == goBackBtn) {
				//					customerQuery.promptAskingForATask();
				frame.dispose();				
			}
		} // End of the inner class CustomerRegistrationButtonListener.

	}
}
