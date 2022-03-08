import java.io.*;
import java.net.*;
import java.util.*;

public class ftpserver {
    public static void main(String args[]){
    try{
    ServerSocket server = new ServerSocket(3000);
    Socket socket = server.accept();
    
    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
    Scanner scan = new Scanner(System.in);

    System.out.println("Enter the file name: ");
    String Name = scan.nextLine();

    File FileName = new File(Name);
    FileInputStream FIS = new FileInputStream(FileName);
    BufferedInputStream BIS = new BufferedInputStream(FIS);

    long Length = FileName.length();
    byte Content[] = new byte[(int)Length];
    BIS.read(Content, 0, (int)Length);
    dos.write(Content);

    System.out.println("File sent successfully");
    socket.close();
    }
    catch(Exception e){
        System.out.println(e);
    }
}
}
