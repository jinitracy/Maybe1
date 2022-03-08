import java.io.*;
import java.net.*;
import java.util.*;

public class httpclient {
    public static void main(String args[]){
        try{
        Socket socket = new Socket("localhost", 3000);
        Scanner scan = new Scanner(System.in);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Enter the website name:");
        String url = scan.nextLine();
        dos.writeUTF(url);
        String code = (String)dis.readUTF();
        System.out.println("The code is" + code);
        socket.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
