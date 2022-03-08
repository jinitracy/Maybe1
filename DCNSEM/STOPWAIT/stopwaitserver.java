import java.io.*;
import java.util.*;
import java.net.*;
public class stopwaitserver{
    public static void main(String args[]){
        try{
            ServerSocket server = new ServerSocket(3000);
            Socket socket = server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int frame_size = dis.readInt();
            for(int i=0; i< frame_size; i++){
                ServerSocket server1 = new ServerSocket(4000+i);
                Socket socket1 = server1.accept();
                DataInputStream dis1 = new DataInputStream(socket.getInputStream());
                String data = dis1.readUTF();
                System.out.println("Message is" + data);
                System.out.println("Frames" + i%2 + "received");
                System.out.println("Ack"+ i%2);
            }
        }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
    }
    
