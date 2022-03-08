import java.io.*;
import java.util.*;
import java.net.*;

public class stopwaitclient{
    public static void main(String args[]){
        try{
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of frames");
        int frame_size = scan.nextInt();
        if(frame_size==0){
            System.out.println("no frames");
          }
          else{
            Socket socket = new Socket("localhost",3000);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.write(frame_size);
          }
          for(int i=0;i<frame_size;i++){
            System.out.println("Enter the data");
            String data = scan.next();
            System.out.println("frames "+i%2+"sent");
            Socket socket1 = new Socket("localhost",4000+i);
            DataOutputStream dos1 = new DataOutputStream(socket1.getOutputStream());
            dos1.writeUTF(data);
            
            
            System.out.println("ack "+i%2);

            }

        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}

