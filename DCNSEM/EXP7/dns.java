import java.io.*;
import java.net.*;
import java.util.*;

public class dns {
    public static void main(String args[]){
        int n;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("Enter 1. DNS 2. Reverse DNS 3. Exit");
            n = Integer.parseInt(System.console().readLine());
            System.out.println(n);

          if(n==1){
              try{
              System.out.println("Enter the host name");
              String hname= in.readLine();
              InetAddress hostname = InetAddress.getByName(hname);
              System.out.println("The host name is" + hostname.getHostName());
              System.out.println("The host address is" + hostname.getHostAddress());
          }  
          catch(Exception e){
              System.out.println(e);
          }
        }

          else if(n==2){
              try{
            System.out.println("Enter the host address");
            String ipadd= in.readLine();
            InetAddress hostadd = InetAddress.getByName(ipadd);
            System.out.println("The host name is" + hostadd.getHostName());
            System.out.println("The host address is" + hostadd);
          }
          catch(Exception e){
              System.out.println(e);

          }
        }
        }while(n==3);
    }
}
