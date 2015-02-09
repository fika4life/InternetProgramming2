package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatConnection extends Thread {

    private BufferedReader in;
    private PrintWriter out;
    private ServerConnection parent;
    private String name;

    public ChatConnection(Socket socket, ServerConnection parent) {
        this.parent=parent;
        try {
            //create Input and output streams of server socket
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out= new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public synchronized void sendMessage(String message,String name){
        out.println(name+": "+message);
        out.flush();
    }

    @Override
    public void run() {
        //calls Threads run
        super.run();
        while(true){
            try {
//                reads message incoming to server socket and adds to message variable
                String message=in.readLine();
                if(message==null){
                    break;
                }
//                allows chat connection to know what signifies a name instead of a message
                else if(message.startsWith("!name!: ")){
                    name=message.replaceFirst("!name!: ","");
                }
                else {
//                    sends input from clients to other clients
                    parent.sendMessage(message,name);
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }
}