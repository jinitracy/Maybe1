import java.io.*;
import java.net.*;
import java.util.*;

public class swserver{
    public static void main(String args[]) throws IOException{
    ServerSocket server = new ServerSocket(3000);
    System.out.println("The server is connected");
    Socket socket = server.accept();
    Scanner scan = new Scanner(System.in);
    try{
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Enter the Frame size:");
        int f = scan.nextInt();
        dos.writeInt(f);
        System.out.println("Enter the window size");
        int w = scan.nextInt();
        System.out.println("Enter the data for " + f + "frames");
        String data[]= new String[f];
        scan.nextLine();
        for(int i=0;i<f;i++){
            data[i]= scan.nextLine();
        }
        int i=0;
        while(i<f){
            if(i+w>=f){
            System.out.println("Sending" + (f-i) + "frames..");
            dos.writeUTF(Integer.toString(f-i));
            for(i=i;i<f;i++){
                dos.writeUTF(data[i]);
            }
            }
            else{
            System.out.println("Sending" + w + "frames");
            dos.writeUTF(Integer.toString(w));
            for(int j=0;j<w;j++){
                dos.writeUTF(data[i]);
                i++;
            }
            }
            int ack = Integer.parseInt(dis.readUTF());
            System.out.println("Acknowledgement received for" + ack + "Frames");
            System.out.println("Next Expecting" + (ack % w) + "frames");
            i = ack;
        }
    }
    catch(Exception e){
        System.out.println(e);
    }
    }
}