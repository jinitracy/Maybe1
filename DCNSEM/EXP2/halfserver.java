import java.io.*;
import java.net.*;
import java.util.*;

public class halfserver {
    public static void main(String args[]){
     try{
        ServerSocket server = new ServerSocket(3000);
        Socket socket = server.accept();
        Scanner scan = new Scanner(System.in);
        System.out.println("The server is connected to port 3000");
        while(true){
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String str = dis.readUTF();
            System.out.println("The message received is " + str );
            if(str.equals("STOP"))
            break;
            System.out.println("Enter the reply");
            String res= scan.nextLine();
            dos.writeUTF(res);
            if(res.equals("STOP"))
            break;
           
        }
        socket.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
}

    
}
