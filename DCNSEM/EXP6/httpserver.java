import java.io.*;
import java.net.*;
import java.util.*;

public class httpserver{
    public static void main(String args[]){

        try{
        ServerSocket server = new ServerSocket(3000);
        System.out.println("The server is connected to the client");
        Socket socket = server.accept();
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String strurl = (String)dis.readUTF();
        URL url = new URL(strurl);
        Scanner scan = new Scanner(url.openStream());
        StringBuffer SB = new StringBuffer();
        while (scan.hasNext()){
            SB.append(scan.next());
        }

        String data = SB.toString();
        data=  data.replaceAll(">", "> \n");
        dos.writeUTF(data);
        server.close();
        socket.close();
        scan.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
    }
}