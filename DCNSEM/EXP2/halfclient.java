import java.io.*;
import java.net.*;
import java.util.*;

public class halfclient {
    public static void main(String args[]){
        try{
            Socket socket = new Socket("localhost", 3000);
            Scanner scan = new Scanner(System.in);
            while(true){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            System.out.println("Enter the message");
            String str= scan.nextLine();
            if(str.equals("STOP"))
            break;
            dos.writeUTF(str);
            String res = dis.readUTF();
            System.out.println("The reply is " + res);
            if(res.equals("STOP"))
            break;
            }
            socket.close();
            scan.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
