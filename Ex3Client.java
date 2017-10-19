import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

public class Ex3Client {
	
	private static byte[] data= new byte[2];
	public static void main(String[] args) throws IOException {
		try(Socket socket = new Socket("18.221.102.182", 38103)){
			
			System.out.println("Connected to server.");
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			int size = is.read();
			byte[] store = new byte[size];
			System.out.println("Reading "+size+" bytes.");
			System.out.println("Data received:");
			System.out.print(" ");
			for(int i =0;i<size;i++){
				if(i%10 == 0 && i>0) {
					System.out.print("\n ");
				}
				store[i]=(byte)is.read();
				System.out.printf("%02X",store[i]);
					
			}
			int sendval=checksum(store);
			returnBytes(sendval);
			System.out.println("\nChecksum calculated:" + " 0x" + javax.xml.bind.DatatypeConverter.printHexBinary(data));
			os.write(data);
			if(is.read()==1){
				System.out.println("Response good");
			}
			else
				System.out.println("Response bad");
		}	
	}
	public static short checksum(byte [] total)
	{
		long sum=0;
		int i =0;
		int size = total.length;
		while(size>1)	{
			sum +=((total[i]<<8) & 0xFF00 | (total[i+1] & 0xFF));
			if((sum & 0xFFFF0000) > 0){
				sum = sum & 0xFFFF;
				sum++;
			}
			size-=2;
			i+=2;		
		}
		if(size > 0){
			sum +=(total[i] << 8 & 0xFF00);
				if  ((sum & 0xFFFF0000) > 0){
					sum = sum & 0xFFFF;
					sum++;
				}
		}
		return (short) (~sum & 0xFFFF);
	}
	
	public static void returnBytes(int val)
	{
		int temp = val>>8;
		temp &= 0xFF;
		data[0]=(byte)temp;
		temp =(int)val;
		temp &= 0xFF;
		data[1]=(byte)temp;
	}
}


