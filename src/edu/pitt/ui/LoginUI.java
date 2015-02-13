package edu.pitt.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Timer;

import edu.pitt.bank.Customer;
import edu.pitt.bank.Security;
import edu.pitt.utilities.ErrorLogger;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class LoginUI {

	private JFrame frmBankLogin;
	private JTextField txtLogin;
	private JTextField txtPassword;
	JLabel lblLoginName;
	JLabel lblPassword;
	JButton btnLogin;
	

	int countdownTimer = 60;
	int delayTimer = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frmBankLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					ErrorLogger.log("Login window error in LoginUI");
					ErrorLogger.log(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	/**
	 * 
	 */
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBankLogin = new JFrame();
		frmBankLogin.setTitle("Bank 1017 Login");
		frmBankLogin.setBounds(100, 100, 450, 300);
		frmBankLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBankLogin.getContentPane().setLayout(null);

		lblLoginName = new JLabel("Login Name:");
		lblLoginName.setBounds(24, 42, 98, 16);
		frmBankLogin.getContentPane().add(lblLoginName);

		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(24, 88, 98, 16);
		frmBankLogin.getContentPane().add(lblPassword);

		txtLogin = new JTextField();
		txtLogin.setBounds(134, 36, 258, 28);
		frmBankLogin.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(134, 82, 258, 28);
		frmBankLogin.getContentPane().add(txtPassword);

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String loginName = txtLogin.getText();

					int pin = Integer.parseInt(txtPassword.getText());
					Security s = new Security();
					Customer c = s.validateLogin(loginName, pin);

					if (c != null) {
						AccountDetailsUI ad = new AccountDetailsUI(c);
						frmBankLogin.setVisible(false);
						 
						ActionListener actionToPreform = new ActionListener() {  
						      public void actionPerformed(ActionEvent evt) {
						        if(countdownTimer > 0){
						        	countdownTimer--;
						        }
						        if(countdownTimer == 0){
						        	JOptionPane.showMessageDialog(null, "Your session has expired.");
						        	countdownTimer = -1;
						        	System.exit(0);
						        }
						      }
						  };
						new Timer(delayTimer, actionToPreform).start();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Login");
					}
				} catch (Exception ex) {
					ErrorLogger.log("Invalid pin was entered");
					ErrorLogger.log(ex.getMessage());
				}
			}

		});
		btnLogin.setBounds(275, 122, 117, 29);
		frmBankLogin.getContentPane().add(btnLogin);
	}
}
