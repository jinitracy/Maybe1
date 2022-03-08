import java.io.*;
import java.net.*;

public class simplexserver {
    public static void main(String args[]){

        try{
        ServerSocket server = new ServerSocket(3000);
        System.out.println("The server is waiting for client request...");
        while(true){
            Socket socket= server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String str = (String) dis.readUTF();
            System.out.println("The received message is" + str);
            server.close();
        }
    }
    catch(Exception e){
        System.out.println(e);
    }
    }
    
}
