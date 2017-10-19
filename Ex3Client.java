import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Ex3Client {
	public static void main(String[] args) throws IOException {
		try(Socket socket = new Socket("18.221.102.182", 38103)){
			System.out.println("Connected to server.");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			byte size =(byte)is.read();
			byte[] store = new byte[size];
			System.out.println(size);
			for(int i =0; i<size;i++){
				store[i]=(byte)is.read();
			}
		System.out.println("Data received");
		for(int i =0;i<size;i++){
			System.out.println();
		}
		
		}
	}
	
}
