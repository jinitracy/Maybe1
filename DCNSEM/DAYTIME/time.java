import java.io.*;
import java.net.*;
import java.util.*;
public class time{
    public static void main(String args[])throws Exception{
        ServerSocket ss = new ServerSocket(5217);
        while(true){
            Socket s = ss.accept();
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeBytes("Date" + (new Date()).toString());
            dos.flush();
            s.close();
        }
    }
    
}