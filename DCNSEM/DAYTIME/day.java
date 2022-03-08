import java.io.*;
import java.net.*;
import java.util.*;
public class day{
    public static void main(String args[])throws Exception{
        Socket s = new Socket(InetAddress.getLocalHost(),5217);
        DataInputStream dis = new DataInputStream(s.getInputStream());
        System.out.println(dis.readLine());
        }
    }
