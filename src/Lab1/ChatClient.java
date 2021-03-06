package Lab1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Fredrik on 2015-01-26.
 */
public class ChatClient {



    private BufferedReader in;
    private PrintWriter out;
    private static final Scanner scan=new Scanner(System.in);

    public ChatClient(Socket socket) {
        try {

            //gets in and out streams of client
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    while(true) {
                        try {
                            String message=in.readLine();
                            if(message==null){
                                break;
                            }
                            System.out.println(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }

                }
            }.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void startClient(){
        sendName();
        writeSomeThing();
    }

    private void sendName(){
        System.out.println("Write your name here: ");
        String name=scan.nextLine();
        out.println("!name!: "+name);
        //in order to send out the message
        out.flush();
    }

    public void writeSomeThing(){


        //writes out message provided by client
        while(true){
            out.println(scan.nextLine());
            out.flush();
        }
    }

    public static void main(String[] args){
        System.out.println("Write the IP of the server:");
        String IP=scan.nextLine();
        try {
            new ChatClient(new Socket(IP,ServerConnection.portNumber)).startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
