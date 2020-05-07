package prac;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {

	public static void main(String arg[]) throws UnknownHostException, IOException{
		
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
			
			while(isContinue)
			{
				boolean isValid =false;
				Scanner scanner = new Scanner(System.in);
				String selected= null;
				while(!isValid){
	
					System.out.println("Addition");
					
					selected ="add";
					 isValid=true;
				}	
				//Asking two parameters for selected operations
				System.out.println("Please enter two parameters for this operation:");
				System.out.println("Parameter 1 :");
				int first = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Parameter 2 :");
				int second = scanner.nextInt();
				
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
				System.out.println(ans);
				System.out.println("Enter 1 if you want to continue or 0 to exit :");
				int operation = scanner.nextInt();
				if(operation==0)
					isContinue=false;
			}
			System.out.println("Exit performed successfully.");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			//opSocket.close();
		}
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
