import java.io.*;
import java.net.*;

public class simplexclient {
    public static void main(String args[]){
        try{
            Socket socket = new Socket("localhost", 3000);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String str= "Hello World";
            dos.writeUTF(str);
            socket.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
}
