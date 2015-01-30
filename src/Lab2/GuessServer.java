package Lab2;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fiona and fredrik on 1/30/15.
 */
public class GuessServer {


    public static void main(String[] args) throws IOException{
        System.out.println("Skapar Serversocket");
        ServerSocket ss = new ServerSocket(8080);
        while(true){
            System.out.println("Väntar på klient...");
            Socket s = ss.accept();
            System.out.println("Klient är ansluten");
            BufferedReader request =
                    new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = request.readLine();

            //Gets user's guess from url if there is a guess
            if (str.contains("guess")){
                int guess= getGuess(str);
                System.out.println(guess);
            }


//            System.out.println(str);
//            StringTokenizer tokens =
//                    new StringTokenizer(str," ?");
//            tokens.nextToken(); // Ordet GET
//            String requestedDocument = tokens.nextToken();
//            while( (str = request.readLine()) != null && str.length() > 0){
//                System.out.println(str);
//            }
            System.out.println("Förfrågan klar.");
            s.shutdownInput();

            PrintStream response =
                    new PrintStream(s.getOutputStream());
            response.println("HTTP/1.1 200 OK");
            response.println("Server : Slask 0.1 Beta");

            response.println("Content-Type: text/html");


            //Generates random string to assign as cookie
            String clientId=randomString(10);
            response.println("Set-Cookie: clientId="+clientId);

            response.println();


            //START OF RESPONSE BODY
            //reads html file and prints out in HTTP response message/body
            BufferedReader br = new BufferedReader(new FileReader("GuessGame.html"));
            String line = null;
            while ((line = br.readLine()) != null) {
                response.println(line);
            }


            /*
            File f = new File("."+requestedDocument);
            FileInputStream infil = new FileInputStream(f);
            byte[] b = new byte[1024];
            while( infil.available() > 0){
                response.write(b,0,infil.read(b));
            }
            */
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
        System.out.println(header);
        Pattern pattern = Pattern.compile("guess=([0-9]+) ");
        Matcher matcher = pattern.matcher(header);
        matcher.find();
        int guess = Integer.parseInt(matcher.group(1));
        return guess;
    }
}

//logiken för spelet
//    guess ska in i logiken och ska då säga om man har rätt eller fel
//    skicka meddelande om guess är för hög eller låg och ny range.
// håll koll på användarna.