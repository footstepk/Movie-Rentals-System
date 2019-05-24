package edits.title;

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
import models.title.TitleQuery;

/**
 * CreateNewTitle is class that represents a creation of new title entry form.
 * 
 * It implements the GUI component and display the form to the screen.
 * 
 * @author Kok Heng
 *
 */

public class CreateNewTitle implements RegistrationForm {

	/** Declare the GUI instance . */

	// The GUI container to hold the components.
	private JFrame frame;
	private JPanel panel;

	/** Declare the components. */
	private JButton creatBtn; // submit creation button.
	private JButton goBackBtn; // for go back to home page.
	private JLabel creationLabel,  titleLabel, titleNameLabel, yearOfReleaseLabel, formatLabel, numberInStockLabel;
	private JTextField titleField, titleNameField, yearOfReleaseField, formatField, numberInStockField;


	/** Construct the GUI component to create new registration form. */
	public CreateNewTitle() {
	}

	/*
	 * Create title creation form label to the window frame.
	 * @return the label to represent the creation label text.
	 */
	@Override
	public JLabel setRegistrationPageLabel() {
		final String message = "Title Creation Form\nEnter title details with * is mandatory";
		// Setup the label.
		creationLabel = new JLabel();
		creationLabel.setText(message);
		creationLabel.setOpaque(true);
		creationLabel.setBackground(new Color(124, 165, 121));
		creationLabel.setPreferredSize(new Dimension(180, 180));
		return creationLabel;
	}

	/*
	 * Create the panel to hold the components.
	 * @return the panel of the components.
	 */
	@Override
	public JComponent setRegistrationContentPane() {
		panel = new JPanel();
		panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2, 5, 5));

		// insert all the components into the panel.
		titleLabel = new JLabel("* Enter genre title: ");
		titleNameLabel = new JLabel("* Enter title name: ");
		yearOfReleaseLabel = new JLabel("* Enter year of release: ");
		formatLabel = new JLabel("Enter format e.g. CD/DVD: ");
		numberInStockLabel = new JLabel("Enter numbers in stock: ");

		titleField = new JTextField(80);
		titleField.setEditable(true);
		titleField.setOpaque(true);

		titleNameField = new JTextField(80);
		titleNameField.setEditable(true);
		titleNameField.setOpaque(true);

		yearOfReleaseField = new JTextField(80);
		yearOfReleaseField.setEditable(true);
		yearOfReleaseField.setOpaque(true);

		formatField = new JTextField(80);
		formatField.setEditable(true);
		formatField.setOpaque(true);

		numberInStockField = new JTextField(80);
		numberInStockField.setEditable(true);
		numberInStockField.setOpaque(true);

		titleLabel.setLabelFor(titleLabel);
		titleNameLabel.setLabelFor(titleNameLabel);
		yearOfReleaseLabel.setLabelFor(yearOfReleaseLabel);
		formatLabel.setLabelFor(formatLabel);
		numberInStockLabel.setLabelFor(numberInStockLabel);

		panel.add(titleLabel);
		panel.add(titleField);
		panel.add(titleNameLabel);
		panel.add(titleNameField);
		panel.add(yearOfReleaseLabel);
		panel.add(yearOfReleaseField);
		panel.add(formatLabel);
		panel.add(formatField);
		panel.add(numberInStockLabel);
		panel.add(numberInStockField);
		panel.add(setRegistrationButton());
		panel.add(setGoBackButton());
		return panel;
	}

	/*
	 * Create the creation button
	 * @return the submission button of the creation.
	 */
	@Override
	public JButton setRegistrationButton() {
		creatBtn = new JButton("Create");
		creatBtn.setOpaque(true);
		creatBtn.setBackground(new Color(224, 224, 163));
		creatBtn.setPreferredSize(new Dimension(25, 25));

		// Allocate an instance of this inner class when click on fire.
		TitleCreationButtonListener listener = new TitleCreationButtonListener();
		creatBtn.addActionListener(listener);

		return creatBtn;
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
		TitleCreationButtonListener listener = new TitleCreationButtonListener();
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

		frame.setTitle("Movie Rental System -> Title Creation Form");
		frame.setSize(350000, 300000);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	 * TitleCreationButtonListener is an instance of the inner class of CreatNewTitle.
	 * It verifies the entries of title creation details.
	 * It implements the ActionEvent. 
	 */
	private class TitleCreationButtonListener implements ActionListener {

		/** Instantiate the title query */
		private TitleQuery titleQuery = new TitleQuery();


		/*
		 * Implements the action listener.
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// get input value from keyboard.
			String title= titleField.getText();
			String titleName = titleNameField.getText();
			String year = yearOfReleaseField.getText();
			String format = formatField.getText();
			String stock = numberInStockField.getText();

			// Convert these string values to integer.
			int yearRelease = Integer.parseInt(year);
			int numsOfStock = Integer.parseInt(stock);

			/** Create the source button upon which button caused fire. */
			JButton source = (JButton) event.getSource();
			if(source == creatBtn) {
				if(titleQuery.getTitleCreate(title, titleName, yearRelease, format, numsOfStock)) {
					JOptionPane.showMessageDialog(frame, "Success!A new title has been created.", "SUCCESS_MESSAGE_INFORMATION", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame, "Errored! The title cannot be created this time, check entries are valid and try again", "ERROR_MESSAGE_INFORMATION", JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}
			} else if(source == goBackBtn) {
				//titleQuery.promptAskingForATask();
				frame.dispose();
			}
		} // End of the inner class CustomerRegistrationButtonListener.
	}

}
