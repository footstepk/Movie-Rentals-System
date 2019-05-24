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
 * UpdateCustomer is class that represents an update of existing customer entry form.
 * 
 * It implements the GUI component and display the form to the screen.
 * 
 * @author Kok Heng
 *
 */

public class UpdateCustomer implements RegistrationForm {
	/** Declare the GUI instance . */

	// The GUI container to hold the components.
	private JFrame frame;
	private JPanel panel;

	/** Declare the components. */
	private JButton updateBtn; // submit update button.
	private JButton goBackBtn; // for go back to home page.
	private JLabel updateLabel, idLabel, phoneLabel, subscribeLabel,currentPointLabel;
	private JTextField idField, phoneField, subscribeField, currentPointField;

	/** Construct the GUI component to update registration form. */
	public UpdateCustomer() {
	}

	/*
	 * Create customer update form label to the window frame.
	 * @return the label to represent the label text.
	 */
	@Override
	public JLabel setRegistrationPageLabel() {
		final String message = "Update Customer Form\nEnter customer details with * is mandatory";
		// Setup the label.
		updateLabel = new JLabel();
		updateLabel.setText(message);
		updateLabel.setOpaque(true);
		updateLabel.setBackground(new Color(100, 165, 128));
		updateLabel.setPreferredSize(new Dimension(180, 180));
		return updateLabel;
	}

	/*
	 * Create the panel to hold the components.
	 * @return the panel of the components.
	 */
	@Override
	public JComponent setRegistrationContentPane() {
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 5, 5));

		// insert all the components into the panel.
		idLabel = new JLabel("* Enter customer id: ");
		phoneLabel = new JLabel("* Enter new phone number: ");
		subscribeLabel = new JLabel("* Enter new subscription plan: ");
		currentPointLabel = new JLabel("* Enter new current point: ");

		idField = new JTextField(80);
		idField.setEditable(true);
		idField.setOpaque(true);

		phoneField = new JTextField(80);
		phoneField.setEditable(true);
		phoneField.setOpaque(true);

		subscribeField = new JTextField(80);
		subscribeField.setEditable(true);
		subscribeField.setOpaque(true);

		currentPointField = new JTextField(80);
		currentPointField.setEditable(true);
		currentPointField.setOpaque(true);

		idLabel.setLabelFor(idLabel);
		phoneLabel.setLabelFor(phoneLabel);
		subscribeLabel.setLabelFor(subscribeLabel);
		currentPointLabel.setLabelFor(currentPointLabel);

		panel.add(idLabel);
		panel.add(idField);
		panel.add(phoneLabel);
		panel.add(phoneField);
		panel.add(subscribeLabel);
		panel.add(subscribeField);
		panel.add(currentPointLabel);
		panel.add(currentPointField);
		panel.add(setRegistrationButton());
		panel.add(setGoBackButton());
		return panel;
	}

	/*
	 * Create the update button
	 * @return the submission button of the update customer.
	 */
	@Override
	public JButton setRegistrationButton() {
		updateBtn = new JButton("Update");
		updateBtn.setOpaque(true);
		updateBtn.setBackground(new Color(192, 192, 188));
		updateBtn.setPreferredSize(new Dimension(25, 25));

		// Allocate an instance of this inner class when click on fire.
		UpdateCustomerButtonListener listener = new UpdateCustomerButtonListener();
		updateBtn.addActionListener(listener);

		return updateBtn;
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
		UpdateCustomerButtonListener listener = new UpdateCustomerButtonListener();
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

		frame.setTitle("Movie Rental System -> Update Customer Form");
		frame.setSize(350000, 300000);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * UpdateCustomerButtonListener is an instance of the inner class of UpdateCustomer.
	 * It verifies the entries of update customer registration details.
	 * It implements the ActionEvent. 
	 */
	private class UpdateCustomerButtonListener implements ActionListener {

		/** Instantiate the customer query */
		private CustomerQuery customerQuery = new CustomerQuery();

		/*
		 * Implements the action listener.
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// get input value from keyboard.
			String customerID = idField.getText();
			int id = Integer.parseInt(customerID); // needs convert to integer.
			String phone = phoneField.getText();
			String subscribe = subscribeField.getText();
			String current = currentPointField.getText();
			int currentPoint = Integer.parseInt(current); // need to convert to integer.

			/** Create the source button upon which button caused fire. */
			JButton source = (JButton) event.getSource();
			if(source == updateBtn) {
				if(customerQuery.getUpdateCustomer(id, phone, subscribe, currentPoint)) {
					JOptionPane.showMessageDialog(frame, "Success! Customer details has been updated.", "SUCCESS_MESSAGE_INFORMATION", JOptionPane.OK_OPTION);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Errored! Customer either doesn't exists or wrong customer id entered, check entries are valid and try again", "ERROR_MESSAGE_INFORMATION", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			} else if(source == goBackBtn) {
//				customerQuery.promptAskingForATask();
				frame.dispose();				
			}
		} // End of the inner class CustomerRegistrationButtonListener.
	}

}
