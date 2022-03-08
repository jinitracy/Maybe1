import java.io.*;
import java.net.*;
import java.util.*;

public class dateserver{
    public static void main(String args[]) throws IOException{
        ServerSocket server = new ServerSocket(5217);
        System.out.println("Server is connected..");
        while(true){
            Socket socket = server.accept();
            DataOutputStream dos = new DataOutputStream( socket.getOutputStream());
            dos.writeBytes("Date" + (new Date()).toString());
            dos.flush();
            socket.close();
            server.close();
        }

    }
}