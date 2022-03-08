import java.io.*;
import java.net.*;
import java.util.*;
class arpserver
{
            public static void main(String args[])
            {
            try
            {
                        ServerSocket server=new ServerSocket(3000);
                        System.out.println("Server is connected..");
                        while(true)
                        {           Socket socket=server.accept();
                                    DataInputStream dis=new DataInputStream(socket.getInputStream());
                                    DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                                    String str=dis.readLine();
                                    String ip[]={"165.165.80.80","165.165.79.1"};
                                    String mac[]={"6A:08:AA:C2","8A:BC:E3:FA"};
                                    for(int i=0;i<ip.length;i++)
                                    {
                                                if(str.equals(ip[i]))
                                                {
                                                            dos.writeBytes(mac[i]+'\n');
                                                            break;
                                                }
                                    }                     
                                    server.close();
                        }
                       
            }
            catch(Exception e)
            {
                        System.out.println(e);
            }
            }
}