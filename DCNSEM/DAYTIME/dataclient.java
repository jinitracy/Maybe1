import java.io.*;
import java.net.*;
import java.util.*;

public class data
client{
    public static void main(String args[]) throws IOException{
        Socket socket = new Socket(InetAddress.getLocalHost(), 5217);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println(dis.readLine());
    }
}