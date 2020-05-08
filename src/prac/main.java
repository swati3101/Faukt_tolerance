package prac;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class main extends JFrame {

	private JPanel contentPane;
	private final JLabel lblParameter = new JLabel("Parameter 1");
	private JTextField textField;
	private JTextField textField_1;
	int first;
	int second;
	private final JTextField textField_2 = new JTextField();
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() {
		textField_2.setColumns(10);
		
		
		setTitle("Replication Scenario for Fault Tolerance");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblParameter.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameter.setBounds(23, 32, 110, 17);
		contentPane.add(lblParameter);

//		int first;
		textField = new JTextField();
		textField.setBounds(163, 32, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		

		
		//int first = Integer.parseInt(textField.getText());
		
		JLabel lblParameter_1 = new JLabel("Parameter 2");
		lblParameter_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblParameter_1.setBounds(23, 72, 110, 26);
		contentPane.add(lblParameter_1);
		
		//int second;
		textField_1 = new JTextField();
		textField_1.setBounds(163, 75, 165, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Answer");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(61, 186, 68, 17);
		contentPane.add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setBounds(139, 184, 269, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		
		//int second = Integer.parseInt(textField_1.getText());
		
		JButton btnSendToServer = new JButton("Send to Server");
		btnSendToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				first = Integer.parseInt(textField.getText());
				second = Integer.parseInt(textField_1.getText());
				
				System.out.println(first);
				System.out.println(second);
				
				
				int port = 0;
				try{
					
					
					
					if (isSocketAliveUitlity("localhost", 4443) == true)
					{
						port = 4443;
					}
					else if (isSocketAliveUitlity("localhost", 8689) == true)
					{
						port = 8689;
					}
					else if (isSocketAliveUitlity("localhost", 5960) == true)
					{
						port = 5960;
					}
					boolean isContinue =true;
			
						boolean isValid =false;
						Scanner scanner = new Scanner(System.in);
						String selected= null;
						while(!isValid){
			
							System.out.println("Addition");
							
							selected ="add";
							 isValid=true;
						}	
						
						//Connecting with Naming Server.
						//InetAddress ip = InetAddress.getLocalHost();
						Socket opSocket = new Socket("localhost", port);
						DataOutputStream opToClient = new DataOutputStream(opSocket.getOutputStream());
						opToClient.writeInt(first);
						opToClient.writeInt(second);
						opToClient.writeUTF(selected);
						
						//Getting ans from Naming Server
						DataInputStream opInStream = new DataInputStream(opSocket.getInputStream());
						String ans = opInStream.readUTF();
						
						//Printing Answer and asking for continuation
						//contentPane.add(ans);
						textField_3.setText(ans);
						
						System.out.println(ans);
						
					System.out.println("Exit performed successfully.");
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				finally{
					//opSocket.close();
				}
			}
			
			
					});
		btnSendToServer.setBounds(108, 136, 137, 23);
		contentPane.add(btnSendToServer);
		
		
	}
	
	public static boolean isSocketAliveUitlity(String hostName, int port) {
		boolean isAlive = false;
 
		// Creates a socket address from a hostname and a port number
		SocketAddress socketAddress = new InetSocketAddress(hostName, port);
		Socket socket = new Socket();
 
		// Timeout required - it's in milliseconds
		int timeout = 2000;
 
		log("hostName: " + hostName + ", port: " + port);
		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;
 
		} catch (SocketTimeoutException exception) {
			System.out.println("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
		} catch (IOException exception) {
			System.out.println(
					"IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
		}
		return isAlive;
	}
 
	// Simple log utility
	private static void log(String string) {
		System.out.println(string);
	}
 
	// Simple log utility returns boolean result
	private static void log(boolean isAlive) {
		System.out.println("isAlive result: " + isAlive + "\n");

		
	}
}
