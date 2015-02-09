package Lab1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Fredrik on 2015-01-26.
 */
public class ServerConnection {

    private Collection<ChatConnection> connectedClients=new ArrayList<ChatConnection>();

    private ServerConnection(){

        try {
            ServerSocket ss=new ServerSocket(portNumber);
            Socket s=null;
            //socket waits for instream from client
            while((s=ss.accept())!=null){

                //creates new chatConnection with socket and this serverConnection object as parameters.
                ChatConnection chatConnection=new ChatConnection(s,this);

                //start the chatConnection thread
                chatConnection.start();

                //add the current chatConnection to the connectedClients collection
                connectedClients.add(chatConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final int portNumber=2635;


    //sends messages to all connected clients
    public void sendMessage(String message,String name){
        for(ChatConnection con: connectedClients){
            con.sendMessage(message,name);
        }
    }

    public static void main(String[] args) {
        //creates serverConnection instance
        final ServerConnection serverConnection=new ServerConnection();

    }
}
