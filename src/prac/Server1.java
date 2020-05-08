package prac;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static void main(String []arg) throws IOException{
		
		
		try{
			
			while(true){
				ServerSocket serverSocket = new ServerSocket(8689);
				Socket sc = serverSocket.accept();
				try{
				System.out.println("Getting Addition Request.. ");
				DataInputStream inFromClient = new DataInputStream(sc.getInputStream());
				int first = inFromClient.readInt();
				int second = inFromClient.readInt();

				PrintWriter pr = new PrintWriter(sc.getOutputStream(),true);
				int answer=first+second ;
				
				DataOutputStream outFromClient = new DataOutputStream(sc.getOutputStream());
				
				outFromClient.writeUTF("Answer of Addition operation is :  "+answer);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				finally{
					sc.close();
					serverSocket.close();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
