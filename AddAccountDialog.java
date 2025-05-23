/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package edu.weber.cs3230.projects.finalproject;

import java.math.BigDecimal;
import javax.swing.*;

/**
 *
 * @author zycmm
 */
public class AddAccountDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddAccountDialog
     */
    public AddAccountDialog(java.awt.Frame parent, boolean modal, Customer customer) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        this.customer = customer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        initBalanceLabel = new javax.swing.JLabel();
        initBalanceTextField = new javax.swing.JTextField();
        accountTypeLabel = new javax.swing.JLabel();
        accountTypeComboBox = new javax.swing.JComboBox<>();
        addAccountOKButton = new javax.swing.JButton();
        addAccountCancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(3, 2, 5, 5));

        initBalanceLabel.setText("Initial Balance");
        getContentPane().add(initBalanceLabel);
        getContentPane().add(initBalanceTextField);

        accountTypeLabel.setText("Account Type");
        getContentPane().add(accountTypeLabel);

        accountTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Checking", "Savings", "Investment" }));
        getContentPane().add(accountTypeComboBox);

        addAccountOKButton.setText("OK");
        addAccountOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccountOKButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addAccountOKButton);

        addAccountCancelButton.setText("Cancel");
        addAccountCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccountCancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(addAccountCancelButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAccountCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAccountCancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_addAccountCancelButtonActionPerformed

// AddAccountDialog.java

    // Method to handle OK button action in Add Account Dialog
    public void addAccountOKButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String accountType = accountTypeComboBox.getSelectedItem().toString();
            BigDecimal initialBalance = new BigDecimal(balanceTextField.getText());
            BankAccount newAccount;

            switch (accountType) {
                case "Checking":
                    newAccount = new CheckingAccount(initialBalance);
                    break;
                case "Savings":
                    newAccount = new SavingsAccount(initialBalance);
                    break;
                case "Investment":
                    newAccount = new InvestmentAccount(initialBalance);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid account type");
            }

            selectedCustomer.addAccount(newAccount);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid balance format");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public JButton getAddAccountCancelButton() {
        return addAccountCancelButton;
    }

    public JTextField getInitBalanceTextField() {
        return initBalanceTextField;
    }

    public JComboBox<String> getAccountTypeComboBox() {
        return accountTypeComboBox;
    }

    public JButton getAddAccountOKButton() {
        return addAccountOKButton;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountTypeComboBox;
    private javax.swing.JLabel accountTypeLabel;
    private javax.swing.JButton addAccountCancelButton;
    private javax.swing.JButton addAccountOKButton;
    private javax.swing.JLabel initBalanceLabel;
    private javax.swing.JTextField initBalanceTextField;
    // End of variables declaration//GEN-END:variables

    private Customer customer;
}
