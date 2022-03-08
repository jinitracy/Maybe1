import java.io.*;
import java.net.*;
import java.util.*;

public class ftpclient {
    public static void main(String args[]){
    try{
        Socket socket = new Socket("localhost", 3000);

        byte Content[] = new byte[10000];
        String FileName= "jiniii.txt";

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int size = dis.read(Content);


        FileOutputStream FOS = new FileOutputStream(FileName);
        BufferedOutputStream BOS = new BufferedOutputStream(FOS);

        BOS.write(Content,0, size);
        System.out.println("File received successfully");

        socket.close();
        BOS.flush();

    }
    catch(Exception e){
        System.out.println(e);
    }
}
}
