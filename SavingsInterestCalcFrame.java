package edu.weber.cs3230.GUI.AdvancedGUI.homework;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SavingsInterestCalcFrame extends JFrame implements ActionListener {
   private JTextArea outputArea;                  // Displays yearly savings
   private JButton calcButton;                    // Triggers savings calculation
   private JFormattedTextField initSavingsField;  // Holds savings amount
   private JFormattedTextField interestRateField; // Holds interest amount
   private JFormattedTextField yearsField;        // Holds num years

   /* Constructor creates GUI components and adds GUI components using a GridBagLayout. */
   SavingsInterestCalcFrame() {
      GridBagConstraints layoutConst; // Used to specify GUI component layout
      JScrollPane scrollPane;         // Container that adds a scroll bar
      JLabel initSavingsLabel;        // Label for savings
      JLabel interestRateLabel;       // Label for interest
      JLabel yearsLabel;              // Label for num years
      JLabel outputLabel;             // Label for yearly savings

      // Set frame's title
      setTitle("Savings Calculator");

      // Create labels
      initSavingsLabel = new JLabel("Initial Savings:");
      interestRateLabel = new JLabel("Interest Rate:");
      yearsLabel = new JLabel("Number of Years:");
      outputLabel = new JLabel("Yearly Savings:");

      // Create output area and add it to scroll pane
      outputArea = new JTextArea(10, 20); // 10 lines tall, 20 characters wide
      scrollPane = new JScrollPane(outputArea);

      // Create a button and add an action listener to it
      calcButton = new JButton("Calculate");
      calcButton.addActionListener(this);

      // Formats for the input fields
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
      NumberFormat percentFormat = NumberFormat.getPercentInstance();
      percentFormat.setMinimumFractionDigits(1);
      NumberFormat integerFormat = NumberFormat.getIntegerInstance();

      // Initialize fields
      initSavingsField = new JFormattedTextField(currencyFormat);
      initSavingsField.setEditable(true);
      initSavingsField.setColumns(10);
      initSavingsField.setValue(0);

      interestRateField = new JFormattedTextField(percentFormat);
      interestRateField.setEditable(true);
      interestRateField.setValue(0.0);

      yearsField = new JFormattedTextField(integerFormat);
      yearsField.setEditable(true);
      yearsField.setValue(0);

      // Use a GridBagLayout
      setLayout(new GridBagLayout());

      // Add components to the frame using GridBagLayout constraints
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;

      layoutConst.gridx = 0;
      layoutConst.gridy = 0;
      add(initSavingsLabel, layoutConst);

      layoutConst.gridx = 1;
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      add(initSavingsField, layoutConst);

      layoutConst.gridy = 1;
      add(interestRateLabel, layoutConst);

      layoutConst.gridx = 1;
      add(interestRateField, layoutConst);

      layoutConst.gridy = 2;
      add(yearsLabel, layoutConst);

      layoutConst.gridx = 1;
      add(yearsField, layoutConst);

      layoutConst.gridy = 3;
      layoutConst.gridx = 0;
      layoutConst.gridwidth = 2;
      layoutConst.fill = GridBagConstraints.NONE;
      add(calcButton, layoutConst);

      layoutConst.gridy = 4;
      add(outputLabel, layoutConst);

      layoutConst.gridy = 5;
      layoutConst.gridwidth = 2;
      layoutConst.fill = GridBagConstraints.BOTH;
      add(scrollPane, layoutConst);

      pack();
      setVisible(true);
   }
   @Override
   public void actionPerformed(ActionEvent event) {
      double savingsDollars = ((Number) initSavingsField.getValue()).doubleValue();
      // Ensure interest rate is divided by 100 if it is entered as a percentage
      double interestRate = ((Number) interestRateField.getValue()).doubleValue() / 100;
      int numYears = ((Number) yearsField.getValue()).intValue();

      // Check for valid input
      if (savingsDollars < 0 || interestRate < 0 || numYears < 0) {
         outputArea.setText("Invalid input values.");
         return;
      }

      outputArea.setText("");
      double currentSavings = savingsDollars;

      for (int i = 1; i <= numYears; i++) {
         currentSavings *= (1 + interestRate); // Compound interest calculation
         outputArea.append("Savings in year " + i + ": $" + String.format("%.2f\n", currentSavings));
      }
   }



   public static void main(String[] args) {
      new SavingsInterestCalcFrame();
   }
}
