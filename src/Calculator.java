import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Calculator application created using Java GUI with Java Swing library.
 * Only supports basic arithmetic operations.
 * 
 * NOTE: after computing the result, inputting numbers will only add onto the text field
 * 
 * @author Ricky Kuang
 *
 */
public class Calculator extends JFrame
{
	//private JLabel calcLabel;
	private JTextField outputField;
	private ArrayList<JButton> digits;
	private String currentOperation;
	private Double firstOperand;
	private Double secondOperand;
	private Double answer;
	
	/**
	 * Constructor for the Calculator application.
	 * GUI layout set up using Swing library.
	 */
	public Calculator() {		
		// initialize instance variables
		outputField = new JTextField("0", 37);
		outputField.setHorizontalAlignment(JTextField.CENTER);
		digits = new ArrayList<JButton>();
		currentOperation = "";
		firstOperand = 0.0;
		secondOperand = 0.0;
		answer = 0.0;
		
		// DISPLAY PANEL
		JPanel displayPanel = new JPanel(new FlowLayout());
		displayPanel.add(outputField);
		
		// BUTTON PANEL
		JPanel buttonPanel = new JPanel(new GridLayout(1,2));
		displayPanel.add(buttonPanel);
		
		// DIGIT PANEL
		JPanel digitPanel = new JPanel(new GridLayout(4,3));
		
		//==================================================== DIGIT BUTTONS ====================================================//
		// create new buttons and add to digits list
		for (int i = 0; i <= 9; i++)
			digits.add(new JButton(Integer.toString(i)));
		digits.add(new JButton("."));
		digits.add(new JButton("+/-"));
		
		// add digit buttons to digit panel
		for (int i = 0; i <= 11; i++)
			digitPanel.add(digits.get(i));
		buttonPanel.add(digitPanel);
		
		// Add listener for first 10 digits
		for (int i = 0; i <= 9; i++) {
			digits.get(i).addActionListener(new ActionListener() {
				/**
				 * Adds an action listener for each of the first 10 digits.
				 * Displays the digit that was clicked in text field.
				 */
				@Override
				public void actionPerformed(ActionEvent event) {
					String currentOutput = outputField.getText();
					JButton source = (JButton)event.getSource();
					String newDigit = "";
					for (int j = 0; j <= 9; j++) {
						if (source == digits.get(j)) {
							newDigit = Integer.toString(j);
							break;
						}
					}
					
					currentOutput = currentOutput + newDigit;
					currentOutput = currentOutput.replaceFirst("^0+(?!$)",  "");
					outputField.setText(currentOutput);
				}
			});
		}
		
		// Add listener for decimal point
		digits.get(10).addActionListener(new ActionListener() {
			/**
			 * Adds an action listener for the decimal point. Just adds decimal point to number in text field.
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				String currentOutput = outputField.getText();
				if (currentOutput.indexOf(".") < 0)
					outputField.setText(currentOutput+".");
			}
		});
		
		// Add listener for '+/-' button. Cases for both positive and negative numbers.
		digits.get(11).addActionListener(new ActionListener() {
			/**
			 * Adds an action listener for the positive/negative button. Makes current number the opposite of itself.
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				String currentText = outputField.getText();
				Double currentTextDouble = Double.parseDouble(currentText);
				if (currentTextDouble >= 0) {
					currentTextDouble -= currentTextDouble * 2;
					outputField.setText(currentTextDouble.toString());
			}
				else {
					currentText = currentText.substring(1);
					outputField.setText(currentText);
				}
			}
		});
		
		//==================================================== IMAGE ICONS ====================================================//
		ImageIcon plusIcon = new ImageIcon("./plus-icon.png");
		Image plusImg = plusIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		plusIcon = new ImageIcon(plusImg);
		
		ImageIcon minusIcon = new ImageIcon("./minus-icon.png");
		Image minusImg = minusIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		minusIcon = new ImageIcon(minusImg);
		
		ImageIcon multiplyIcon = new ImageIcon("./multiply-icon.png");
		Image multiplyImg = multiplyIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		multiplyIcon = new ImageIcon(multiplyImg);
		
		ImageIcon divideIcon = new ImageIcon("./divide-icon.png");
		Image divideImg = divideIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		divideIcon = new ImageIcon(divideImg);
		
		ImageIcon clearIcon = new ImageIcon("./clear-icon.png");
		Image clearImg = clearIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		clearIcon = new ImageIcon(clearImg);
		
		ImageIcon equalIcon = new ImageIcon("./equal-icon.png");
		Image equalImg = equalIcon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		equalIcon = new ImageIcon(equalImg);
		
		//==================================================== OPERATOR BTNS ====================================================//
		digits.add(new JButton("Plus", plusIcon));
		digits.add(new JButton("Minus", minusIcon));
		digits.add(new JButton("Mult", multiplyIcon));
		digits.add(new JButton("Divide", divideIcon));
		digits.add(new JButton("Clear", clearIcon));
		digits.add(new JButton("Equal", equalIcon));

		// Action listener for PLUS button
		digits.get(12).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentOperation = "+";
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText("0");
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		});
		
		// Action listener for MINUS button
		digits.get(13).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentOperation = "-";
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText("0");
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		});
		
		// Action listener for MULTIPLY button
		digits.get(14).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentOperation = "*";
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText("0");
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		});
		
		// Action listener for DIVIDE button
		digits.get(15).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				currentOperation = "/";
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText("0");
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		});
		
		// Action listener for CLEAR button
		digits.get(16).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				clearValues();
			}
		});
		
		// Action listener for EQUAL button
		digits.get(17).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				equalEvent();
				String currentText = outputField.getText();
				outputField.setText(firstOperand.toString());
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					outputField.setText(firstOperand.toString());
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		});
		
		// ADD OPERATORS TO OPERATOR PANEL
		JPanel operatorPanel = new JPanel(new GridLayout(6, 1));
		for (int i = 12; i <= 17; i++)
			operatorPanel.add(digits.get(i));
		
		// add operator panel
		buttonPanel.add(operatorPanel);
		
		//==================================================== OPERATOR MENU ====================================================//
		JMenuBar operatorMenuBar = new JMenuBar();
		JMenu operatorMenu = new JMenu("Operator Menu");
		operatorMenuBar.add(operatorMenu);
		
		// add plus item
		JMenuItem plusItem = new JMenuItem("Plus", plusIcon);
		operatorMenu.add(plusItem);
		
		// add minus item
		JMenuItem minusItem = new JMenuItem("Minus", minusIcon);
		operatorMenu.add(minusItem);
		
		// add multiply item
		JMenuItem multItem = new JMenuItem("Multiply", multiplyIcon);
		operatorMenu.add(multItem);
		
		// add divide item
		JMenuItem divideItem = new JMenuItem("Divide", divideIcon);
		operatorMenu.add(divideItem);
		
		JMenuItem equalItem = new JMenuItem("Equal", equalIcon);
		operatorMenu.add(equalItem);
		
		// add clear item
		JMenuItem clearItem = new JMenuItem("Clear", clearIcon);
		operatorMenu.add(clearItem);
		
		ActionListener operatorMenuAL = new ActionListener() {
			/**
			 * Method to select/perform operation depending on what was selected from operator menu.
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				JMenuItem source = (JMenuItem)event.getSource();
				
				if (source == plusItem) {
					currentOperation = "+";
				}
				else if (source == minusItem) {
					currentOperation = "-";
				}
				else if (source == multItem) {
					currentOperation = "*";
				}
				else if (source == divideItem) {
					currentOperation = "/";
				}
				else if (source == clearItem) {
					clearValues();
				}
				else if (source == equalItem) {
					equalEvent();
				}
				
				String currentText = outputField.getText();
				try {
					Double currentTextDouble = Double.parseDouble(currentText);
					firstOperand = currentTextDouble;
					if (source == equalItem)
						outputField.setText(firstOperand.toString());
					else
						outputField.setText("0");
				} catch(NumberFormatException e) {
					clearValues();
				}
			}
		};
		
		// add action listener for all JMenu operators
		plusItem.addActionListener(operatorMenuAL);
		minusItem.addActionListener(operatorMenuAL);
		multItem.addActionListener(operatorMenuAL);
		divideItem.addActionListener(operatorMenuAL);
		clearItem.addActionListener(operatorMenuAL);
		equalItem.addActionListener(operatorMenuAL);
		
		// set JMenuBar
		this.setJMenuBar(operatorMenuBar);
		
		// close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add displayPanel and set size, location, title. make visible
		add(displayPanel);
		setTitle("Calculator");
		setSize(450, 300);
		setLocation(500, 220);
		setVisible(true);
	}
	
	/**
	 * Method that is called upon when the 'equal' operator is called.
	 * Sets the answer variable as the result of the arithmetic operation.
	 */
	private void equalEvent() {
		answer = 0.0;
		String currentOutput = outputField.getText();
		try {
			secondOperand = Double.parseDouble(currentOutput);
			
			if (currentOperation == "+") 
				answer = firstOperand + secondOperand;
			else if (currentOperation == "-")
				answer = firstOperand - secondOperand;
			else if (currentOperation == "*")
				answer = firstOperand * secondOperand;
			else if (currentOperation == "/") {
				if (secondOperand != 0.0)
					answer = firstOperand / secondOperand;
				else
					clearValues();
			}
			// case when user clicks equal but no operation was selected, just returns the current output
			else if (currentOperation == "")
				answer = secondOperand;
			
			outputField.setText(answer.toString());
			firstOperand = answer;
		} catch(NumberFormatException e) {
			clearValues();
		}
	}
	
	/**
	 * Clears all fields, resets everything back to 0.
	 */
	private void clearValues() {
		currentOperation = "";
		firstOperand = 0.0;
		secondOperand = 0.0;
		answer = 0.0;
		outputField.setText("0");
	}
	
	/**
	 * Main method, calls the constructor of Calculator.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Calculator();
	}
}