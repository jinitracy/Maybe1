/*
            Chat_Client

            Algorithm
            1.Create Chat_Client class and get the gostname and port number as arguement.
            2.Set the hostname and port number to the class's object's variable.
            3.Call the Execute function and create a socket variable.
            4.Create two thread class one for reading the content and another for writing the content.
            5.Pass the socket and client's object as the arguement to both the classes and start the thread.
            6.Inside Writer thread create a object for output stream.
            7.Now the method run() get the username from the user and send it to the Server.
            8.Inside do while , define the code to send message continuously untill the user send "BYE" message.
            9.When do while fails close the socket.
            10.Now,In Reader thread create object for Input Stream that will read the content send by the user
            11.Inside run method , read the information send by the connected Client.
            12.Each Thread will comes to end when the User of particular Thread send the BYE message.
 */


import java.io.*;
import java.net.*;

public class Chat_Client{
    private String hostname;
    private int port;
    private String userName;

    Chat_Client(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    public static void main(String args[]){
        if(args.length < 2){
            System.out.println("Syntax : java Chat_Server <hostname> <portnumber>");
            return;
        }
        int port = Integer.parseInt(args[1]);
        String hostname = args[0];

        Chat_Client Client = new Chat_Client(hostname, port);
        Client.execute();
    }
    void execute(){
        try{
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the Server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    String getUserName(){
        return this.userName;
    }
    void setUserName(String userName){
        this.userName = userName;
    }
}
class ReadThread extends Thread{
    private Socket socket;
    private Chat_Client Client;
    private BufferedReader reader;

    ReadThread(Socket socket, Chat_Client Client){
        this.socket = socket;
        this.Client = Client;

        try{
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        }
        catch(Exception e){
            System.out.println("***___"+e.getMessage()+"___***");
        }
    }

    public void run(){
           while(true){
               try{
                   String response = reader.readLine();
                   System.out.println("\n"+response);

                   if(Client.getUserName() != null){
                       System.out.print("["+Client.getUserName()+"] : ");
                   }
               }
               catch(Exception e){
                    System.out.println("......"+e.getMessage()+".....|***");

                   break;
               }
           }

    }

}

class WriteThread extends Thread {
    private Socket socket;
    private Chat_Client Client;
    private PrintWriter writer;

    WriteThread(Socket socket, Chat_Client Client){
        this.socket = socket;
        this.Client = Client;


        try{
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        }
        catch(Exception e){
             System.out.println("***___"+e.getMessage()+"___***");

        }
    }

    public void run(){
            try{
                Console console = System.console();
                String userName = console.readLine("Enter your Name : ");
                Client.setUserName(userName);
                writer.println(userName);
                String message;

                do{
                   message = console.readLine("["+userName+"] : ");
                   writer.println(message);
                }while(!(message.toLowerCase()).equals("bye"));
                socket.close();
            }
            catch(Exception e){
                 System.out.println("***___"+e.getMessage()+"___***");

            }
    }


}