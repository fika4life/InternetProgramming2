package Lab2;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fiona and fredrik on 1/30/15.
 */

public class GuessServer {

    private static Map<String,PlayerInfo> map = new HashMap<String, PlayerInfo>();


    public static void main(String[] args) throws IOException{
        //Create serversockets
        System.out.println("Skapar Serversocket");
        ServerSocket ss = new ServerSocket(8080);
        while(true){
            System.out.println("Väntar på klient...");
            Socket s = ss.accept();
            System.out.println("Klient är ansluten");
            BufferedReader request =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = request.readLine();

            System.out.println("Förfrågan klar.");
            s.shutdownInput();

            PrintStream response =
                    new PrintStream(s.getOutputStream());
            response.println("HTTP/1.1 200 OK");
            response.println("Server : Slask 0.1 Beta");

            response.println("Content-Type: text/html");


            String line3 = null;
            boolean sessionIdExists=false;
            while ((line3 = request.readLine())!= null){

                if(line3.contains("Cookie")) {
                    sessionIdExists = true;
                    break;
                }

            }
            PlayerInfo playerInfo=null;
            String clientId=null;
            boolean getNewClientId=false;
            if (sessionIdExists){
                clientId = getClientId(line3);
                playerInfo=map.get(clientId);
                if(playerInfo==null){
                    getNewClientId=true;
                }
            }
            else{
                getNewClientId=true;
            }

            if(getNewClientId){
                //Generates random string to assign as cookie
                clientId=randomString(10);
                response.println("Set-Cookie: clientId="+clientId);
                playerInfo = new PlayerInfo();
                map.put(clientId, playerInfo);
            }


            response.println();


            //START OF RESPONSE BODY
            //reads html file and prints out in HTTP response message/body
            BufferedReader br = new BufferedReader(new FileReader("GuessGame.html"));
            String line = null;
            while ((line = br.readLine()) != null) {
                response.println(line);
            }

            //Gets user's guess from url if there is a guess
            if (str.contains("guess")){
                playerInfo.numOfGuesses++;
                response.println("<p>Number of guesses: " + playerInfo.numOfGuesses + "</p>");
                int guess= getGuess(str);
                if(guess==playerInfo.secretNumber){
                    response.println("<p>CORRECT! The number was: " + playerInfo.secretNumber + "</p>");
                    map.put(clientId, new PlayerInfo());
                }
                else {
                    if (guess > playerInfo.secretNumber) {
                        response.println("<p>Guess is too high</p>");
                        playerInfo.highestGuess = guess;


                    } else if (guess < playerInfo.secretNumber) {
                        response.println("<p>Guess is too low</p>");
                        playerInfo.lowestGuess = guess;
                    }
                    response.println("<p>The number is between: " + playerInfo.lowestGuess + " and " + playerInfo.highestGuess+ "</p>");
                }

            }

            BufferedReader br2 = new BufferedReader(new FileReader("GuessGame2.html"));
            String line2= null;
            while ((line2 = br2.readLine()) != null) {
                response.println(line2);
            }

            s.shutdownOutput();
            s.close();
        }
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    private static String randomString(int len)
    {
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    private static int getGuess(String header){

        Pattern pattern = Pattern.compile("guess=([0-9]+) ");
        Matcher matcher = pattern.matcher(header);
        matcher.find();
        int guess = Integer.parseInt(matcher.group(1));
        return guess;
    }

    private static String getClientId(String cookieheader){

        Pattern pattern = Pattern.compile("Cookie: clientId=(.+)");
        Matcher matcher = pattern.matcher(cookieheader);
        matcher.find();
        String clientId = matcher.group(1);

        return clientId;
    }


}


//logiken för spelet
//    guess ska in i logiken och ska då säga om man har rätt eller fel
//    skicka meddelande om guess är för hög eller låg och ny range.
// håll koll på användarna.