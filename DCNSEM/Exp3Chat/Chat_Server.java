/*
            Chat_Server

            Algorithm:
            1.Create Chat_Sever class and Get the port number from the user
            2.Create instance for Chat_Server and pass port number and the object as arguement
            3.Using the instance of the Chat_Server call the execute function
            4.Accept the client using ServerSocket
            5.Create a Thread for the newly connected client and pass socket and the class object as the arguement
            6.Store the Thread in Hash_set for the Future reference
            7.Start the Thread
            8.Inside the Thread class, Run method is executed
            9.Create Input and Output Stream objects
            10.Print the Number of users present in the Server
            11.Get the username from the user and store in a Hash_set
            12.Broadcast the new user connected message to all other users connected to the server
            13.Get the message from the user and broadcast the message to all the users present
            14.When an user sends the BYE message than remove that user and userthread from the server and close the socket


 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Chat_Server{
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> UserThreads = new HashSet<>();

   public Chat_Server(Integer port){
       this.port = port;
   }
    public static void main(String args[]){
        if(args.length < 1){
            System.out.println("Syntax : java Chat_Server <port number>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Chat_Server server = new Chat_Server(port);
        server.execute();
    }
    public void execute(){
        try(ServerSocket ServerSocket = new ServerSocket(port)){
            System.out.println("Server Listening to the port "+ port);
            while(true){
               Socket socket = ServerSocket.accept();
               System.out.println("New User Connected");
               UserThread newUser = new UserThread(socket, this);
               UserThreads.add(newUser);
               newUser.start();
            }

        }
        catch(IOException e){
            System.out.println("....."+e.getMessage()+".....");
        }
    }

    boolean hasUsers(){
        return !this.userNames.isEmpty();
    }
    Set<String> getUserNames(){
        return this.userNames;
    }
    void addUserName(String userName){
        userNames.add(userName);
    }
    void Broadcast(String message, UserThread excludeUser){
        for(UserThread aUser: UserThreads){
            if(aUser != excludeUser){
                aUser.sendMessage(message);
            }
        }
    }
    void removeUser(String userName, UserThread aUser){
        boolean removed = userNames.remove(userName);
        if(removed){
            UserThreads.remove(aUser);
            System.out.println("User "+ userName+" Quitted");
        }
    }
}





class UserThread extends Thread{
    private Socket socket;
    private Chat_Server server;
    private PrintWriter writer;


    public UserThread(Socket socket, Chat_Server server){
        this.socket = socket;
        this.server = server;
    }

    public void run(){
        try{
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            printUsers();

            String userName = reader.readLine();
            server.addUserName(userName);

            String serverMessage = "New User '"+userName+"' connected.";
            server.Broadcast(serverMessage, this);

            String clientMessage = null ;

            do{
                clientMessage = reader.readLine();
                serverMessage = "["+userName+"] : "+clientMessage;
                server.Broadcast(serverMessage, this);
            }while(!(clientMessage.toLowerCase()).equals("bye"));

            server.removeUser(userName, this);
            socket.close();

            serverMessage = userName+" has Quitted";
            server.Broadcast(serverMessage, this);
        }
        catch(IOException e){
            System.out.println("....."+e.getMessage()+".....");

        }
    }
    void printUsers(){
        if(server.hasUsers()){
            writer.println("Users On-Line : "+server.getUserNames());
        }
        else{
            writer.println("Your are the First here.");
        }
    }
    void sendMessage(String message){
        writer.println(message);
    }



}