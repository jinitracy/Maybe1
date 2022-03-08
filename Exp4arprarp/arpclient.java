import java.io.*;
import java.net.*;
import java.util.*;
class arpclient
{
            public static void main(String args[])
            {
            try
            {         
                        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                        Socket socket=new Socket("127.0.0.1",3000);
                        DataInputStream dis=new DataInputStream(socket.getInputStream());
                        DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                        System.out.println("Enter the Logical address(IP):");
                        String str=br.readLine();
                        dos.writeBytes(str+'\n');
                        String str1=dis.readLine();
                        System.out.println("The Physical Address is: "+str1);             
                        socket.close();
            }
            catch (Exception e)
            {
            System.out.println(e);
            }
            }
}
