import java.io.*;
import java.net.*;
import java.util.*;

public class swclient{
    public static void main(String args[]) throws IOException{
        Socket socket= new Socket("localhost", 3000);
        Scanner scan = new Scanner(System.in);
        try{
         DataInputStream dis = new DataInputStream(socket.getInputStream());
         DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
         int f = dis.readInt();
         String data[] = new String[f];
         int i= 0;
         while(i<f){
             int a= Integer.parseInt((String)dis.readUTF());
             int k = 0;
             for(int j=0;j<a;j++){
                 data[i] = (String)dis.readUTF();
                 System.out.println("The data in the" + k + "frame is" + data[i]);
                 i++;
                 k++;
             }
             System.out.println("Acknowledgment sent for" + a + "frames");
             dos.writeUTF(Integer.toString(i));
         }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}